import http from "k6/http";
import { check, group, sleep, fail } from "k6";

export let options = {
    vus: 1,
    duration: "10s",
    thresholds: {
        http_req_duration: ["p(99)<1500"], // 99% of requests must complete below 1.5s
    },
};
//main page  조회
const mainPageSearch = () => {
    let mainpage = http.get(`${BASE_URL}`);
    check(mainpage, { "retrieved main page:": (obj) => obj.status === 200});
}

//경로 page 조회
const pathPageSearch = () => {
    let pathPage = http.get(`${BASE_URL}/path`);
    check(pathPage, { "retrieved page page:": (obj) => obj.status === 200});
}

//경로 조회 API
const pathSearch = () => {
    let pathApi = http.get(`${BASE_URL}/paths?source=1&target=2`)
    check(pathApi, {
            "Path Api success:": (res) => res.status === 200,
            "Path Api find path success:": (res) => res.json().hasOwnProperty("stations")
        }
    );
}


//노선 api 조회
const lineApiSearch = () => {
    let lineApi = http.get(`${BASE_URL}/lines`);
    check(lineApi, { "retrieved lines:": (obj) => obj.status === 200});
}

//역 api 조회
const stationsApiSearch = () => {
    let stationApi = http.get(`${BASE_URL}/stations`);
    check(stationApi, { "retrieved station page:": (obj) => obj.status === 200});
}




export default function () {
    // 메인 페이지로 이동
    mainPageSearch();
    // 경로조회 페이지 이동
    pathPageSearch();
    //노선 조회
    lineApiSearch();
    //역 조회
    stationsApiSearch();
    // 경로 조회
    pathSearch();
}
