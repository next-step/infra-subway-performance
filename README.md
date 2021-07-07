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
#### 개선전
##### smoke
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 2 max VUs, 40s max duration (incl. graceful stop):
           * default: Up to 2 looping VUs for 10s over 1 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (10.1s), 0/2 VUs, 9 complete and 0 interrupted iterations
default ✓ [======================================] 0/2 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 36       ✗ 0  
     data_received..................: 104 kB  10 kB/s
     data_sent......................: 6.0 kB  590 B/s
     http_req_blocked...............: avg=1.9ms    min=2.66µs  med=2.77µs  max=68.36ms  p(90)=2.95µs   p(95)=4.32µs  
     http_req_connecting............: avg=37.67µs  min=0s      med=0s      max=1.35ms   p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=27.94ms  min=12.93ms med=15.08ms max=74.32ms  p(90)=68.84ms  p(95)=73.91ms 
       { expected_response:true }...: avg=27.94ms  min=12.93ms med=15.08ms max=74.32ms  p(90)=68.84ms  p(95)=73.91ms 
     http_req_failed................: 0.00%   ✓ 0        ✗ 36 
     http_req_receiving.............: avg=208.62µs min=49.7µs  med=83.22µs max=883.39µs p(90)=627.07µs p(95)=685.47µs
     http_req_sending...............: avg=59.01µs  min=28.41µs med=36.5µs  max=251.43µs p(90)=109.62µs p(95)=147.96µs
     http_req_tls_handshaking.......: avg=821.65µs min=0s      med=0s      max=29.57ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=27.67ms  min=12.75ms med=14.67ms max=74.17ms  p(90)=68.73ms  p(95)=73.78ms 
     http_reqs......................: 36      3.567831/s
     iteration_duration.............: avg=1.12s    min=1.1s    med=1.11s   max=1.18s    p(90)=1.13s    p(95)=1.16s   
     iterations.....................: 9       0.891958/s
     vus............................: 1       min=1      max=1
     vus_max........................: 2       min=2      max=2
```
##### load
```
          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m0s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 30s over 1 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m33.3s), 000/300 VUs, 2159 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 8636       ✗ 0    
     data_received..................: 25 MB   755 kB/s
     data_sent......................: 1.5 MB  44 kB/s
     http_req_blocked...............: avg=242.35µs min=2.53µs  med=2.7µs    max=48.35ms p(90)=2.87µs   p(95)=3.11µs  
     http_req_connecting............: avg=47.65µs  min=0s      med=0s       max=14.14ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=331.63ms min=7.48ms  med=293.87ms max=2.83s   p(90)=627.82ms p(95)=828.58ms
       { expected_response:true }...: avg=331.63ms min=7.48ms  med=293.87ms max=2.83s   p(90)=627.82ms p(95)=828.58ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 8636 
     http_req_receiving.............: avg=205.12µs min=30.92µs med=61.51µs  max=35.03ms p(90)=268.05µs p(95)=447.35µs
     http_req_sending...............: avg=47.32µs  min=20.81µs med=34.02µs  max=4.06ms  p(90)=75.95µs  p(95)=106.16µs
     http_req_tls_handshaking.......: avg=186.52µs min=0s      med=0s       max=45.84ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=331.38ms min=7.3ms   med=293.63ms max=2.83s   p(90)=627.73ms p(95)=828.35ms
     http_reqs......................: 8636    259.457088/s
     iteration_duration.............: avg=2.32s    min=1.1s    med=2.27s    max=6s      p(90)=3.45s    p(95)=3.98s   
     iterations.....................: 2159    64.864272/s
     vus............................: 39      min=10       max=299
     vus_max........................: 300     min=300      max=300
```
##### stress
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 2m50s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 2m20s over 8 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m20.9s), 000/400 VUs, 13141 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  2m20s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 52564      ✗ 0    
     data_received..................: 146 MB  1.0 MB/s
     data_sent......................: 8.1 MB  58 kB/s
     http_req_blocked...............: avg=53.34µs  min=2.42µs  med=2.68µs   max=61.51ms p(90)=2.8µs    p(95)=2.87µs  
     http_req_connecting............: avg=9.74µs   min=0s      med=0s       max=15.87ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=328.94ms min=6.85ms  med=269.53ms max=4.74s   p(90)=658.26ms p(95)=987.23ms
       { expected_response:true }...: avg=328.94ms min=6.85ms  med=269.53ms max=4.74s   p(90)=658.26ms p(95)=987.23ms
     http_req_failed................: 0.00%   ✓ 0          ✗ 52564
     http_req_receiving.............: avg=110.49µs min=27.78µs med=55.95µs  max=27.53ms p(90)=197.6µs  p(95)=265.84µs
     http_req_sending...............: avg=40.93µs  min=18.18µs med=32.67µs  max=1.08ms  p(90)=62.92µs  p(95)=71.84µs 
     http_req_tls_handshaking.......: avg=39.1µs   min=0s      med=0s       max=29.83ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=328.79ms min=6.73ms  med=269.4ms  max=4.74s   p(90)=658.15ms p(95)=987.12ms
     http_reqs......................: 52564   373.070023/s
     iteration_duration.............: avg=2.31s    min=1.07s   med=2.22s    max=7.8s    p(90)=3.63s    p(95)=4.24s   
     iterations.....................: 13141   93.267506/s
     vus............................: 5       min=5        max=400
     vus_max........................: 400     min=400      max=400

```
#### 개선후

