source_s3_path=$1
int_partner_seg_id=$2
int_partner_id=$3
country_id=$4
mode=$5
destination_s3_path=$6

input_dir="/mnt/uploader/input/output_${int_partner_seg_id}"
data_dir="/mnt/uploader/data/output_${int_partner_seg_id}"
watch_dir="/mnt/uploader/watch/output_${int_partner_seg_id}"

rm -rf ${input_dir}
rm -rf ${data_dir}
rm -rf ${watch_dir}

mkdir -p ${input_dir}
mkdir -p ${watch_dir}

aws s3 cp ${source_s3_path} ${input_dir} --recursive
gunzip ${input_dir}/*
cat ${input_dir}/* >> ${input_dir}/inputfile.txt


segment_size=`wc -l ${input_dir}/inputfile.txt | gawk -F\  '{print $1}'`
echo "Segment Size : ${segment_size}"

nohup python $HOME/upload_scripts/watch.py ${watch_dir} ${int_partner_id} ${country_id} ${destination_s3_path} ${mode} &
python $HOME/upload_scripts/transformer.py ${input_dir} ${data_dir} ${watch_dir} ${int_partner_id} ${int_partner_seg_id} ${segment_size} ${mode}

find ${data_dir} -type f -not -name part* -delete
mv ${data_dir}/* ${watch_dir}