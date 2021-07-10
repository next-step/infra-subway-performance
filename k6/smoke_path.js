import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';

export let options = {
    vus: 1,
    duration: '10s',

    thresholds: {
        http_req_duration: ['p(99)<1500'],
    },
};


const BASE_URL = 'https://dolilu.kro.kr';

export default function ()  {
    let pathRes = 경로_조회_요청();
    경로_조회_확인됨(pathRes, 23);

    sleep(1);
};

export function 경로_조회_요청() {
    return http.get(`${BASE_URL}/paths` + "?source=1&target=2").json();
}

export function 경로_조회_확인됨(pathRes, distance){
    check(pathRes, {
        'find path successfully': (resp) => resp['distance'] === distance,
    });
}
