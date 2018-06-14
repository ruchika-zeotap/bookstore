import os
import subprocess
import sys
import time
import psycopg2
import json

from pyspark import SparkConf, SparkContext
from pyspark.sql.functions import *

APP_NAME = "HelloWorld of Big Data"
partitions = 2

def insert_size_in_db(size, int_partner_id, int_partner_seg_id):
    if int(size) != 0:
        print "Updating count in DB ..."
        query = "update segments set segment_size={} where int_partner_id='{}' and (int_partner_segment_id='{}' or int_partner_custom_fields ->> 'refid' = '{}')".format(size, int_partner_id, int_partner_seg_id, int_partner_seg_id)
        cur.execute(query)
        conn.commit()
        print "Updated count in DB ..."
        

def transform(x):
    x[-1] = segmentListFormat[int_partner_id].format(int_partner_seg_id)
    return '\t'.join(x)


def run_job(context, filename):
    print "Transformer input file : ", filename
    print "Transformer output location :", output_dir_path

    textRDD = context.textFile(filename).repartition(partitions)
    output = textRDD.flatMap(lambda x: x.split('\n')).map(lambda y: y.split('\t')).map(lambda x: transform(x))
    output.saveAsTextFile(output_dir_path)


if __name__ == "__main__":
    # Configure Spark
    conf = SparkConf().setAppName(APP_NAME)
    conf = conf.setMaster("local[*]")
    context = SparkContext(conf=conf)

    input_dir_path = sys.argv[1]
    output_dir_path = sys.argv[2]
    watch_dir_path = sys.argv[3]
    int_partner_id = sys.argv[4]
    int_partner_seg_id = sys.argv[5]
    segment_size = sys.argv[6]
    mode = sys.argv[7]

    with open(os.environ["HOME"] + "/upload_scripts/config.json") as f:
        config = json.load(f)

    with open(os.environ["HOME"] + "/upload_scripts/segmentIdListFormat.config") as f:
        segmentListFormat = json.load(f)

    conn = psycopg2.connect("host={} user=postgres dbname={} password={}".format(config[mode]["DB_HOST"], config[mode]["DB_NAME"], config[mode]["DB_PASSWORD"]))
    cur = conn.cursor()

    print "Connected to DB ..."

    print "Input path :", input_dir_path
    print "Output path :", output_dir_path
    print "Watch path :", watch_dir_path

    # Execute Main functionality
    run_job(context, input_dir_path + '/inputfile.txt')
    #insert_size_in_db(segment_size, int_partner_id, int_partner_seg_id)
