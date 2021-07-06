<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 인프라공방 샘플 서비스 - 지하철 노선도

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew clean build
```
<br>

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
    ```javascript
    - 공통 테스트 조건
        - 실패율은 0% 인 경우만 기록
        - 요청의 99%가 1.5초 이내에 완료되어야 함
        - 측정구분값은 최대 가상유저수(vus)와 요청당 평균처리시간(http_req_duration)으로 함
    ```

    - 접속 빈도가 높은 페이지 (로그인 요청)

      <table>
      <thead>
      <tr>
          <th>시나리오</th>
          <th>구분</th>
          <th>개선전</th>
          <th>개선후 (동일조건)</th>
          <th>개선후 (vus증가)</th>
          <th>개선후 (was+1)</th>
      </tr>
      </thead>
      <tbody>
      <tr>
          <td rowspan="2">Smoke</td>
          <td>max vus</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>14.34ms</td>
          <td>11.04ms</td>
          <td>14.46ms</td>
          <td>9.47ms</td>
      </tr>
      <tr>
          <td rowspan="2">Load</td>
          <td>max vus</td>
          <td>200</td>
          <td>200</td>
          <td>300</td>
          <td>300</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>74.41ms</td>
          <td>81.44ms</td>
          <td>135.59ms</td>
          <td>85.34ms</td>
      </tr>
      <tr>
          <td rowspan="2">Stress</td>
          <td>max vus</td>
          <td>250</td>
          <td>250</td>
          <td>500</td>
          <td>500</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>504ms</td>
          <td>158.66ms</td>
          <td>317.51ms</td>
          <td>219.28ms</td>
      </tr>
      </tbody>
      </table>

   - 데이터를 갱신하는 페이지 (회원가입을 요청)

      <table>
      <thead>
      <tr>
          <th>시나리오</th>
          <th>구분</th>
          <th>개선전</th>
          <th>개선후 (동일조건)</th>
          <th>개선후 (vus증가)</th>
          <th>개선후 (was+1)</th>
      </tr>
      </thead>
      <tbody>
      <tr>
          <td rowspan="2">Smoke</td>
          <td>max vus</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>16.05ms</td>
          <td>11.32ms</td>
          <td>11.94ms</td>
          <td>12.09ms</td>
      </tr>
      <tr>
          <td rowspan="2">Load</td>
          <td>max vus</td>
          <td>200</td>
          <td>200</td>
          <td>300</td>
          <td>300</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>80.41ms</td>
          <td>80.3ms</td>
          <td>106.2ms</td>
          <td>71.72ms</td>
      </tr>
      <tr>
          <td rowspan="2">Stress</td>
          <td>max vus</td>
          <td>250</td>
          <td>250</td>
          <td>500</td>
          <td>500</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>153.63ms</td>
          <td>151.29</td>
          <td>247.2ms</td>
          <td>174.65ms</td>
      </tr>
      </tbody>
      </table>

   - 데이터를 조회하는데 여러 데이터를 참조하는 페이지 (로그인 후 경로를 검색)

      <table>
      <thead>
      <tr>
          <th>시나리오</th>
          <th>구분</th>
          <th>개선전</th>
          <th>개선후 (동일조건)</th>
          <th>개선후 (vus증가)</th>
          <th>개선후 (was+1)</th>
      </tr>
      </thead>
      <tbody>
      <tr>
          <td rowspan="2">Smoke</td>
          <td>max vus</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>27.07ms</td>
          <td>17.36ms</td>
          <td>18.25ms</td>
          <td>11.84ms</td>
      </tr>
      <tr>
          <td rowspan="2">Load</td>
          <td>max vus</td>
          <td>200</td>
          <td>200</td>
          <td>300</td>
          <td>300</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>266.66ms</td>
          <td>242.73ms</td>
          <td>209.74ms</td>
          <td>106.03ms</td>
      </tr>
      <tr>
          <td rowspan="2">Stress</td>
          <td>max vus</td>
          <td>250</td>
          <td>250</td>
          <td>500</td>
          <td>500</td>
      </tr>
      <tr>
          <td>http_req_duration</td>
          <td>557.94ms</td>
          <td>543.63ms</td>
          <td>511.72ms</td>
          <td>243.14ms</td>
      </tr>
      </tbody>
      </table>

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

   - 정적파일 경량화 (css를 main.js로 통합)
   - 렌더링 차단 리소스 제거하기 (js async 적용)
   - nginx : gzip 압축 적용
   - nginx : worker 옵션 변경
      - worker_processes : 1
      - worker_connections : 1024
   - nginx : http2 적용
   - was : 경로 찾기 캐시 적용 (redis)
   - was 인스턴스 추가

   ```javascript
      (개선후 PageSpeed 측정값)
      - First Contentful Paint : 14.8 -> 1.7
      - Time to Interactive : 15.8 -> 5.3
      - Speed Index : 14.8 -> 3.7
      - Total Blocking Time : 960 -> 840
      - Largest Contentful Paint : 15.9 -> 5.3
   ```

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

