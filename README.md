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

#### 0. 목표 rps 구하기
1) 1일 예상 사용자 수(DAU) : 500,000명 (50만명)
2) 피크 시간대의 집중률 (최대 트래픽/평소 트래픽) : 10
3) 1명당 1일 평균 요청수 : 4

**Throughput 계산 : 1일 평균 rps ~ 1일 최대 rps**
- 1일 총 접속 수 : DAU(1일 사용자 수) X 1명당 1일 평균 접속 수
    - 500,000 * 4 = 2,000,000
- 1일 평균 rps : 1일 총 접속 수 / 86,400(초/일)
    - 2,000,000 / 86,400 = 23.14 => 24
- 1일 최대 rps : 1일 평균 rps * (피크 시간대 집중률)
    - 23.14 * 10 = 231.4 => 232 

[VUser 구하기]
```
VU: 가상 사용자 수
R: 하나의 VU iteration(시나리오)에 포함된 요청 수
T: VU iteration을 완료하는데 소요되는 시간보다 큰 시간
```
- http_req_duration : 0.8s
- Latency : 100ms 이하
- 
- R: 1 (메인페이지, 경로검색 모두 1)
- T = 1*0.8s +0.1s = 0.9s
- 평균 VUser = 24 * 0.9s / 1 = 21.6 => 22
- 최대 VUser = 232 * 0.9s / 1 = 208.8 => 209

#### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

[메인페이지] - 정적 리소스 캐싱과 etag 적용 영향 테스트

**smoke test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 19.64ms           | 276.09µs           | 18.74ms          | 26.53ms          |
| 개선후 | 57.36ms           | 436.79µs           | 56.43ms          | 27.47ms          |



**load test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 14.23ms           | 131.78µs           | 13.98ms          | 128.47µs         |
| 개선후 | 14.65ms           | 140.24µs           | 14.37ms          | 137.33µs         |



**stress test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked | http_req_failed |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- | --------------- |
| 개선전 | 9.7ms             | 85.08µs            | 9.55ms           | 43.2µs           | 31.52%          |
| 개선후 | 9.79ms            | 84.46µs            | 9.63ms           | 42.35µs          | 31.52%          |



[경로검색 - 복잡한 연산이 포함된 페이지] - WAS 캐싱 영향 테스트

**smoke test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 154.51ms          | 909.88µs           | 153.13ms         | 30.47ms          |
| 개선후 | 162.87ms          | 679.66µs           | 161.66ms         | 27.94ms          |



**load test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- |
| 개선전 | 601.18ms          | 462.54µs           | 600.64ms         | 810.24µs         |
| 개선후 | 15.01ms           | 164µs              | 14.69ms          | 150.46µs         |



**stress test**

| 구분   | http_req_duration | http_req_receiving | http_req_waiting | http_req_blocked | http_req_failed |
| ------ | ----------------- | ------------------ | ---------------- | ---------------- | --------------- |
| 개선전 | 615.78ms          | 319.46µs           | 615.44ms         | 298.25µs         | 46.96%          |
| 개선후 | 11.94ms           | 153.98µs           | 11.71ms          | 54.68µs          | 29.27%          |


#### 결론
- 정적 리소스 캐싱을 적용한 것은 큰 차이를 보이지 못함
- WAS 캐싱은 많은 개선 효과가 있는 것으로 보임

** 스크립트와 결과물은 ```k6-test``` 폴더 참조

#### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
1) ETag와 정적 캐싱을 이용한 성능 개선
- 정적 리소스(js와 css)에 대해 캐시 만료 기한을 1년으로 설정
- 변경된 파일 배포시 클라이언트가 업데이트 받을 수 있도록 요청 url에 버전정보를 사용
- html에서 리소스 버전 정보를 하드코딩하여 url에 사용하지 않고 ```HandlebarsHelper```를 이용하여 버전 정보를 서버에서 받아올 수 있도록 설정
- 버전 정보는 설정파일로 관리하여, 버전 변경시 재빌드 하지 않아도 되도록 함

2) WAS 캐싱
- 경로 찾기 캐싱
  - ```노선 추가, 노선 삭제, 노선에 구간 추가, 노선에 구간 삭제```의 경우, 캐싱 삭제
- 노선 캐싱(전체조회, 특정ID로 조회)
  - 특정 노선에 대해 ```입력, 수정, 삭제``` 이벤트가 발생할 경우 ```전체 조회``` 캐싱 삭제
- 지하철역 캐싱(전체 조회)
  - 특정 지하철역에 대해 ```입력, 수정, 삭제``` 이벤트가 발생할 경우 ```전체 조회``` 캐싱 삭제


---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
### A. 쿼리 최적화

