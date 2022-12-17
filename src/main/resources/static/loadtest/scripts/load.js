import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    stages: [
        { duration: '5m', target: 2 },
        { duration: '5m', target: 5 },
        { duration: '5m', target: 7 },
        { duration: '5m', target: 7 },
        { duration: '5m', target: 5 },
        { duration: '5m', target: 2 }
    ],
    thresholds: {
        http_req_duration: ['p(99)<100'], // 99% of requests must complete below 100ms
    },
};

const BASE_URL = 'https://jisu1211.kro.kr';

const source_min = 1;
const source_max = 10;
const target_min = 101;
const target_max = 110;

function random(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
}

function main() {
    check(http.get(`${BASE_URL}`), {
        'You have successfully accessed the main page.': (res) => res.status === 200,
    });
}

function path() {
    check(http.get(`${BASE_URL}/path`), {
        'You have successfully accessed the search path page.': (res) => res.status === 200,
    });
}

function findPath(source, target) {
    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    check(http.get(`${BASE_URL}/path?source=${source}&target=${target}`, params), {
        'The path was successfully searched.': (res) => res.status === 200,
    });
}

export default function() {
    main();
    path();
    findPath(random(source_min, source_max), random(target_min, target_max));

    sleep(1);
};
