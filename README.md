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
- /src/main/resources/step1 디렉토리 내 이미지 파일 참고 부탁드립니다.

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- redis를 이용하여 노선 조회, 역 조회, 경로 조회 기능에 캐싱을 적용했습니다.
- gzip 압축, cache 사용, TLS, HTTP/2 설정을 통해 리버스 프록시를 개선했습니다.

---

### 2단계 - 스케일 아웃
- spring boot에 HTTP Cache, gzip 설정하기 
- Launch Template 작성하기 
- Auto Scaling Group 생성하기
- Smoke, Load, Stress 테스트 후 결과를 기록

1. Launch Template 링크를 공유해주세요.
- [바로가기](https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0a8521e6842d65ea9)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

- /src/main/resources/step2 디렉토리 내 이미지 파일 참고 부탁드립니다.

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- /src/main/resources/step2 디렉토리 내 이미지 파일 참고 부탁드립니다.
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
SELECT 
    manager_salary_top5.사원번호,
    manager_salary_top5.이름,
    manager_salary_top5.연봉,
    manager_salary_top5.직급명,
    r.time AS 입출입시간,
    r.region AS 지역,
    r.record_symbol AS 입출입구분
FROM
    (SELECT 
        m.employee_id AS 사원번호,
            e.last_name AS 이름,
            s.annual_income AS 연봉,
            p.position_name AS 직급명
    FROM
        manager AS m
    JOIN department AS d ON d.id = m.department_id
    JOIN position AS p ON p.id = m.employee_id
    JOIN employee AS e ON e.id = m.employee_id
    JOIN salary AS s ON s.id = e.id
    WHERE
        d.note = 'active'
	AND p.position_name = 'Manager'
	AND NOW() BETWEEN m.start_date AND m.end_date
	AND NOW() BETWEEN s.start_date AND s.end_date
    ORDER BY s.annual_income DESC
    LIMIT 5) AS manager_salary_top5
JOIN record AS r ON r.employee_id = manager_salary_top5.사원번호
WHERE r.record_symbol = 'O'
ORDER BY manager_salary_top5.연봉 DESC;
```

---

### 4단계 - 인덱스 설계
### 요구사항
- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
    - Coding as a Hobby 와 같은 결과를 반환하세요.
    - 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
    - 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
    - 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
    - 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

1-0. 인덱스, PK 추가 쿼리
```sql
ALTER TABLE subway.member add constraint PK_MEMBER__ID primary key (id);
CREATE INDEX IDX_MEMBER__AGE ON subway.member (age);

ALTER TABLE subway.covid add constraint PK_COVID__ID primary key (id);
CREATE INDEX idx_covid_programmer_id ON subway.covid (programmer_id);
CREATE INDEX idx_covid_member_id ON subway.covid (member_id);
CREATE INDEX idx_covid_hospital_id ON subway.covid  (hospital_id);

ALTER TABLE subway.programmer add constraint PK_PROGRAMMER__ID primary key (id);
CREATE INDEX IDX_PROGRAMMER__MEMBER_ID ON subway.programmer (member_id);
CREATE INDEX IDX_PROGRAMMER__COUNTRY ON subway.programmer (country);
CREATE INDEX idx_programmer_hobby_student_years_coding on subway.programmer (hobby, student, years_coding);

ALTER TABLE subway.hospital add constraint PK_HOSPITAL__ID primary key (id);
CREATE INDEX idx_hospital_name_id ON subway.hospital (name, id);
```
1-1. Coding as a Hobby 와 같은 결과를 반환하세요.
```sql
SELECT
    hobby,
    CONCAT(ROUND((COUNT(hobby) * 100) / (SELECT COUNT(hobby) FROM subway.programmer), 1), '%') as rate
FROM subway.programmer
GROUP BY hobby
ORDER BY hobby DESC;
```
1-2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
SELECT
    covid.id,
    hospital.name 
FROM
    (SELECT id,hospital_id,programmer_id FROM subway.covid) as covid
INNER JOIN (SELECT id,name FROM subway.hospital) as hospital ON covid.hospital_id = hospital.id
INNER JOIN (SELECT id FROM subway.programmer) as programmer ON covid.programmer_id = programmer.id;
```
1-3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql
SELECT
    covid.id,
    hospital.name,
    programmer.hobby,
    programmer.dev_type,
    programmer.years_coding
FROM
    (SELECT
         id,
         hobby,
         dev_type,
         years_coding 
	FROM
	    subway.programmer
	WHERE
	    hobby = 'Yes' 
	AND (years_coding = '0-2 years' OR student LIKE 'Yes%')) as programmer
INNER JOIN (SELECT id, programmer_id, hospital_id FROM subway.covid) as covid ON programmer.id = covid.programmer_id
INNER JOIN (SELECT id, name FROM subway.hospital) as hospital ON hospital.id = covid.hospital_id
ORDER BY programmer.id;
```
1-4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```sql
SELECT
    covid.stay,
    COUNT(*) as count 
FROM
    (SELECT
         id
     FROM
         subway.member
     WHERE
         age BETWEEN 20 AND 29) as member
INNER JOIN (SELECT id FROM subway.programmer WHERE country = 'India') as programmer ON member.id = programmer.id 
INNER JOIN (SELECT id,hospital_id,stay FROM subway.covid) as covid ON programmer.id = covid.id 
INNER JOIN (SELECT id FROM subway.hospital WHERE id = 9) as hospital ON hospital.id = covid.hospital_id 
GROUP BY covid.stay;
```
1-5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```sql
SELECT
    programmer.exercise,
    COUNT(*) as count
FROM
    (SELECT
         id,
         member_id,
         hospital_id,
         programmer_id
     FROM
         subway.covid) as covid
INNER JOIN (SELECT id FROM subway.hospital WHERE id = 9) as hospital ON covid.hospital_id = hospital.id
INNER JOIN (SELECT id, exercise FROM subway.programmer) as programmer ON covid.programmer_id  = programmer.id
INNER JOIN (SELECT id, age FROM subway.member WHERE age BETWEEN 30 AND 39) as member ON covid.member_id = member.id
GROUP BY programmer.exercise;
```
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
