import http from "k6/http";
import { check, group, sleep, fail } from "k6";

export let options = {
    vus: 1,
    duration: "10s",
    thresholds: {
        http_req_duration: ["p(99)<1500"], // 99% of requests must complete below 1.5s
    },
};


const BASE_URL = "https://www.kbh0581.r-e.kr";

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



export default function () {
    // 메인 페이지로 이동
    mainPageSearch();

    // 경로조회 페이지 이동
    pathPageSearch();

    // 경로 조회
    pathSearch();
}