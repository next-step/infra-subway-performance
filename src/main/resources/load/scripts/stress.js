import http from 'k6/http';
import {check, group, sleep, fail} from 'k6';

export let options = {
    stages: [
        {duration: '2m', target: 5},
        {duration: '3m', target: 30},
        {duration: '5m', target: 50},
        {duration: '5m', target: 100},
        {duration: '5m', target: 150},
        {duration: '5m', target: 200},
        {duration: '5m', target: 250}
    ], thresholds: {
        http_req_duration: ['p(99)<100'],
    },
};

const BASE_URL = 'https://hahoho87-subway.kro.kr';

function main() {
    let mainPageRes = http.get(`${BASE_URL}/`);
    check(mainPageRes, {
        'Moved to main page': (res) => res.status === 200,
    });
}

function path() {
    let pathPageRes = http.get(`${BASE_URL}/path`);
    ;
    check(pathPageRes, {
        'Moved to path page': (res) => res.status === 200,
    });
}

function findPath(authHeaders) {
    let findPathRes = http.get(`${BASE_URL}/path?source=1&target=50`, authHeaders);
    check(findPathRes, {
        'Found path': (res) => res.status === 200,
    });
}

export default function () {
    main();
    path();
    findPath();

    sleep(1);
};
