import subprocess
import os
import time
from datetime import datetime

profile = "Test"

def copy_to_s3(source, destination):
	print "Starting upload ..."
	if destination != None:
		mApp_path, mWeb_path = destination.split(',')
		for input_file in os.listdir(source):
			if 'mApp' in input_file:
				dest = mApp_path + "dt={}/".format(datetime.now().strftime("%Y%m%d"))
				process = subprocess.Popen(['aws', 's3', 'cp', source+input_file, dest, '--profile', profile], stdout=subprocess.PIPE)
				output, error = process.communicate()
				print output

			elif 'mWeb' in input_file:
				dest = mWeb_path + "dt={}/".format(datetime.now().strftime("%Y%m%d"))
				process = subprocess.Popen(['aws', 's3', 'cp', source+input_file, dest, '--profile', profile], stdout=subprocess.PIPE)
				output, error = process.communicate()
				print output

	print "Finished upload ..."


def split(source, max_size):
	print "Starting split ..."
	for input_file in os.listdir(source):
		process = subprocess.Popen('split -C {} {} {} --additional-suffix={}'.format(max_size, source+input_file, source, input_file), stdout=subprocess.PIPE, shell=True)
		output, error = process.communicate()

		process = subprocess.Popen('rm {}'.format(source+input_file), stdout=subprocess.PIPE, shell=True)
		output, error = process.communicate()

	print "Finished split ..."


def compress(source):
	print "Starting compress ..."
	process = subprocess.Popen('gzip {}*'.format(source), stdout=subprocess.PIPE, shell=True)
	output, error = process.communicate()
	print "Finished compress ..."


def create_mApp_mWeb_files(source):
	print "Creating mApp and mWeb files ..."
	epoch = int(time.time())

	process = subprocess.Popen("ec2metadata --availability-zone", stdout=subprocess.PIPE, shell=True)
	region, error = process.communicate()

	original_files = os.listdir(source)

	for input_file in original_files:
		with open(source+input_file, "r") as sf, open(source+input_file+"_mApp_" + str(epoch), "w+") as df:
			for line in sf:
				data = line.split('\t')

				if data[0] != '':
					df.write("{}\t{}".format(data[0], data[-1]))

			sf.close()
			df.close()

		if region.split("-")[0] != "us":
			with open(source+input_file, "r") as sf, open(source+input_file+"_mWeb_" + str(epoch), "w+") as df:
				for line in sf:
					data = line.split('\t')

					if data[1] != '':
						df.write("{}\t{}".format(data[1], data[-1]))

				sf.close()
				df.close()

		process = subprocess.Popen("rm {}".format(source+input_file), stdout=subprocess.PIPE, shell=True)
		output, error = process.communicate()

	print "Finished mApp and mWeb files ..."


def start_upload_adform(source, destination, mode):
	global profile
	if mode == "PROD":
		profile = "Adform"

	source = source + "/"
	split(source, "99M")
	create_mApp_mWeb_files(source)
	compress(source)
	copy_to_s3(source, destination)
