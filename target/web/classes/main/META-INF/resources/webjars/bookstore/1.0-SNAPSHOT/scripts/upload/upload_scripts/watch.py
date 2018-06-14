import os
import subprocess
import sys
import time
import json

from upload_ddp_data import start_upload_ddp
from upload_adform_s3_data import start_upload_adform
from upload_smartclip_s3_data import start_upload_smartclip
from upload_teads_s3_data import start_upload_teads
from upload_TTD_data import start_upload_tdd
from upload_dmp_data import start_upload_dmp
from upload_inmobi_data import start_upload_inmobi

uploader_map = {
    '1': {
        '235': start_upload_ddp,
        '208': start_upload_ddp,
        '110': start_upload_ddp,
        '103': start_upload_ddp,
        '234': start_upload_ddp,
        '84': start_upload_ddp
    },
    # '2': {
    #     '208': start_upload_s3
    # },
    '3': {
        '235': start_upload_adform,
        '208': start_upload_adform,
        '110': start_upload_adform,
        '103': start_upload_adform,
        '234': start_upload_adform,
        '84' : start_upload_adform
    },
    '4': {
        '235': start_upload_tdd,
        '208': start_upload_tdd,
        '110': start_upload_tdd,
        '234': start_upload_tdd,
        '84': start_upload_tdd
    },
    '5': {
        '110': start_upload_smartclip
    },
    '6': {
        '235': start_upload_dmp,
        '208': start_upload_dmp,
        '110': start_upload_dmp,
        '103': start_upload_dmp,
        '234': start_upload_dmp,
        '84': start_upload_dmp
    },
    '7': {
        '110': start_upload_teads,
        '208':start_upload_teads
    },
    '8': {
        '103': start_upload_inmobi
    }
}


def find_path_size(path):
    process = subprocess.Popen(['du', '-s', path], stdout=subprocess.PIPE)
    output, error = process.communicate()
    return int(output.split('\t')[0])


def start_watch(source, destination, mode):
    orig_size = find_path_size(source)
    while True:
        size = find_path_size(source)
        print "Current size : ", size
        if size != orig_size:
            break
        else:
            time.sleep(2)
            print "Watching path ..." + source

    while True:
        last_size = find_path_size(source)
        time.sleep(2)
        current_size = find_path_size(source)
        if last_size == current_size:
            break

    time.sleep(2)
    print "Starting upload ..."
    uploader_map[intPartnerId][countryId](source, destination, mode)


if __name__ == "__main__":
    source = sys.argv[1]
    intPartnerId = sys.argv[2]
    countryId = sys.argv[3]
    mode = sys.argv[4]

    if mode == "PROD":
        with open(os.environ["HOME"] + "/upload_scripts/s3destination_prod.config", "r") as fp:
            s3_destination = json.load(fp)
    elif mode == "TEST":
        with open(os.environ["HOME"] + "/upload_scripts/s3destination_test.config", "r") as fp:
            s3_destination = json.load(fp)

    destination_s3_path = s3_destination[intPartnerId][countryId]
    start_watch(source, destination_s3_path, mode)
