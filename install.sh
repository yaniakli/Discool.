#!/bin/bash -

printf "DB username : " ; read -r username

mysql < src/main/resources/rebuild_db_no_cred.sql -u $username -p

mvn install