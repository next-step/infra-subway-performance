import http from 'k6/http';
import {check, group, sleep, fail} from 'k6';

export let options = {
    stages: [
        { duration: '5s', target: 100 },
        { duration: '10s', target: 100 },
        { duration: '5s', target: 200 },
        { duration: '10s', target: 200 },
        { duration: '5s', target: 300 },
        { duration: '10s', target: 300 },
        { duration: '5s', target: 400 },
        { duration: '10s', target: 400 },
        { duration: '5s', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(99)<1000'],
    },
};

const BASE_URL = 'http://15.164.96.247/';

export default function () {
    let response = http.get(`${BASE_URL}/paths/?source=4&target=3`);
    check(response, {
        'path page': response => response.status === 200
    });

    sleep(1);
}
