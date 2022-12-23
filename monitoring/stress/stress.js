import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        {duration: '5s', target: 23}, // ramping up
        {duration: '30s', target: 23},
        {duration: '5s', target: 80}, // ramping up
        {duration: '30s', target: 80},
        {duration: '5s', target: 150}, // ramping up
        {duration: '30s', target: 150},

        {duration: '5s', target: 180}, // stress peak
        {duration: '30s', target: 180},

        {duration: '3s', target: 150}, // ramping down
        {duration: '15s', target: 150},
        {duration: '3s', target: 80}, // ramping down
        {duration: '15s', target: 80},
        {duration: '3s', target: 23}, // ramping down
        {duration: '15s', target: 23},
        {duration: '5s', target: 0}, // ramping down
    ],
    thresholds: {
        http_req_duration: ['p(95)<100'], // 99% of requests must complete below 1.0s
    },
};

const BASE_URL = 'https://90mansik.kro.kr/';

export default function ()  {
    mainPage();
    pathPage();
    findPage();

    sleep(1);
}

function mainPage(){
    const response = http.get(BASE_URL);

    check(response, {
        'success mainPage' : (res) => res.status === 200
    });
}

function pathPage(){
    const response = http.get(`${BASE_URL}/path`);

    check(response, {
        'success pathPage' : (res) => res.status === 200
    });
}

function findPage(){
    const response = http.get(`${BASE_URL}/path?source=1&target=3`);

    check(response, {
        'success findPage' : (res) => res.status === 200
    });
}
