import http from 'k6/http';
import {check, sleep} from 'k6';

export let options = {
    vus: 2,
    duration: '10m',
    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://www.tasklet1579.p-e.kr';
const USERNAME = 'tasklet1579@next.co.kr';
const PASSWORD = 'test1234';

export default () => {
    visit_lending_page();

    login();

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
}
