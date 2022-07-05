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
- 이전 미션 성능테스트 결과 및 전제조건
  - https://github.com/next-step/infra-subway-monitoring/tree/ssamzag
- ### 웹 성능 테스트 
|      | FCP  | TTI  | SI   | TBT  | LCP |CLS|
|------|------|------|------|------|---|---|
| 개선 전 | 3.1s | 3.1s | 3.0s | 3.0s |10ms|0|
| 개선 후 | 1.3s | 1.3s | 1.5s | 1.3s |0ms|0|

- ### k6 테스트 전/후
- Smoke
  - 개선 전
  ```
           /\      |‾‾| /‾‾/   /‾‾/
      /\  /  \     |  |/  /   /  /
     /  \/    \    |     (   /   ‾‾\
    /          \   |  |\  \ |  (‾)  |
   / __________ \  |__| \__\ \_____/ .io
    
   execution: local
      script: smoke.js
      output: -
    
   scenarios: (100.00%) 1 scenario, 1 max VUs, 1m0s max duration (incl. graceful stop):
            * default: 1 looping VUs for 30s (gracefulStop: 30s)
    
    
  running (0m33.1s), 0/1 VUs, 10 complete and 0 interrupted iterations
  default ✓ [======================================] 1 VUs  30s
    
      ✓ Main Page
      ✓ Login Page
      ✓ logged in successfully
      ✓ Path Page
      ✓ Request Path
    
      checks.........................: 100.00% ✓ 50       ✗ 0
      data_received..................: 77 kB   2.3 kB/s
      data_sent......................: 7.4 kB  222 B/s
      http_req_blocked...............: avg=836.44µs min=2µs    med=7µs    max=41.48ms  p(90)=10.1µs   p(95)=11µs
      http_req_connecting............: avg=136.92µs min=0s     med=0s     max=6.84ms   p(90)=0s       p(95)=0s
    ✓ http_req_duration..............: avg=60.16ms  min=7.47ms med=9.6ms  max=473.65ms p(90)=190.13ms p(95)=274.07ms
        { expected_response:true }...: avg=68.42ms  min=7.47ms med=9.29ms max=473.65ms p(90)=193.84ms p(95)=321.54ms
      http_req_failed................: 20.00%  ✓ 10       ✗ 40
      http_req_receiving.............: avg=93.58µs  min=45µs   med=92.5µs max=157µs    p(90)=123.3µs  p(95)=127.65µs
      http_req_sending...............: avg=37.86µs  min=7µs    med=31.5µs max=235µs    p(90)=52.1µs   p(95)=57.94µs
      http_req_tls_handshaking.......: avg=489.22µs min=0s     med=0s     max=24.46ms  p(90)=0s       p(95)=0s
      http_req_waiting...............: avg=60.03ms  min=7.31ms med=9.48ms max=473.49ms p(90)=189.98ms p(95)=273.92ms
      http_reqs......................: 50      1.510712/s
      iteration_duration.............: avg=3.3s     min=3.18s  med=3.25s  max=3.57s    p(90)=3.44s    p(95)=3.5s
      iterations.....................: 10      0.302142/s
      vus............................: 1       min=1      max=1
      vus_max........................: 1       min=1      max=1
  ```
  - 개선 후
  ```
            /\      |‾‾| /‾‾/   /‾‾/
       /\  /  \     |  |/  /   /  /
      /  \/    \    |     (   /   ‾‾\
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
       script: smoke.js
       output: InfluxDBv1 (http://52.78.136.247:8086)
    
    scenarios: (100.00%) 1 scenario, 1 max VUs, 1m0s max duration (incl. graceful stop):
             * default: 1 looping VUs for 30s (gracefulStop: 30s)
    
    
  running (0m33.7s), 0/1 VUs, 8 complete and 0 interrupted iterations
  default ✓ [======================================] 1 VUs  30s
    
       ✓ mainPage
       ✓ loginPage
       ✓ logged in successfully
       ✓ retrieved member
       ✓ pathPage
       ✓ requestPath
    
       checks.........................: 100.00% ✓ 48       ✗ 0
       data_received..................: 61 kB   1.8 kB/s
       data_sent......................: 4.2 kB  126 B/s
       http_req_blocked...............: avg=1.61ms   min=0s      med=1µs     max=77.52ms p(90)=2µs     p(95)=2µs
       http_req_connecting............: avg=455.93µs min=0s      med=0s      max=21.88ms p(90)=0s      p(95)=0s
     ✓ http_req_duration..............: avg=31.99ms  min=10.63ms med=18.91ms max=325.5ms p(90)=44.97ms p(95)=74.86ms
         { expected_response:true }...: avg=31.99ms  min=10.63ms med=18.91ms max=325.5ms p(90)=44.97ms p(95)=74.86ms
       http_req_failed................: 0.00%   ✓ 0        ✗ 48
       http_req_receiving.............: avg=82.45µs  min=29µs    med=79.5µs  max=135µs   p(90)=104.7µs p(95)=113.3µs
       http_req_sending...............: avg=235.33µs min=42µs    med=258.5µs max=852µs   p(90)=301.7µs p(95)=355.04µs
       http_req_tls_handshaking.......: avg=847.33µs min=0s      med=0s      max=40.67ms p(90)=0s      p(95)=0s
       http_req_waiting...............: avg=31.67ms  min=10.4ms  med=18.57ms max=325.1ms p(90)=44.69ms p(95)=74.67ms
       http_reqs......................: 48      1.426085/s
       iteration_duration.............: avg=4.2s     min=4.11s   med=4.12s   max=4.74s   p(90)=4.33s   p(95)=4.53s
       iterations.....................: 8       0.237681/s
       vus............................: 1       min=1      max=1
       vus_max........................: 1       min=1      max=1
  ```
