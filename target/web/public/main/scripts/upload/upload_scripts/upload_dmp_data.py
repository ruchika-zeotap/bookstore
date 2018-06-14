import httplib
import os
import subprocess
from concurrent.futures import ThreadPoolExecutor, as_completed

import ddp_pb2


def uploader(base_path, file_name):
    with open(base_path + '/' + file_name) as fp:
        content = fp.readlines()
        max_count = 1000

        for line in content:
            data = line.split('\t')
            segment_id = int(data[-1])

            if max_count == 1000:
                data_request = ddp_pb2.UpdateUsersDataRequest()

            for user_id in data[:-1]:
                if user_id != '':
                    for user_id_type in [0, 1, 2]:
                        ops = data_request.ops.add()
                        ops.user_id = user_id
                        ops.user_list_id = segment_id
                        ops.time_added_to_user_list = 0
                        ops.user_id_type = user_id_type
                        max_count -= 1

                        if max_count == 0:
                            conn = httplib.HTTPSConnection("cm.g.doubleclick.net")
                            conn.request("POST", "/upload?nid=zeotap_dmp", data_request.SerializeToString())
                            response = conn.getresponse()
                            print response.status, response.reason
                            data = response.read()
                            res = ddp_pb2.UpdateUsersDataResponse()
                            res.MergeFromString(data)
                            conn.close()
                            del data_request
                            max_count = 1000

                        if max_count == 1000:
                            data_request = ddp_pb2.UpdateUsersDataRequest()

        fp.close()


def delete_files(path):
    process = subprocess.Popen(['rm', '-rf', path], stdout=subprocess.PIPE)
    output, error = process.communicate()


def start_upload_dmp(source, destination, mode):
    files = os.listdir(source)
    with ThreadPoolExecutor(max_workers=10) as executor:
        future_to_url = {executor.submit(uploader, source, file_name): file_name for file_name in files}
        for future in as_completed(future_to_url):
            url = future_to_url[future]
            try:
                data = future.result()
            except Exception as e:
                print "'{} generated an exception :{}'".format(url, e)

                # delete_files(source)
