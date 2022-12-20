#!/bin/bash

k6 run --out influxdb=http://monitoring:8086/k6_subway --config smoke.json script.js
