import http from 'k6/http';
import {check, group, sleep, fail} from 'k6';
import {randomIntBetween} from 'https://jslib.k6.io/k6-utils/1.1.0/index.js';


const HOST = {
    PROTOCOL : "http",
    URL : "3.36.233.76",
    PORT : "8080",
    USERNAME : "haedoang@gmail.com",
    PASSWORD : "11"
}

// smoke options
// export let options = {
//   vus: 1, // 1 user looping for 1 minute
//   duration: '10s',
//
//   thresholds: {
//     http_req_duration: ['p(99) < 100'], // 99% of requests must complete below 1s
//   },
// };

// load options
export let options = {
  stages: [
    { duration: '30s', target: 8 },
    { duration: '30s', target: 88 },
    { duration: '15s', target: 100 },
  ],
  thresholds: {
    http_req_duration: ['p(99)<100'],
  },
};


// stress
// export let options = {
//     vus: 150, // 1 user looping for 1 minute
//     duration: '10s',
//     thresholds: {
//         http_req_duration: ['p(99)<200'], // 99% of requests must complete below 1.5s
//     },
// };

const BASE_URL = `${HOST.PROTOCOL}://${HOST.URL}:${HOST.PORT}`;

export default function () {
    // 메인페이지 접속
    let response = http.get(`${BASE_URL}`);
    check(response, {
        'response successfully': (response) => response.status === 200
    });

    //경로 조회 response
    let response2 = http.get(`${BASE_URL}/paths?source=1&target=2`).json();
    check(response2, {
        'get Paths in successfully': (resp) => response.status === 200
    });
};
