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
- smoke 테스트 개선 결과 http_req_duration: avg= 68.54ms -> 14.19ms
```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: .\smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 2 max VUs, 40s max duration (incl. graceful stop):
           * default: 2 looping VUs for 10s (gracefulStop: 30s)


running (10.6s), 0/2 VUs, 20 complete and 0 interrupted iterations
default ✓ [======================================] 2 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path

     checks.........................: 100.00% ✓ 60       ✗ 0
     data_received..................: 84 kB   7.9 kB/s
     data_sent......................: 10 kB   979 B/s
     http_req_blocked...............: avg=1.52ms   min=0s     med=0s       max=46.31ms p(90)=0s       p(95)=0s
     http_req_connecting............: avg=101.06µs min=0s     med=0s       max=3.03ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=14.19ms  min=7.35ms med=14.78ms  max=27.4ms  p(90)=17.89ms  p(95)=19.92ms
       { expected_response:true }...: avg=14.19ms  min=7.35ms med=14.78ms  max=27.4ms  p(90)=17.89ms  p(95)=19.92ms
     http_req_failed................: 0.00%   ✓ 0        ✗ 60
     http_req_receiving.............: avg=382.05µs min=0s     med=274.84µs max=1.11ms  p(90)=985.36µs p(95)=1ms
     http_req_sending...............: avg=305.55µs min=0s     med=0s       max=1.64ms  p(90)=849.43µs p(95)=1ms
     http_req_tls_handshaking.......: avg=1.27ms   min=0s     med=0s       max=38.3ms  p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=13.51ms  min=7.07ms med=13.91ms  max=26.74ms p(90)=17.77ms  p(95)=18.67ms
     http_reqs......................: 60      5.678202/s
     iteration_duration.............: avg=1.05s    min=1.04s  med=1.05s    max=1.09s   p(90)=1.06s    p(95)=1.09s
     iterations.....................: 20      1.892734/s
     vus............................: 2       min=2      max=2
     vus_max........................: 2       min=2      max=2
```
- load 테스트 개선 결과 http_req_duration: avg= 3.99s -> 12.03ms
```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: .\load.js
     output: -

  scenarios: (100.00%) 1 scenario, 50 max VUs, 1m5s max duration (incl. graceful stop):
           * default: Up to 50 looping VUs for 35s over 3 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m35.8s), 00/50 VUs, 1346 complete and 0 interrupted iterations
default ✓ [======================================] 00/50 VUs  35s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ find path

     checks.........................: 100.00% ✓ 4038       ✗ 0
     data_received..................: 5.3 MB  147 kB/s
     data_sent......................: 650 kB  18 kB/s
     http_req_blocked...............: avg=232.88µs min=0s     med=0s       max=50.6ms  p(90)=0s       p(95)=0s
     http_req_connecting............: avg=49.64µs  min=0s     med=0s       max=5.99ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=12.03ms  min=3.88ms med=10.48ms  max=67.77ms p(90)=19.17ms  p(95)=25.21ms
       { expected_response:true }...: avg=12.03ms  min=3.88ms med=10.48ms  max=67.77ms p(90)=19.17ms  p(95)=25.21ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 4038
     http_req_receiving.............: avg=439.67µs min=0s     med=427.35µs max=24.51ms p(90)=947.04µs p(95)=1.03ms
     http_req_sending...............: avg=118.39µs min=0s     med=0s       max=2.27ms  p(90)=530.56µs p(95)=601.92µs
     http_req_tls_handshaking.......: avg=171.01µs min=0s     med=0s       max=44.62ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=11.47ms  min=3.32ms med=9.9ms    max=66.6ms  p(90)=18.63ms  p(95)=24.52ms
     http_reqs......................: 4038    112.739208/s
     iteration_duration.............: avg=1.04s    min=1.02s  med=1.03s    max=1.13s   p(90)=1.05s    p(95)=1.06s
     iterations.....................: 1346    37.579736/s
     vus............................: 7       min=5        max=50
     vus_max........................: 50      min=50       max=50
```
- stress 테스트 개선 결과 http_req_duration: avg= 5.57s -> 10.26ms
```
          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: .\stress.js
     output: -

running (2m03.7s), 00000/10000 VUs, 26749 complete and 0 interrupted iterations
default ✓ [======================================] 00000/10000 VUs  1m40s

     ✗ logged in successfully
      ↳  81% — ✓ 21823 / ✗ 4926
     ✓ retrieved member
     ✓ find path

     checks.........................: 92.91% ✓ 64583      ✗ 4926
     data_received..................: 116 MB 933 kB/s
     data_sent......................: 15 MB  118 kB/s
     http_req_blocked...............: avg=115.48ms min=0s     med=0s     max=15.57s  p(90)=16.07ms  p(95)=61.02ms
     http_req_connecting............: avg=31.4ms   min=0s     med=0s     max=15.55s  p(90)=3.06ms   p(95)=6.42ms
   ✗ http_req_duration..............: avg=6.09s    min=0s     med=4.24s  max=1m0s    p(90)=14.94s   p(95)=20.17s
       { expected_response:true }...: avg=6.29s    min=3.73ms med=4.66s  max=28.07s  p(90)=14.79s   p(95)=19.63s
     http_req_failed................: 7.67%  ✓ 5372       ✗ 64581
     http_req_receiving.............: avg=254.47µs min=0s     med=0s     max=351.4ms p(90)=648.16µs p(95)=998.5µs
     http_req_sending...............: avg=281.43µs min=0s     med=0s     max=1.08s   p(90)=528µs    p(95)=997µs
     http_req_tls_handshaking.......: avg=83.84ms  min=0s     med=0s     max=7.49s   p(90)=11.32ms  p(95)=46.32ms
     http_req_waiting...............: avg=6.09s    min=0s     med=4.24s  max=1m0s    p(90)=14.94s   p(95)=20.17s
     http_reqs......................: 69953  565.475573/s
     iteration_duration.............: avg=20.8s    min=1.02s  med=21.29s max=1m20s   p(90)=42.04s   p(95)=44.4s
     iterations.....................: 26749  216.229556/s
     vus............................: 1      min=1        max=10000
     vus_max........................: 10000  min=10000    max=10000

ERRO[0128] some thresholds have failed
```
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Http 1.1 성능 개선 (nginx 설정)
- gzip 사용 (nginx 설정)
- http 캐싱 적용 (nginx 설정)
- application 캐싱 적용 (Redis cache 사용)
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
select salary_top_5.employee_id, e.last_name, e.first_name, p.position_name, r.time, r.region, r.record_symbol
from (
    select ed.employee_id from tuning.employee_department as ed
    inner join tuning.department as d on ed.department_id = d.id
    inner join tuning.salary as s on ed.employee_id = s.id
    where d.note = 'active' and ed.end_date > now() and s.end_date > now()
    order by s.annual_income desc limit 5
) as salary_top_5
inner join tuning.record as r on salary_top_5.employee_id = r.employee_id
inner join tuning.employee as e on salary_top_5.employee_id = e.id
inner join tuning.position as p on salary_top_5.employee_id = p.id
where r.record_symbol = 'o' and p.end_date > now();
```
---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