- Load
  - 개선 전
  ```
            /\      |‾‾| /‾‾/   /‾‾/
       /\  /  \     |  |/  /   /  /
      /  \/    \    |     (   /   ‾‾\
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
       script: load.js
       output: InfluxDBv1 (http://52.78.136.247:8086)
    
    scenarios: (100.00%) 1 scenario, 12 max VUs, 41m30s max duration (incl. graceful stop):
             * default: Up to 12 looping VUs for 41m0s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
  running (41m02.9s), 00/12 VUs, 2796 complete and 0 interrupted iterations
  default ✓ [======================================] 00/12 VUs  41m0s
    
       ✓ mainPage
       ✓ loginPage
       ✓ logged in successfully
       ✓ pathPage
       ✓ requestPath
    
       checks.........................: 100.00% ✓ 13980    ✗ 0
       data_received..................: 20 MB   8.3 kB/s
       data_sent......................: 2.0 MB  795 B/s
       http_req_blocked...............: avg=100.59µs min=0s     med=6µs    max=185.8ms  p(90)=9µs      p(95)=10µs
       http_req_connecting............: avg=35.13µs  min=0s     med=0s     max=153.16ms p(90)=0s       p(95)=0s
     ✗ http_req_duration..............: avg=63.57ms  min=6.23ms med=9.91ms max=2.41s    p(90)=134.34ms p(95)=416.69ms
         { expected_response:true }...: avg=74.42ms  min=6.23ms med=9.7ms  max=2.41s    p(90)=187.23ms p(95)=529.65ms
       http_req_failed................: 20.00%  ✓ 2796     ✗ 11184
       http_req_receiving.............: avg=94.8µs   min=9µs    med=88µs   max=5.77ms   p(90)=132µs    p(95)=160µs
       http_req_sending...............: avg=31.33µs  min=2µs    med=28µs   max=1.18ms   p(90)=45µs     p(95)=51µs
       http_req_tls_handshaking.......: avg=48.65µs  min=0s     med=0s     max=176.05ms p(90)=0s       p(95)=0s
       http_req_waiting...............: avg=63.45ms  min=6.15ms med=9.79ms max=2.41s    p(90)=134.2ms  p(95)=416.58ms
       http_reqs......................: 13980   5.676327/s
       iteration_duration.............: avg=3.32s    min=3.09s  med=3.18s  max=5.52s    p(90)=3.67s    p(95)=3.72s
       iterations.....................: 2796    1.135265/s
       vus............................: 1       min=1      max=12
       vus_max........................: 12      min=12     max=12
  ```
