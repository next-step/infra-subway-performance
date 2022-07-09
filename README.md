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

- 표 우측으로 갈수록 기능 누적 추가 (*고정 경로*)

|req_duration| before  | + gzip  | + cache | + http2 | + redis | +고정 경로 캐시 
|------------|---------|---------|---------|---------|---------|--------------
| smoke      | 66.16ms | 39.83ms | 36.53ms | 38.52ms | 37.98ms | 6.2ms         
| load       | 49.42ms | 48.50ms | 49.39ms | 51.02ms | 45.87ms | 6.6ms         
| stress     | 490.78ms| 454.78ms| 451.65ms| 473.73ms| 374.18ms| 215.17ms      

- 표 우측으로 갈수록 기능 누적 추가 (*랜덤 경로*)

|req_duration| + 랜덤 경로 캐시| + 톰캣 스레드(200 > 400) |
|------------|---------------|---------------
| smoke      | 23.72ms       | 11.34ms
| load       | 19.65ms       | 16.90ms
| stress     | 454.78ms      | 203.44ms

- handh.kro.kr

| 사이트        | 구분     | FCP   | TTI   | SI    | TBT    | LCP   | CLS   |
|--------------|---------|-------|-------|-------|--------|-------|-------|
| 개선 이전     | Mobile  | 14.5s | 15.1s | 14.5s | 0.560s | 15.1s | 0.042 |
|              | Desktop | 2.7s  | 2.8s  | 2.7s  | 0.050s | 2.8s  | 0.004 |
| 개선 이후     | Mobile  | 5.2s |  5.6s |  5.2s |  0.400s | 5.6s  | 0.042 |
|              | Desktop | 1.1s  | 1.2s  | 1.4s  | 0.020s | 1.2s  | 0.004 |

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- [x] gzip 압축 수행
    - nginx 설정파일에서 gzip 옵션 추가
    - smoke 부하 테스트는 36ms 속도 개선
- [x] cache 적용
    - nginx 설정파일에서 cache 옵션 추가
    - gzip 압축때와 비교하여 상대적으로 속도 개선 없었음
- [x] http2 적용
    - nginx 설정파일에서 http2 옵션 추가
    - cache까지 적용했을 때보다 오히려 속도가 살짝 느려짐
- [x] Spring Boot Redis 적용
    - 도커를 통해 redis 실행 및 SpringBoot와 연결 수행
    - stress 테스트의 경우에는 다른 테스트와 비교하여 상대적으로 많이 개선됨
- [x] Spring Boot Tomcat 스레드 수 증가
    - 200개 → 400개
    - 전체적으로 속도 개선됨
    
#### 목표 rps 구하기 (참고 자료)

