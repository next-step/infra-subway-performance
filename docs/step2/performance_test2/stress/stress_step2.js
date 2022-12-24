import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 200 },
        { duration: '2m', target: 200 },

        { duration: '1m', target: 400 },
        { duration: '2m', target: 400 },

        { duration: '1m', target: 800 },
        { duration: '2m', target: 800 },

        { duration: '1m', target: 1200 },
        { duration: '2m', target: 1200 },

        { duration: '8m', target: 0 }, // 20m
    ],
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://runningmap.kro.kr';

export default function ()  {

    accessPathFindPage();
    findPath();

    sleep(1);
};

function accessPathFindPage() {
    let requestPathPage = http.get(`${BASE_URL}/path`);
    check(requestPathPage, {
        '[SUCCESS] connect to find path page': (response) => response.status === 200,
    });
}

function findPath() {
    let findPathResponse = http.get(`${BASE_URL}/paths/?source=94&target=246`);
    check(findPathResponse, {
        '[SUCCESS] find shortest path': (response) => response.status === 200,
        '[SUCCESS] find shortest path distance': (response) => response.json().distance > 0,
    })
}