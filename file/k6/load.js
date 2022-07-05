import http from 'k6/http';
import {check, sleep} from 'k6';
import { URL } from 'https://jslib.k6.io/url/1.0.0/index.js';

export let options = {
    stages: [
        { duration: '3m', target: 31 },
        { duration: '3m', target: 61 },
        { duration: '3m', target: 61 },
        { duration: '3m', target: 61 },
        { duration: '3m', target: 61 },
        { duration: '3m', target: 0 },
    ],
    thresholds: {
        http_req_duration: ['p(80)<1000'], // 80% of requests must complete below 1.0s
    },
};

const BASE_URL = 'https://www.tasklet1579.p-e.kr';
const USERNAME = 'tasklet1579@next.co.kr';
const PASSWORD = 'test1234';

export default () => {
    visit_lending_page();

    let access_token = login();

    find_stations();

    find_path();

    add_my_favorite(access_token);

    sleep(1);
};

function visit_lending_page() {
    let homeUrl = `${BASE_URL}`;
    let response = http.get(homeUrl);

    check(response, {
        '랜딩 페이지 점검': (response) => response.status === 200
    });
}

function login() {
    var body = JSON.stringify({
        email: USERNAME,
        password: PASSWORD,
    });

    var headers = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let response = http.post(`${BASE_URL}/login/token`, body, headers);

    check(response, {
        '로그인 토큰 점검': (response) => response.json('accessToken') !== '',
    });

    return response.json('accessToken');
}

function find_stations() {
    let stationsUrl = `${BASE_URL}/stations`;
    let response = http.get(stationsUrl);

    check(response, {
        '지하철역 개수 점검': (response) => response.body.length > 1
    });
}

function find_path() {
    let findPathUrl = new URL(`${BASE_URL}/paths`);
    findPathUrl.searchParams.append('source', 1);
    findPathUrl.searchParams.append('target', 15);

    let response = http.get(findPathUrl.toString());

    check(response, {
        '지하철 길찾기 점검': (response) => response.json('stations').length > 1
    });
}

function add_my_favorite(access_token) {
    var body = JSON.stringify({
        source: 1,
        target: 15,
    });

    let headers = {
        headers: {
            Authorization: `Bearer ${access_token}`,
            'Content-Type': 'application/json',
        },
    };

    let response = http.post(`${BASE_URL}/favorites`, body, headers);

    check(response, {
        '지하철 즐겨찾기 점검': (response) => response.status === 201
    });
}
