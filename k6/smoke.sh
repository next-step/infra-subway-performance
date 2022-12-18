#!/bin/bash

k6 run --out influxdb=http://localhost:8086/subwayk6db --config smoke.json script.js