- 개선 후
  ```
            /\      |‾‾| /‾‾/   /‾‾/
       /\  /  \     |  |/  /   /  /
      /  \/    \    |     (   /   ‾‾\
     /          \   |  |\  \ |  (‾)  |
    / __________ \  |__| \__\ \_____/ .io
    
    execution: local
       script: load.js
       output: InfluxDBv1 (http://52.78.136.247:8086)
    
    scenarios: (100.00%) 1 scenario, 12 max VUs, 41m30s max duration (incl. graceful stop):
             * default: Up to 12 looping VUs for 41m0s over 7 stages (gracefulRampDown: 30s, gracefulStop: 30s)
    
    
  running (41m03.2s), 00/12 VUs, 2218 complete and 0 interrupted iterations
  default ✓ [======================================] 00/12 VUs  41m0s
    
       ✓ mainPage
       ✓ loginPage
       ✓ logged in successfully
       ✓ retrieved member
       ✓ pathPage
       ✓ requestPath
    
       checks.........................: 100.00% ✓ 13308    ✗ 0
       data_received..................: 16 MB   6.4 kB/s
       data_sent......................: 1.0 MB  417 B/s
       http_req_blocked...............: avg=113.18µs min=0s     med=1µs     max=402.02ms p(90)=2µs     p(95)=2µs
       http_req_connecting............: avg=43.69µs  min=0s     med=0s      max=193.55ms p(90)=0s      p(95)=0s
     ✗ http_req_duration..............: avg=31.02ms  min=6.44ms med=20.46ms max=577.3ms  p(90)=51.1ms  p(95)=60.7ms
         { expected_response:true }...: avg=31.02ms  min=6.44ms med=20.46ms max=577.3ms  p(90)=51.1ms  p(95)=60.7ms
       http_req_failed................: 0.00%   ✓ 0        ✗ 13308
       http_req_receiving.............: avg=74.49µs  min=10µs   med=63µs    max=7.36ms   p(90)=99µs    p(95)=112µs
       http_req_sending...............: avg=192.07µs min=23µs   med=158µs   max=6.32ms   p(90)=304µs   p(95)=360µs
       http_req_tls_handshaking.......: avg=59.21µs  min=0s     med=0s      max=351.1ms  p(90)=0s      p(95)=0s
       http_req_waiting...............: avg=30.75ms  min=2.91ms med=20.23ms max=577.17ms p(90)=50.77ms p(95)=60.32ms
       http_reqs......................: 13308   5.402782/s
       iteration_duration.............: avg=4.19s    min=4.06s  med=4.19s   max=4.95s    p(90)=4.25s   p(95)=4.39s
       iterations.....................: 2218    0.900464/s
       vus............................: 1       min=1      max=12
       vus_max........................: 12      min=12     max=12
  ```

