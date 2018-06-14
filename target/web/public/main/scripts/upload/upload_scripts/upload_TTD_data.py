import datetime
import sys
import httplib, urllib
import json
import csv
import os
import concurrent.futures
from hashlib import sha1
import hmac
import base64
import requests


def readFile(base_path, file_name):
	filePath = base_path+"/"+file_name
	file = open(filePath, 'r')
	reader = csv.reader(file, delimiter="\t")
	return reader

def upload_to_ttd(jsonBody):
	jsonBodyStr = json.dumps(jsonBody)
	key = b'92355c50eef84c71bd31b4daba0e4080'
	raw = b""+jsonBodyStr+""
	hashed = None
	hashed = hmac.new(key,raw, sha1).digest()
	headers = {'content-type': 'application/json','TTDSignature': base64.b64encode(hashed).decode()}
	url = 'http://euw-data.adsrvr.org/data/thirdparty'
	data = jsonBodyStr
	r = requests.post(url, data=data, headers=headers)
	print r.status_code
	# print "200 ok"
	if r.status_code != 200:
		print "NOT CORRECT"


def main(base_path, file_name):
	reader = readFile(base_path, file_name)
	jsonBody={}
	ItemList=[]
	i = 0
	j = 0
	DataProviderId = "zeotap"

	for key in reader:
		adids = key[:-1]
		segIdList = key[-1].split(",")

		if adids[0] != '':
			DataList = []
			item = {}
			jsonBody["DataProviderId"] = DataProviderId
			current_time = str(datetime.datetime.utcnow())
			i=i+1

			for idx in range (0,len(segIdList)):
				data={}
				data["TimestampUtc"] = current_time
				data["Name"] = segIdList[idx]
				data["TtlInMinutes"] = 43200
				DataList.append(data)

			item["DAID"] = adids[0]
			item["Data"] = DataList
			ItemList.append(item)
			jsonBody["Items"] = ItemList

			if i%5000 == 0:
				upload_to_ttd(jsonBody)
				jsonBody={}
				ItemList=[]
				item = {}
				i=0

		if len(adids) > 1 and adids[1] != '':
			DataList = []
			item = {}
			jsonBody["DataProviderId"] = DataProviderId
			current_time = str(datetime.datetime.utcnow())
			i=i+1

			for idx in range (0,len(segIdList)):
				data={}
				data["TimestampUtc"] = current_time
				data["Name"] = segIdList[idx]
				data["TtlInMinutes"] = 43200
				DataList.append(data)

			item["TDID"] = adids[1]
			item["Data"] = DataList
			ItemList.append(item)
			jsonBody["Items"] = ItemList

			if i%5000 == 0:
				upload_to_ttd(jsonBody)
				jsonBody={}
				ItemList=[]
				item = {}
				i=0
				
	upload_to_ttd(jsonBody)


def start_upload_tdd(source, destination, mode):
	files = os.listdir(source)
	with concurrent.futures.ThreadPoolExecutor(max_workers=25) as executor:
	    # Start the load operations and mark each future with its URL
	    future_to_url = {executor.submit(main, source, file_name): file_name for file_name in files}
	    for future in concurrent.futures.as_completed(future_to_url):
	        url = future_to_url[future]
	        try:
	            data = future.result()
	        except Exception as exc:
	            print('%r generated an exception: %s' % (url, exc))

