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

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- [x] gzip 압축
- [x] TLS, HTTP/2 설정
- [x] WAS 캐싱 적용
- [x] nginx cache 적용
- https://yohan-subway.n-e.kr/

---

### 2단계 - 스케일 아웃

- [x] gzip 설정
- [x] springboot에 HTTP Cache 설정
    - [x] 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
    - [x] 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
- [x] Launch Template 작성
- [x] Auto Scaling Group 생성
- [x] Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.
   - https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-079a679d4ffecaa27
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
* `/monitoring/cloudwatch` - 폴더 내 결과 캡쳐
```sh
$ stress -c 2
```
3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* `/monitoring/load/cache` - 폴더 내 결과 캡쳐
* `/monitoring/stress/cache` - 폴더 내 결과 캡쳐
* `/monitoring/smoke/cache` - 폴더 내 결과 캡쳐
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

- 작성 쿼리 
```
SELECT c.employee_id   as 사원번호,
       c.last_name     AS 이름,
       c.annual_income AS 연봉,
       c.position_name AS 직급명,
       r.time          AS 입출입시간,
       r.region        AS 지역,
       r.record_symbol AS 입출입구분
FROM (SELECT m.employee_id,
             e.last_name,
             s.annual_income,
             p.position_name
      FROM manager m
               JOIN department d ON d.id = m.department_id
               JOIN position p ON p.id = m.employee_id
               JOIN employee e ON e.id = m.employee_id
               JOIN salary s ON s.id = e.id
      WHERE d.note = 'active'
        AND p.position_name = 'manager'
        AND NOW() BETWEEN m.start_date AND m.end_date
        AND NOW() BETWEEN s.start_date AND s.end_date
      ORDER BY s.annual_income DESC
      LIMIT 5) c
         JOIN record r ON r.employee_id = c.employee_id
WHERE r.record_symbol = 'O'
ORDER BY c.annual_income DESC;
```

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- [x] Coding as a Hobby 와 같은 결과를 반환하세요.
- `Coding as a Hobby.png`
```sql
ALTER TABLE programmer ADD CONSTRAINT pk_programmer PRIMARY KEY (id);
ALTER TABLE programmer ADD INDEX idx_programmer_hobby(hobby);

SELECT hobby,
       ROUND((COUNT(id) / (SELECT COUNT(id) FROM programmer) * 100), 1) as rate
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;

```
- [x] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name) 
- `hospital_name.png`
```sql
ALTER TABLE hospital ADD CONSTRAINT pk_hospital PRIMARY KEY(id);
ALTER TABLE covid ADD CONSTRAINT pk_covid PRIMARY KEY(id);
ALTER TABLE covid ADD INDEX idx_covid_programmer_id(programmer_id);
ALTER TABLE covid ADD INDEX idx_covid_hospital_id(hospital_id);

SELECT c.id, 
       h.name
FROM hospital h
         INNER JOIN covid c ON h.id = c.hospital_id
         INNER JOIN programmer p ON c.programmer_id = p.id;
```
- [x] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding) 
- `hobby.png`
```sql
SELECT c.id,
       h.name,
       p.hobby,
       p.dev_type,
       p.years_coding
FROM hospital h
         INNER JOIN covid c ON h.id = c.hospital_id
         INNER JOIN programmer p ON p.id = c.programmer_id
WHERE p.hobby = 'Yes'
  AND (p.years_coding = '0-2 years' OR p.student LIKE 'yes%')
ORDER BY p.id;
```
- [x] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
- `covid_stay.png`
```sql
ALTER TABLE member ADD CONSTRAINT pk_member PRIMARY KEY(id);
ALTER TABLE hospital ADD INDEX idx_hospital_name(name);
ALTER TABLE member ADD INDEX idx_member_age(age);
ALTER TABLE programmer ADD INDEX idx_programmer_country(country);

SELECT c.stay,
       COUNT(c.id)
FROM hospital h
         INNER JOIN covid c ON c.hospital_id = h.id
         INNER JOIN programmer p ON p.id = c.programmer_id
         INNER JOIN member m ON m.id = c.member_id
WHERE h.name = '서울대병원'
  AND m.age BETWEEN 20 AND 29
  AND p.country = 'india'
GROUP BY c.stay
```
- [x] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
- `user_exercise.png`
```sql
SELECT p.exercise,
       COUNT(p.id)
FROM hospital h
         INNER JOIN covid c ON c.hospital_id = h.id
         INNER JOIN programmer p ON p.id = c.programmer_id
         INNER JOIN member m ON m.id = c.member_id
WHERE h.name = '서울대병원'
  AND m.age BETWEEN 30 AND 39
GROUP BY p.exercise;
```

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
