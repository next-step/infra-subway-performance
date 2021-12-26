import http from 'k6/http';
import {check, sleep} from 'k6';

export let options = {
    vus: 50,
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://minseoklim.p-e.kr';
const USERNAME = 'test@test.com';
const PASSWORD = 'password';
const AGE = 20;

export default function () {
    const before = new Date().getTime();
    const T = 6 * 1.5;

    let jsonHeaders = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let htmlHeaders = {
        headers: {
            'Content-Type': 'text/html',
        },
    };

    // 지하철역 목록 조회 화면
    let stationsPage = http.get(`${BASE_URL}/stations`, htmlHeaders);
    check(stationsPage, {'retrieved stationsPage': (obj) => obj != null});

    // 지하철역 목록 조회
    let stations = http.get(`${BASE_URL}/stations`, jsonHeaders).json();
    check(stations, {'retrieved stations': (obj) => obj != null});

    // 경로 검색 화면
    let pathPage = http.get(`${BASE_URL}/path`, htmlHeaders);
    check(pathPage, {'retrieved pathPage': (obj) => obj != null});

    // 경로 검색
    let paths = http.get(`${BASE_URL}/paths/?source=1&target=2`, jsonHeaders).json();
    check(paths, {'retrieved paths': (obj) => obj != null});

    // 노선 조회 화면
    let linesPage = http.get(`${BASE_URL}/lines`, htmlHeaders);
    check(linesPage, {'retrieved linesPage': (obj) => obj != null});

    // 노선 조회
    let lines = http.get(`${BASE_URL}/lines`, jsonHeaders).json();
    check(lines, {'retrieved lines': (obj) => obj != null});

    const after = new Date().getTime();
    const diff = (after - before) / 1000;
    const remainder = T - diff;
    check(remainder, {'reached request rate': remainder > 0});
    if (remainder > 0) {
        sleep(remainder);
    } else {
        console.warn(`Timer exhausted! The execution time of the test took longer than ${T} seconds`);
    }
};