- Stress
  - 개선 전
  ```
  running (38m14.2s), 000/500 VUs, 767394 complete and 0 interrupted iterations
  default ✓ [======================================] 000/500 VUs  38m0s
    
       ✗ mainPage
        ↳  4% — ✓ 31228 / ✗ 736166
       ✗ loginPage
        ↳  4% — ✓ 31390 / ✗ 736004
       ✗ logged in successfully
        ↳  4% — ✓ 31498 / ✗ 735896
       ✓ retrieved member
       ✗ pathPage
        ↳  99% — ✓ 31253 / ✗ 225
       ✗ requestPath
        ↳  99% — ✓ 31203 / ✗ 275
    
       checks.........................: 7.84%   ✓ 188050      ✗ 2208566
       data_received..................: 250 MB  109 kB/s
       data_sent......................: 33 MB   14 kB/s
       http_req_blocked...............: avg=71.44µs  min=0s      med=0s       max=1.9s    p(90)=0s      p(95)=3µs
       http_req_connecting............: avg=18.88µs  min=0s      med=0s       max=1.21s   p(90)=0s      p(95)=0s
     ✗ http_req_duration..............: avg=98.89ms  min=0s      med=0s       max=40.94s  p(90)=0s      p(95)=18.06ms
         { expected_response:true }...: avg=1.26s    min=5.18ms  med=58.11ms  max=40.94s  p(90)=4.55s   p(95)=6.3s
       http_req_failed................: 92.15%  ✓ 2208586     ✗ 188050
       http_req_receiving.............: avg=5.16µs   min=0s      med=0s       max=26.31ms p(90)=0s      p(95)=41µs
       http_req_sending...............: avg=29.28µs  min=0s      med=0s       max=30.07s  p(90)=0s      p(95)=14µs
       http_req_tls_handshaking.......: avg=40.72µs  min=0s      med=0s       max=1.89s   p(90)=0s      p(95)=0s
       http_req_waiting...............: avg=98.86ms  min=0s      med=0s       max=30.05s  p(90)=0s      p(95)=17.86ms
       http_reqs......................: 2396636 1044.636222/s
       iteration_duration.............: avg=446.55ms min=95.95µs med=103.59ms max=51.83s  p(90)=185.1ms p(95)=228.43ms
       iterations.....................: 767394  334.488662/s
       vus............................: 13      min=1         max=500
       vus_max........................: 500     min=500       max=500
  ```
  - 개선 후
  ```
  ✗ mainPage
  ↳  9% — ✓ 217151 / ✗ 2053276
  ✗ loginPage
  ↳  9% — ✓ 217283 / ✗ 2053144
  ✗ logged in successfully
  ↳  9% — ✓ 217318 / ✗ 2053109
  ✓ retrieved member
  ✗ pathPage
  ↳  99% — ✓ 217114 / ✗ 203
  ✗ requestPath
  ↳  99% — ✓ 217060 / ✗ 257
  
  checks.........................: 17.46%  ✓ 1303243    ✗ 6159989
  data_received..................: 1.5 GB  675 kB/s
  data_sent......................: 100 MB  44 kB/s
  http_req_blocked...............: avg=10.74µs  min=0s      med=0s      max=834.75ms p(90)=0s       p(95)=1µs
  http_req_connecting............: avg=2.72µs   min=0s      med=0s      max=197.46ms p(90)=0s       p(95)=0s
  ✓ http_req_duration..............: avg=6.88ms   min=0s      med=0s      max=27.87s   p(90)=21.74ms  p(95)=37ms
  { expected_response:true }...: avg=39.4ms   min=5.41ms  med=25ms    max=27.87s   p(90)=58.55ms  p(95)=88.15ms
  http_req_failed................: 82.53%  ✓ 6159990    ✗ 1303243
  http_req_receiving.............: avg=41.27µs  min=0s      med=0s      max=4.74s    p(90)=28µs     p(95)=51µs
  http_req_sending...............: avg=13.89µs  min=0s      med=0s      max=32.71ms  p(90)=45µs     p(95)=78µs
  http_req_tls_handshaking.......: avg=7.87µs   min=0s      med=0s      max=824.97ms p(90)=0s       p(95)=0s
  http_req_waiting...............: avg=6.82ms   min=0s      med=0s      max=27.87s   p(90)=21.53ms  p(95)=36.77ms
  http_reqs......................: 7463233 3271.74627/s
  iteration_duration.............: avg=150.48ms min=86.29µs med=35.25ms max=29.39s   p(90)=158.81ms p(95)=1.18s
  iterations.....................: 2270427 995.314104/s
  vus............................: 37      min=1        max=500
  vus_max........................: 500     min=500      max=500
  ```
