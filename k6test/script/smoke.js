import http from 'k6/http';
import {check} from 'k6';

export let options = {
    vus: 1, // 1 user looping for 10s
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<1500'], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = 'https://applemango2021.kro.kr';
const USERNAME = 'applemango2021@mail.com';
const PASSWORD = 'password';

export default function () {

    let mainResult = 메인화면_접속()
    메인화면_접속_성공_검증(mainResult)

    let loginResult = 로그인()
    로그인_성공_검증(loginResult)

    let pathResult = 경로조회()
    경로조회_성공_검증(pathResult)
}

export function 메인화면_접속() {
    return http.get(`${BASE_URL}`);
}

export function 메인화면_접속_성공_검증(mainResult) {
    check(mainResult, {'Entered main screen successfully': (res) => res.status === 200});
}

export function 로그인() {
    var payload = JSON.stringify({
        email: USERNAME,
        password: PASSWORD
    });

    var params = {
        headers: {
            'Content-Type': 'application/json'
        }
    };

    return http.post(`${BASE_URL}/login/token`, payload, params);
}

export function 로그인_성공_검증(loginResult) {
    check(loginResult, {
        'Logged in successfully': (resp) => resp.json('accessToken') !== '',
    });
}

export function 경로조회() {
    return http.get(`${BASE_URL}/paths?source=99&target=100`).json();
}

export function 경로조회_성공_검증(pathResult) {
    check(pathResult, {'Found shortest path successfully': (obj) => obj.distance === 1});
}