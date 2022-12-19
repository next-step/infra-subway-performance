#!/bin/bash

k6 run --config load.json --out influxdb=http://monitoring:8086/k6_subway  script.js 1> load.log 2>&1 &