- ### 그라파나 대시보드
- Smoke
![img_3.png](img_3.png)
- Load
![img_1.png](img_1.png)
- Stress
![img_2.png](img_2.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- nginx 프록시 개선
  - gzip 설정
  - cache 설정
  - http/2 적용
- was 서버 redis cache 적용
---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
    ```
  select s.사원번호, s.이름, g.연봉, j.직급명, sr.입출입시간, sr.지역, sr.입출입구분
  from (select g.사원번호, g.연봉
        from tuning.급여 g
        inner join tuning.부서관리자 bm on g.사원번호 = bm.사원번호
        where bm.종료일자 = '9999-01-01'
        and g.종료일자 = '9999-01-01'
        order by 연봉 desc
        limit 5) as g
  inner join (select 사원번호, 이름 from tuning.사원) as s on s.사원번호 = g.사원번호
  inner join (select 사원번호, 입출입시간, 지역, 입출입구분 from tuning.사원출입기록 where 입출입구분 = 'O') as sr on s.사원번호 = sr.사원번호
  inner join (select 사원번호, 직급명 from tuning.직급 where 종료일자 = '9999-01-01') as j on j.사원번호 = s.사원번호
    ```
### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요.
   - 적용 인덱스
     ```
     
     -- subway.member
     ALTER TABLE subway.member add constraint PK_MEMBER__ID primary key (`id`);
     CREATE INDEX `IDX_MEMBER__AGE` ON subway.member (`age`);
     
     
     -- subway.covid     
     ALTER TABLE subway.covid add constraint PK_COVID__ID primary key (`id`);
     CREATE INDEX `idx_covid_programmer_id` ON subway.covid (`programmer_id`);
     CREATE INDEX `idx_covid_member_id` ON subway.covid  (`member_id`);
     CREATE INDEX `idx_covid_hospital_id` ON subway.covid  (`hospital_id`);
  
     -- subway.programmer
     ALTER TABLE subway.programmer add constraint PK_PROGRAMMER__ID primary key (`id`); 
     CREATE INDEX `IDX_PROGRAMMER__MEMBER_ID` ON subway.programmer (`member_id`);
     CREATE INDEX `IDX_PROGRAMMER__COUNTRY` ON subway.programmer (`country`);
     CREATE INDEX `idx_programmer_hobby_student_years_coding` on subway.programmer (`hobby`,`student`,`years_coding`)  
     
  
     ALTER TABLE subway.hospital add constraint PK_HOSPITAL__ID primary key (`id`);
     CREATE INDEX `idx_hospital_name_id` ON subway.hospital (`name`,`id`)
     ```
   - Coding as a Hobby 와 같은 결과를 반환하세요. 
     - hobby 인덱스 삭제하고 idx_programmer_hobby_student_years_coding 를 태웠습니다 
         ```
         select concat(round(count(case when hobby = 'Yes' then 1 end) / count(*) * 100, 1), '%') yes,
         concat(round(count(case when hobby = 'No' then 1 end) / count(*) * 100, 1), '%') No
         from subway.programmer;
         ```
  
   - 프로그래머별로 해당하는 병원 이름을 반환하세요.
     - programmer id로 Range Index 를 태웠습니다. 
     - 페이징 쿼리 적용하여 Fetch를 줄였습니다.
       ```
       select c.id, h.name
       from (select id, hospital_id, programmer_id from subway.covid) c     
       inner join (select id from subway.programmer) p
       on c.programmer_id = p.id
       inner join (select id, name from subway.hospital) h 
       on c.hospital_id = h.id
       where p.id >= 0
       limit 0, 1000;
       ```

   - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
      - 이전 작업은 (취미 and 학생) or (주니어 and no 학생) 조건으로 진행 했었습니다.
      - 다른 곳에 피드백하신 쿼리조건 참고하여 다시 수정하였습니다.
      - 한국어 참 어렵네요..;;
      - hobby, student, years_coding 복합 인덱스 적용
      - 페이징쿼리 적용
        ```
        select c.id, h.name, p.hobby, p.dev_type, p.years_coding, student
        from (select id, hobby, student, dev_type, years_coding from subway.programmer
              where hobby = 'Yes' and (years_coding = '0-2 years' or student like 'Yes%')) p
        inner join (select id, programmer_id, hospital_id from subway.covid) c
        on p.id = c.programmer_id
        inner join (select id, name from subway.hospital) h
        on h.id = c.hospital_id  
        order by p.id 0, limit 100;
        ```

   - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
     - File Sort 제거하였습니다.
         ```
        select c.stay, count(*) count
        from (select id from subway.member where age between 20 and 29)  m
        inner join (select id from subway.programmer where country = 'India') p 
        on m.id = p.id
        inner join (select id, hospital_id, stay from subway.covid) c 
        on p.id = c.id
        inner join (select id from subway.hospital where id = 9) h 
        on h.id = c.hospital_id
        group by c.stay
        order by null;
         ```
  
   - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
     - File Sort 제거하였습니다. 
         ```
       select p.exercise, count(*) count
       from (select id, member_id, hospital_id, programmer_id from subway.covid) c   
       inner join (select id from subway.hospital where id = 9) h
       on c.hospital_id = h.id
       inner join (select id, exercise from subway.programmer) p
       on c.programmer_id  = p.id
       inner join (select id, age from subway.member where age between 30 and 39) m
       on c.member_id = m.id
       group by p.exercise
       order by null;
         ```
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
