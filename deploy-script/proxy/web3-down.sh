#!/bin/bash

sudo echo -e "least_conn;" > /etc/nginx/upstream.conf
sudo echo -e "server 192.168.100.157:8080 max_fails=3 fail_timeout=3s;" >> /etc/nginx/upstream.conf
sudo echo -e "server 192.168.100.59:8080 max_fails=3 fail_timeout=3s;" >> /etc/nginx/upstream.conf
sudo echo -e "server 192.168.100.39:8080 down;" >> /etc/nginx/upstream.conf
sudo nginx -s reload