- 예상 1일 사용자 수(DAU): 450,000명 (2021년 8월 네이버지도 1,392만명 이용)
    - DAU 참고 ([링크](https://moneys.mt.co.kr/news/mwView.php?no=2021091810258035737))
    
- 피크 시간대의 집중률: 2.2
    - 2022년 5월 승/하차 인원 피크 18~19시 평균: 1,332,176명
    - 2022년 5월 승/하차 인원 시간당 평균: 623,866명
    - 집중률 참고 ([링크](https://insfiler.com/detail/rt_subway_time-0003))
    
- 1명당 1일 평균 접속 수: 6회
    - 출근 3회, 퇴근 3회
    - 대중교통 환승 시 추가 사용 고려하여 선정 ([링크](https://www.sedaily.com/NewsView/265XF8LQW8))
    
- 1일 총 접속 수: 1일 사용자 수(DAU) x 1명당 1일 평균 접속 수
    - 450,000 * 6 = 2,700,000
    
- 1일 평균 rps: 1일 총 접속 수 / 86,400
    - 2,700,000 / 86,400 = 32
    
- 1일 최대 rps: 1일 평균 rps x (최대 트래픽 / 평균 트래픽)
    - 32 * 2.2 = 71
    
- Latency: 일반적으로 50 ~ 100ms 이하로
    - 100ms
    
### VUser 구하기

- R(VUser가 1회 테스트 시 요청 보내는 수): 6 
    - 메인페이지 이동
    - 로그인 페이지 이동
    - 회원가입 페이지 이동
    - 로그인
    - 내 정보 조회
    - 최단 경로 조회
    
- T = (R * http_req_duration) (+ 1s)
    - (6 * 0.1) + 1 = 2s
    
- VUser = (목표 rps * T) / R
    - Min VUser = (32 * 2) / 6 = 10
    - Max VUser = (71 * 2) / 6 = 24  

---

### 2단계 - 스케일 아웃

0. 요구사항
- [x] springboot에 HTTP Cache, gzip 설정하기
- [x] Launch Template 작성하기
- [x] Auto Scaling Group 생성하기
- [x] DNS(내도메인.한국), TLS 설정
    - AWS ALB IP를 A 레코드로 등록했으나, ALB IP가 바뀌는 이슈가 있습니다.
- [x] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.

[링크](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0dd94461facf15a35)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

![이미지](/k6/stress/stress_feedback_auto_scaling.PNG)

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- 2단계 요구 사항을 만족한 이후 1단계 테스트에서 수행한 스크립트 그대로 테스트 진행
    - Smoke, Load, Stress 테스트 모두 부하로 인한 인스턴스 개수가 증가하지 않음
    - 캐싱, gzip 등 여러 가지 기능의 반영으로 성능이 개선되었음
    - 반대로 1단계 테스트의 시간이나 VUSER의 수가 작았음을 의미
    
- 1단계 리뷰어님의 피드백에 따라서 추가로 Stress 테스트 진행
    - 시나리오 개선1: 스크립트 시나리오에서 프로그램의 핵심 로직인 경로 탐색에 집중
    - 시나리오 개선2: 경로 탐색에 캐시를 적용함으로써 높은 부하 상태에서 성능 개선 확인
    - 시나리오 개선3: 테스트 부하 테스트 시간 증가(4m → 15m) 및 Vuser의 수 증가 (300 → 900)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
select 
    a.id as '사원번호', 
    a.last_name as '이름', 
    a.annual_income as '연봉', 
    a.position_name as '직급명', 
    r.region as '지역', 
    r.record_symbol as '입출입 구분', 
    MAX(r.time) as '입출입 시간'
from record r
inner join (
	select e.id, e.last_name, s.annual_income, p.position_name
	from employee e
	inner join manager m on e.id = m.employee_id and curdate() between m.start_date and m.end_date
	inner join department d on m.department_id = d.id and d.note = 'active'
	inner join position p on m.employee_id = p.id and p.position_name = 'manager'
	inner join salary s on s.id = e.id and curdate() between s.start_date and s.end_date
	order by s.annual_income desc
	limit 5
) a
on r.employee_id = a.id
and r.record_symbol = 'O'
group by a.id, a.last_name, a.annual_income, a.position_name, r.region, r.record_symbol
order by a.annual_income desc, r.region;
```

---

### 2단계 - 인덱스 설계

0. 테이블 별 설정한 인덱스 정보

|Table       | Key_name               | Column_name |
|------------|------------------------|---------------
| covid      | PRIMARY                | id
| covid      | id_UNIQUE              | id
| covid      | idx_covid_hospital_id  | hospital_id
| covid      | idx_covid_programmer_id| programmer_id

|Table       | Key_name               | Column_name |
|------------|------------------------|---------------
| hospital   | PRIMARY                | id
| hospital   | id_UNIQUE              | id
| hospital   | name_UNIQUE            | name
| hospital   | idx_hospital_name      | name

|Table       | Key_name               | Column_name |
|------------|------------------------|---------------
| member     | PRIMARY                | id
| member     | id_UNIQUE              | id

|Table       | Key_name               | Column_name |
|------------|------------------------|---------------
| programmer | PRIMARY                | id
| programmer | id_UNIQUE              | id
| programmer | idx_programmer_country | country
| programmer | idx_programmer_exercise| exercise


1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- [x] [Coding as a Hobby](https://insights.stackoverflow.com/survey/2018#developer-profile-_-coding-as-a-hobby) 와 같은 결과를 반환하세요.
    - Coding as a Hobby
```sql
select programmer.hobby, 
	round(concat((programmer.cnt / total.cnt) * 100, "%"),2) as percentage
from (
    select 
        hobby,
        count(id) as cnt
    from programmer
    group by hobby
) as programmer
inner join (
    select count(id) as cnt
    from programmer
) as total on 1 = 1;
```

![이미지](/query/step4/question0_explain.png)

- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)

```sql
select c.id, h.name
from programmer p
inner join covid c on c.programmer_id = p.id
inner join hospital h on h.id = c.hospital_id;
```

![이미지](/query/step4/question1_explain.png)

- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
    - (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)

```sql
select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from programmer p
inner join covid c on c.id = p.id
inner join hospital h on h.id = c.hospital_id
where (p.student in ('Yes, part-time', 'Yes, full-time') and p.hobby = 'Yes')
    or (p.years_coding = '0-2 years' and p.student in ('No', 'NA'))
order by p.id;
```

![이미지](/query/step4/question2_explain.png)

- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)

```sql
select c.stay, count(m.id)
from (select id from member where age between 20 and 29) m
inner join (select id from programmer where country = 'india') p on m.id = p.id
inner join (select hospital_id, programmer_id, stay from covid) c on c.programmer_id = p.id
inner join (select id from hospital where name = '서울대병원') h on h.id = c.hospital_id
group by stay
```

![이미지](/query/step4/question3_explain.png)

- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)

```sql
select exercise, count(m.id)
from (select id from member where age between 30 and 39) m
inner join (select id, exercise from programmer) p on m.id = p.id
inner join (select hospital_id, programmer_id, stay from covid) c on c.programmer_id = p.id
inner join (select id from hospital where name = '서울대병원') h on h.id = c.hospital_id
group by exercise;
```

![이미지](/query/step4/question4_explain.png)

- [x] 전체 출력 결과

![이미지](/query/step4/output.png)

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