##### smoke
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: -

  scenarios: (100.00%) 1 scenario, 2 max VUs, 40s max duration (incl. graceful stop):
           * default: Up to 2 looping VUs for 10s over 1 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (10.4s), 0/2 VUs, 10 complete and 0 interrupted iterations
default ✓ [======================================] 0/2 VUs  10s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 40       ✗ 0  
     data_received..................: 115 kB  11 kB/s
     data_sent......................: 6.6 kB  628 B/s
     http_req_blocked...............: avg=769.72µs min=2.64µs  med=2.73µs  max=30.68ms  p(90)=2.86µs   p(95)=2.89µs  
     http_req_connecting............: avg=30.05µs  min=0s      med=0s      max=1.2ms    p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=9.63ms   min=4.61ms  med=9.56ms  max=14.55ms  p(90)=14.04ms  p(95)=14.36ms 
       { expected_response:true }...: avg=9.63ms   min=4.61ms  med=9.56ms  max=14.55ms  p(90)=14.04ms  p(95)=14.36ms 
     http_req_failed................: 0.00%   ✓ 0        ✗ 40 
     http_req_receiving.............: avg=196.93µs min=45.78µs med=67.25µs max=1.74ms   p(90)=466.28µs p(95)=657.22µs
     http_req_sending...............: avg=55.45µs  min=28.69µs med=35.16µs max=229.08µs p(90)=105.3µs  p(95)=112.53µs
     http_req_tls_handshaking.......: avg=710.32µs min=0s      med=0s      max=28.41ms  p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=9.37ms   min=4.47ms  med=9.44ms  max=14.38ms  p(90)=13.94ms  p(95)=14.26ms 
     http_reqs......................: 40      3.834418/s
     iteration_duration.............: avg=1.04s    min=1.03s   med=1.04s   max=1.06s    p(90)=1.04s    p(95)=1.05s   
     iterations.....................: 10      0.958605/s
     vus............................: 1       min=1      max=1
     vus_max........................: 2       min=2      max=2

