import os
import json
import psycopg2
from datetime import datetime
import subprocess
import time
from concurrent.futures import ThreadPoolExecutor, as_completed


INMOBI_INT_PARTNER_ID = 8
today = datetime.now().strftime("%Y-%m-%d")
profile = "Test"

def remove_success_file(destination):
	destination = "{}{}/_SUCCESS".format(destination, today)
	process = subprocess.Popen(['aws', 's3', 'rm', destination, '--profile', profile], stdout=subprocess.PIPE)
	output, error = process.communicate()
	print output


def upload_success_file(destination):
	source = os.environ["HOME"] + "/_SUCCESS"
	with open(source, "w+") as sf:
		sf.close()

	destination = "{}{}/".format(destination, today)
	process = subprocess.Popen(['aws', 's3', 'cp', source, destination, '--profile', profile], stdout=subprocess.PIPE)
	output, error = process.communicate()
	print output


def upload_segment_taxonomy(destination, mode):
	print "Starting taxonomy file upload ..."
	source = os.environ["HOME"] + "/inmobi_taxonomy_file_" + mode
	destination = "{}{}/segmenttaxonomy/".format(destination, today)
	process = subprocess.Popen(['aws', 's3', 'cp', source, destination, '--profile', profile], stdout=subprocess.PIPE)
	output, error = process.communicate()
	print output
	print "Finished taxonomy file upload ..."


def create_segment_taxonomy(config, mode):
	conn = psycopg2.connect("host={} user=postgres dbname={} password={}".format(config[mode]["DB_HOST"], config[mode]["DB_NAME"], config[mode]["DB_PASSWORD"]))
	cur = conn.cursor()
	taxonomy_segments = "select int_partner_segment_id, segment_name, segment_desc from segments where int_partner_id = '{}'".format(INMOBI_INT_PARTNER_ID)
	cur.execute(taxonomy_segments)
	taxonomy = cur.fetchall()

	with open(os.environ["HOME"] + "/inmobi_taxonomy_file_" + mode, "w+") as tf:
   		for row in taxonomy:
   			metadata = {
   				"segment_meta" : {
   					"segment-id" : row[0],
   					"name" : row[1],
   					"description" : row[2],
   					"provider-name" : "zeotap"
   				}
   			}

   			tf.write(json.dumps(metadata) + "\n")
   		tf.close()
   	
	cur.close()
	conn.close()


def split_input_files(source):
	print "Starting split ..."
	epoch = str(int(time.time()))

	for input_file in os.listdir(source):
		process = subprocess.Popen('split -l 1000000 {} {} --additional-suffix={}'.format(source+input_file, source, input_file+ "_" + epoch), stdout=subprocess.PIPE, shell=True)
		output, error = process.communicate()
		print output

		process = subprocess.Popen('rm {}'.format(source+input_file), stdout=subprocess.PIPE, shell=True)
		output, error = process.communicate()
		print output

	print "Finished split ..."


def compress_lzo(source):
	print "Starting compression ..."
	process = subprocess.Popen("lzop {}*".format(source), stdout=subprocess.PIPE, shell=True)
	output, error = process.communicate()

	process = subprocess.Popen("find {} -type f -not -name *.lzo -delete".format(source), stdout=subprocess.PIPE, shell=True)
	output, error = process.communicate()

	print "Finished compression ..."


def upload_data_files(source, destination):
	print "Starting upload ..."
	destination = "{}{}/usersegmentmapping/".format(destination, today)
	process = subprocess.Popen(['aws', 's3', 'cp', source, destination, '--recursive', '--profile', profile], stdout=subprocess.PIPE)
	output, error = process.communicate()
	print output
	print "Finished upload ..."


def create_data_file(source, input_file, destination):
	with open(source+input_file, "r") as sf, open(destination + input_file + "_processed", "w+") as df:
		for line in sf:
			data = line.split('\t')
			segments = [str(segment_id.strip()) for segment_id in data[-1].split(',')]

			for adid in data[:-1]:	
				if adid != '':
					output_line = {
						"segment-data" : {
							"udid" : adid,
							"idtype" : "GPID",			#can be IDFA/GPID/O1
							"segments" : segments,
							"refreshed-time" : today	
						}
					}

					df.write(json.dumps(output_line) + "\n")

		sf.close()
		df.close()

	process = subprocess.Popen('rm {}'.format(source+input_file), stdout=subprocess.PIPE, shell=True)
	output, error = process.communicate()


def create_data_files(source):
	print "Starting data file creation ..."
	input_files = os.listdir(source)

	with ThreadPoolExecutor(max_workers=10) as executor:
		future_to_url = {executor.submit(create_data_file, source, input_file, source): input_file for input_file in input_files}
		for future in as_completed(future_to_url):
			url = future_to_url[future]
			try:
				data = future.result()
			except Exception as e:
				print "'{} generated an exception :{}'".format(url, e)

	print "Finished data file creation ..."


def start_upload_inmobi(source, destination, mode):
	global profile
	if mode == "PROD":
		profile = "InMobi"

	with open(os.environ["HOME"] + "/upload_scripts/config.json") as f:
	    config = json.load(f)

	source = source + "/"
	create_segment_taxonomy(config, mode)
	split_input_files(source)
	create_data_files(source)
	compress_lzo(source)
	remove_success_file(destination)
	upload_segment_taxonomy(destination, mode)
	upload_data_files(source, destination)
	upload_success_file(destination)