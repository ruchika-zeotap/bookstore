import subprocess
import os
import time
from datetime import datetime

profile = "Test"

def copy_to_s3(source, destination):
    if destination != None:
        process = subprocess.Popen(['aws', 's3', 'cp', source, destination, '--recursive', '--profile', profile], stdout=subprocess.PIPE)
        output, error = process.communicate()
        print output


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


def rename(source):
	print "Starting rename ..."
	today = datetime.now().strftime("%Y%m%d")
	epoch = int(time.time())
	for input_file in os.listdir(source):
		process = subprocess.Popen('mv {} {}zeotap_EU_{}_{}.tsv'.format(source+input_file, source, today, epoch), stdout=subprocess.PIPE, shell=True)
		output, error = process.communicate()
		epoch = epoch + 1
	print "Finished rename ..."


def start_upload_teads(source, destination, mode):
	global profile
	if mode == "PROD":
		profile = "Teads"

	source = source + "/"
	split(source, "5G")
	rename(source)
	compress(source)
	copy_to_s3(source, destination)