```
##### load
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: -

  scenarios: (100.00%) 1 scenario, 300 max VUs, 1m0s max duration (incl. graceful stop):
           * default: Up to 300 looping VUs for 30s over 1 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (0m31.0s), 000/300 VUs, 4536 complete and 0 interrupted iterations
default ✓ [======================================] 000/300 VUs  30s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 18144      ✗ 0    
     data_received..................: 51 MB   1.7 MB/s
     data_sent......................: 2.9 MB  93 kB/s
     http_req_blocked...............: avg=117.31µs min=2.48µs  med=2.68µs  max=32.25ms p(90)=2.82µs   p(95)=2.92µs  
     http_req_connecting............: avg=23.23µs  min=0s      med=0s      max=13.62ms p(90)=0s       p(95)=0s      
   ✓ http_req_duration..............: avg=6.17ms   min=2.5ms   med=7.12ms  max=22.87ms p(90)=9.41ms   p(95)=10.37ms 
       { expected_response:true }...: avg=6.17ms   min=2.5ms   med=7.12ms  max=22.87ms p(90)=9.41ms   p(95)=10.37ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 18144
     http_req_receiving.............: avg=76.6µs   min=23.71µs med=52.17µs max=3.83ms  p(90)=126.05µs p(95)=197.38µs
     http_req_sending...............: avg=41.55µs  min=20.63µs med=31.23µs max=4.34ms  p(90)=63.67µs  p(95)=78.8µs  
     http_req_tls_handshaking.......: avg=88.75µs  min=0s      med=0s      max=29.76ms p(90)=0s       p(95)=0s      
     http_req_waiting...............: avg=6.05ms   min=2.41ms  med=6.99ms  max=22.78ms p(90)=9.3ms    p(95)=10.27ms 
     http_reqs......................: 18144   584.668848/s
     iteration_duration.............: avg=1.02s    min=1.02s   med=1.02s   max=1.06s   p(90)=1.03s    p(95)=1.03s   
     iterations.....................: 4536    146.167212/s
     vus............................: 13      min=10       max=299
     vus_max........................: 300     min=300      max=300
```
##### stress
```

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: stress.js
     output: -

  scenarios: (100.00%) 1 scenario, 400 max VUs, 2m50s max duration (incl. graceful stop):
           * default: Up to 400 looping VUs for 2m20s over 8 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (2m20.8s), 000/400 VUs, 29420 complete and 0 interrupted iterations
default ✓ [======================================] 000/400 VUs  2m20s

     ✓ logged in successfully
     ✓ retrieved member
     ✓ lines success!!
     ✓ get shortest line success

   ✓ checks.........................: 100.00% ✓ 117680     ✗ 0     
     data_received..................: 326 MB  2.3 MB/s
     data_sent......................: 18 MB   127 kB/s
     http_req_blocked...............: avg=26.14µs min=2.31µs  med=2.66µs  max=77.25ms  p(90)=2.79µs  p(95)=2.86µs  
     http_req_connecting............: avg=4.54µs  min=0s      med=0s      max=16.59ms  p(90)=0s      p(95)=0s      
   ✓ http_req_duration..............: avg=6.35ms  min=2.39ms  med=7.1ms   max=217.85ms p(90)=9.67ms  p(95)=11.26ms 
       { expected_response:true }...: avg=6.35ms  min=2.39ms  med=7.1ms   max=217.85ms p(90)=9.67ms  p(95)=11.26ms 
     http_req_failed................: 0.00%   ✓ 0          ✗ 117680
     http_req_receiving.............: avg=78.64µs min=23.51µs med=50.31µs max=15.56ms  p(90)=128.1µs p(95)=195.71µs
     http_req_sending...............: avg=40.74µs min=17.09µs med=31.39µs max=9.27ms   p(90)=60.88µs p(95)=72.8µs  
     http_req_tls_handshaking.......: avg=17.92µs min=0s      med=0s      max=41.87ms  p(90)=0s      p(95)=0s      
     http_req_waiting...............: avg=6.23ms  min=2.31ms  med=6.99ms  max=217.78ms p(90)=9.57ms  p(95)=11.15ms 
     http_reqs......................: 117680  835.913286/s
     iteration_duration.............: avg=1.02s   min=1.01s   med=1.02s   max=1.23s    p(90)=1.03s   p(95)=1.04s   
     iterations.....................: 29420   208.978321/s
     vus............................: 7       min=7        max=399 
     vus_max........................: 400     min=400      max=400 
```
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
Nginx에서는 Cache적용 및 GZip 압축을 적용을 했습니다. 하지만 이것으로는 큰 성능 개선이 진행은 않되었습니다.  
그래서 Tomcat의 Worker Thread도 늘려보는 진행도 했으나, 이것도 효과를 보지는 못했습니다. (nginx에서 event connections 512개가 기본인데, 이것을 넘어 Error가 발생하므로, Tomcat의 Thread가 부족할것이라 판단)  
결국 Cache를 사용하기로 했고, Redis가 아닌 Local Cache인 EhCache를 적용도 고민을 해봤으나, WAS가 여러개 뜨면 동기화 이슈가 있기 때문에 Redis를 선택했습니다.
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
#### 인덱스 이해하기
간단하게 PK만 잡아주고, FK 대신 Index를 잡아주니 다 통과를 합니다..

##### Coding as a Hobby
변수를 사용하지 않고 바로 가능하지만, 조금 더 빠른 시간을 내기위해 작업해줬습니다.
``` sql
ALTER TABLE `subway`.`programmer` ADD INDEX `hobby` (`hobby` ASC);

set @a := (select count(1) as cnt from programmer);
select	hobby
	,	count(1) / @a
from	programmer
group by hobby;
```
#### 프로그래머별로 해당 하는 병원 이름 반환
문제가 잘 이해가 안되서, covid.id, hospital.name을 리턴하도록 했습니다. covid.programmer_id를 사용할 수 있어서, programmer랑 조인이 필요는 없어 보입니다.
```sql
ALTER TABLE `subway`.`hospital` CHANGE COLUMN `id` `id` INT(11) NOT NULL ,ADD PRIMARY KEY (`id`);

select	cov.id, hos.name
from	covid	as cov
        join hospital	as hos
             on cov.hospital_id = hos.id;
```
#### 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요
```sql
alter table programmer add constraint programmer_pk primary key (id);
select  cov.id
     ,   hos.name
     ,   pro.hobby
     ,   pro.dev_type
     ,   pro.years_coding
from    covid as cov
            join programmer  as pro
                 on cov.programmer_id = pro.id
            join  hospital as hos
                  on cov.hospital_id = hos.id
where   (hobby = 'Yes' AND dev_type = '%student%') # like를 쓸 수 밖에 없는 데이터 규격들..ㅜㅜ
   or years_coding = '0-2 years' ;
```
#### 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
```sql
alter table covid add constraint covid_pk primary key (id);
create index covid_index on covid (hospital_id, programmer_id, member_id, stay);
select  cov.stay
     ,  count(1)
from    hospital hos
            join covid as cov
                 on cov.hospital_id = hos.id
            join programmer pro
                 on cov.programmer_id = pro.id
where   hos.id = 9
  and   country = 'India'
group by cov.stay;
```
#### 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
위에 잡힌 인덱스로 충분이 100ms 언더로 잡힘.
```sql
select  pro.exercise
    ,   count(1)
from    hospital hos
            join covid as cov
                 on cov.hospital_id = hos.id
            join programmer pro
                 on cov.programmer_id = pro.id
            join member mem
                on cov.member_id = mem.id
where   hos.id = 9
    and mem.age between 30 and 39
group by pro.exercise;
```
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

