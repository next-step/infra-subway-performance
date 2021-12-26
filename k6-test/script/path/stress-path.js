import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 100 },
        { duration: '1m', target: 100 },
        { duration: '1m', target: 300 },
        { duration: '1m', target: 300 },
        { duration: '1m', target: 500 },
        { duration: '1m', target: 500 },
        { duration: '5m', target: 0 },
    ],
};

const BASE_URL = 'https://jerry92k-subway.n-e.kr/';

export default function ()  {

    var params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let source =24 // (동작역)
    let target =21 // (광교역)

    http.get(`${BASE_URL}/paths?source=${source}&target=${target}`, params);
    sleep(1);
};
