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
- 리소스 부하가 큰 최적 경로 페이지 캐싱 적용해보기
  - caching 미적용
    - [smoke 결과](/k6/path/path_smoke.PNG)
    - [load 결과](/k6/path/path_load.PNG)
    - [stress 결과](/k6/path/path_stress.PNG)
  - caching 적용
    - [smoke 결과](/k6/path/redis_path_smoke.PNG)
    - [load 결과](/k6/path/redis_path_load.PNG)
    - [stress 결과](/k6/path/redis_path_stress.PNG)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요   
기존에 캐시를 미적용 하였을땐 stress 테스트에서 99프로 이상을 통과하지 못했던 상황이 생겼습니다.   
그래서 이 부분에 캐싱을 적용하면 눈에 띄는 성능이 나타날 것이라고 예상하여   
캐싱을 적용하였더니 100프로 통과하는 모습을 보여줬으며 지표 또한 눈에 띄는 결과를 나타내었습니다.   

- 평균값 지표

|http_지표|reqs|blocked|connecting|tls_handshaking|sending|waiting|receiving|duration|
|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|
|캐시 미적용|6170|413ps|345ps|0ps|76ps|1.5s|90ps|2.5s|
|캐시 적용|15108|49ps|26ps|0ps|71ps|4.3ms|135ps|1s|

- 지표 설명
  - http_reqs — 총 요청 수
  - http_req_blocked — 요청을 시작하기 전에 사용 가능한 TCP 연결을 기다리는 데 소요 된 시간
  - http_req_connecting — TCP 연결 설정에 소요 된 시간
  - http_req_tls_handshaking — TLS 핸드 쉐이킹에 소요 된 시간
  - http_req_sending — 데이터 전송에 소요 된 시간
  - http_req_waiting — 원격 호스트의 응답을 기다리는 데 소요 된 시간
  - http_req_receiving — 데이터 수신에 소요 된 시간
  - http_req_duration— 요청에 대한 총 시간. http_req_sending + http_req_waiting 기준

- 지표적 성능 향상 수치
  - 총 요청수 : 2.5 배 가까이 정상적으로 올라옴
  - 요청 시작전 tcp 연결 대기 소요 시간 : 8배 향상
  - tcp 연결 소요 시간 : 10배 이상 향상
  - **원격 호스트 응답 대기 시간 : 3000배 가까운 성능향상**
  - **총 요청 시간 : 2.5배**

지표적으로 분석 했을 경우 캐싱을 적용하여 가장 눈에 띄는 지표는 원격 호스트의 응답 대기시간과
총 요청 시간인 것 같습니다.

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

[실행결과 결과](/images/sql.PNG)

``` mysql
-- Coding as a Hobby 와 같은 결과를 반환하세요.
-- hobby 설정
select 
	  (sum(case when hobby = 'Yes' then 1 else 0 end) / count(*)) * 100 as '네'
    , (sum(case when hobby = 'No' then 1 else 0 end) / count(*)) * 100 as '아니오'
from  programmer;

-- 프로그래머별로 해당하는 병원 이름을 반환하세요.
-- covid, hospital pk 설정
select 
	   cv.id
     , hp.name
from   programmer pg
join   covid cv
on     pg.id = cv.programmer_id
join   hospital hp
on     hp.id = cv.hospital_id;

-- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
select 
	   programmer.covid_id
	 , hospital.name
     , programmer.hobby
     , programmer.dev_type
     , programmer.years_coding
from (
		select 
			   c.id as covid_id
			 , c.hospital_id
             , m.id as member_id
			 , hobby
             , dev_type
             , years_coding
        from   covid c
        join   programmer p
        on     p.id = c.programmer_id
        join   member m
        on     m.id = p.member_id
        where  hobby = 'Yes'
		and    ( student = 'Yes' or years_coding = '0-2 years' )
        order  by m.id
) programmer
join hospital hospital
on   hospital.id = programmer.hospital_id
;

-- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요
select 
	   covid.stay
	 , count(*)
from (
		select 
			   m.id as member_id
		from   member m
		join   programmer p
		on     m.id = p.member_id
        where  p.country = 'India'
		and    m.age between 20 and 30
) user
join (
		select 
			   c.member_id
             , c.stay
		from   covid c
		join   hospital h
		on     c.hospital_id = h.id
        and    h.id = '9'
) covid
on    user.member_id = covid.member_id
group by covid.stay;

-- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
select 
	   user.exercise
	 , count(*)
from (
		select 
			   p.id as programmer_id
			 , p.exercise
		from   member m
		join   programmer p
		on     m.id = p.member_id
        where  p.country = 'India'
		and    m.age between 20 and 30
) user
join (
		select 
			   c.programmer_id
             , c.stay
		from   covid c
		join   hospital h
		on     c.hospital_id = h.id
        and    h.id = '9'
) covid
on    user.programmer_id = covid.programmer_id
group by user.exercise;
```


2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

