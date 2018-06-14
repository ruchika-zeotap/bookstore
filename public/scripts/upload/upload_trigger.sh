source_s3_path=$1
export_guid=$2
segment_mapping=$3
mids_mapping=$4
int_partner_id=$5
country_id=$6
destination_s3_path=$7

remote_user="ubuntu"
RANDOM=$$$(date +%s)

us_default=("30.0.110.146")
eu_default=("20.0.10.5")
in_default=("10.0.131.182")

us_uploaders=("30.0.110.146" "10.30.10.203")
eu_uploaders=("20.0.10.5" "10.40.10.79")
in_uploaders=("10.0.131.182" "10.60.10.6")

distributables=("1" "3" "4" "6" "9" "14")
is_distributable=false

for ip in ${distributables[@]};
do
	if [ "${ip}" -eq "${int_partner_id}" ]; then
		is_distributable=true
	fi
done

if [ "${country_id}" -eq "235" ] || [ "${country_id}" -eq "40" ]; then
	if [ "${is_distributable}" == true ]; then
		remote_ip=${us_uploaders[$RANDOM % ${#us_uploaders[@]}]}
	else
		remote_ip=${us_default[$RANDOM % ${#us_default[@]}]}
	fi
	pem_file="${HOME}/virginia-prod-key.pem"

elif [ "${country_id}" -eq "110" ] || [ "${country_id}" -eq "208" ] || [ "${country_id}" -eq "234" ] || [ "${country_id}" -eq "84" ] || [ "${country_id}" -eq "77" ]; then
	if [ "${is_distributable}" == true ]; then
		remote_ip=${eu_uploaders[$RANDOM % ${#eu_uploaders[@]}]}
	else
		remote_ip=${eu_default[$RANDOM % ${#eu_default[@]}]}
	fi
	pem_file="${HOME}/Ireland-prodkey.pem"

elif [ "${country_id}" -eq "103" ]; then
	if [ "${is_distributable}" == true ]; then
		remote_ip=${in_uploaders[$RANDOM % ${#in_uploaders[@]}]}
	else
		remote_ip=${in_default[$RANDOM % ${#in_default[@]}]}
	fi
	pem_file="${HOME}/mumbai-emr-prodkey.pem"
fi

echo "Connecting to : ${remote_ip}"

ssh -i ${pem_file} ${remote_user}@${remote_ip} <<- ENDSSH
    cd \$HOME/zat-uploaders/
	nohup python __init__.py ${source_s3_path} ${export_guid} "${segment_mapping}" "${mids_mapping}" ${int_partner_id} ${country_id} TEST ${destination_s3_path} < /dev/null >> zat_upload.log 2>&1 &
ENDSSH

echo "Started upload for : ${export_guid}"