#### 요구사항

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
  (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

- 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.

#### 단계별 요구사항

1. 쿼리 작성만으로 1s 이하로 반환한다.
2. 인덱스 설정을 추가하여 50 ms 이하로 반환한다.



#### 진행과정

> [참고] mac M1 칩 사용 환경

1.쿼리 작성 후 실행

- cost : 14828.04
- duration time : 2.707 s
- fetch time : 0.000062 s

<details markdown="1">
  <summary>초기 실행 계획</summary>

  ![1번쿼리-초기](https://user-images.githubusercontent.com/62507373/147715477-9d09ee87-ee6d-4e97-8483-63d0d05e6f3f.png)
</details>



2.개선 대상 파악

- '부서' 테이블이 table full scan됨

  - 전체 중 active가 대부분을 차지함

  - 테이블의 데이터 양 자체가 작아서 (부서번호,비고) 복합키로 거는게 아니면 인덱스 스캔을 해도 테이블 랜덤 액세스를 할 것임

    => 결론 : 테이블 크기가 작고 active 비율이 대부분이라 인덱스 사용하지 않음

- '사원출입기록' 테이블이 table full scan 됨.

  - PK 복합키로 묶여있는 ''사원번호'' 칼럼에 대해 별도 인덱스 생성

3.개선

- '사원출입기록' 테이블에 '사원번호' 칼럼에 인덱스 추가

4.결과 확인

- cost : 13.74
- duration time : 0.011 s
- fetch time : 0.000013 s

<details markdown="1">
  <summary>개선된 실행 계획</summary>

![a-2](https://user-images.githubusercontent.com/62507373/147719612-75b92efe-ef6c-4f5b-a502-a11d5ce76ccd.png)
</details>


> 실행쿼리

```sql
select ba.사원번호
     , em.이름
     , ba.연봉
     , l.직급명
     , r.지역
     , r.입출입구분
     , r.입출입시간
from
  (SELECT bm.사원번호, s.연봉 FROM 부서관리자 as bm
                               inner join 부서 as b on b.비고 = 'active' and bm.부서번호 = b.부서번호
                               inner join 급여 as s on s.사원번호 = bm.사원번호 and s.시작일자<=curdate() and s.종료일자 = str_to_date('99990101','%Y%m%d')
   order by s.연봉 desc
     limit 0,5) as ba
    inner join 사원출입기록 as r on ba.사원번호 = r.사원번호
    inner join 사원 as em on ba.사원번호 = em.사원번호
    inner join 직급 as l on ba.사원번호 = l.사원번호 and l.시작일자<= curdate() and l.종료일자 = str_to_date('99990101','%Y%m%d');
```

### B. 인덱스 설계

#### 요구사항

- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환

**1.[Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은 결과를 반환하세요.**

- 인덱스 설정

  - programmer : hobby 인덱스 생성

- 결과

  - cost : 1
  - duration time : 0.303 s
  - fetch time : 0.000019 s

  
> 실행쿼리

```sql
select concat(round((st.yes_count*100) / total,1),'%') as 'Yes' 
, concat(round((st.no_count*100) / total,1),'%') as 'No' 
from
(select sum(case when hobby = 'yes' then 1 else 0 end) as yes_count , sum(case when hobby = 'no' then 1 else 0 end) as no_count, sum(1) as total
 from programmer p) st;
```

<details markdown="1">
  <summary>실행 계획</summary>

![b-1](https://user-images.githubusercontent.com/62507373/147715706-1294ed58-3dcd-4c10-bcee-e3685edaf922.png)
</details>



**2.프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)**

- 인덱스 설정

  - covid : 복합키 인덱스(programmer_id, hospital_id)

  - programmer : id에 pk 설정

  - hospital : id에 pk 설정, 복합키 인덱스(id, name)
- 결과

  - cost : 443969.12
  - duration time : 0.035 s
  - fetch time : 0.012s



> 실행 쿼리

```sql
select p.id, h.name
from programmer p 
inner join covid c on p.id = c.programmer_id
inner join hospital h on c.hospital_id = h.id;
```

<details markdown="1">
  <summary>실행 계획</summary>

![b-2](https://user-images.githubusercontent.com/62507373/147715722-f48fa410-60dc-4c6b-b5ce-4a0591ea3a35.png)
</details>



**3.프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)**

- 인덱스 설정

  - programmer : years_coding에 인덱스 설정

- 결과

  - cost : 109532.17

  - duration time : 0.036 s

  - fetch time : 0.012s

  
> 실행 쿼리

```sql
select pr.id, h.name
from
(select p.id from programmer p 
 where p.hobby='yes' or p.years_coding = '0-2 years') pr
inner join covid c on pr.id = c.programmer_id
inner join hospital h on c.hospital_id = h.id
order by pr.id;
```

<details markdown="1">
  <summary>실행 계획</summary>

![b-3](https://user-images.githubusercontent.com/62507373/147715731-4b549c48-2277-4d5b-8b20-3a86c38241ee.png)
</details>

**4.서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)**

- 인덱스 설정

  - hospital : name 인덱스 설정
  - programmer: country 인덱스 설정
  - member: id pk 설정, age 인덱스 설정
  - covid : (hospital_id, programmer_id, member_id, stay) 복합키 인덱스 설정

- 결과

  - cost : 199294.39

  - duration time : 0.078 s

  - fetch time : 0.000018s


> 실행 쿼리

```sql
select * from covid c
inner join hospital h on h.name='서울대병원' and c.hospital_id = h.id
inner join programmer p on c.programmer_id=p.id and p.country='India'
inner join member m on c.member_id=m.id and m.age>=20 and m.age<=29;
```

<details markdown="1">
  <summary>실행 계획</summary>

![b-4](https://user-images.githubusercontent.com/62507373/147715745-fc294a13-63fb-4a45-a222-068151e87068.png)
</details>

**5.서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)**

1. 인덱스 설정
  - programmer: exercise 인덱스 설정
2. 결과

  - cost : 20909.79

  - duration time : 0.075s

  - fetch time : 0.0.000015s
  
> 실행 쿼리

```sql
select p.exercise, count(exercise)
from covid c
inner join hospital h on h.name='서울대병원' and c.hospital_id = h.id
inner join member m on c.member_id=m.id and m.age>=30 and m.age<=39
inner join programmer p on c.programmer_id=p.id
group by p.exercise
order by null;
```

<details markdown="1">
  <summary>실행 계획</summary>

![b-5](https://user-images.githubusercontent.com/62507373/147715757-1d4e3897-9ff9-4177-8955-27610a4259c9.png)
</details>


2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

- 지하철역 조회
  https://jerry92k-subway.n-e.kr/stations?page=1&size=2
- 노선 조회
  https://jerry92k-subway.n-e.kr/lines?page=1&size=2
