#!/bin/bash

sudo echo -e "least_conn;\nserver 192.168.100.157:8080 max_fails=3 fail_timeout=3s;\nserver 192.168.100.157:8081 max_fails=3 fail_timeout=3s;" > /etc/nginx/upstream.conf
sudo nginx -s reload