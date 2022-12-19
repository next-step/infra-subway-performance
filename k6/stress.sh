#!/bin/bash

k6 run --config stress.json --out influxdb=http://monitoring:8086/k6_subway script.js 1> stress.log 2>&1 &
