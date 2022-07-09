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
- smoke
    + before
    ![smoke_test_before](/result/k6/smoke_test_before.png)
    + after
    ![smoke_test_after](/result/k6/smoke_test_after.png)
- load
    + before
    ![load_test_before](/result/k6/load_test_before.png)
    + after
    ![load_test_after](/result/k6/load_test_after.png)
- stress
    + before
    ![stress_test_before](/result/k6/stress_test_before.png)
    + after
    ![stress_test_after](/result/k6/stress_test_after.png)
- PageSpeed - mobile
    + before
    ![PageSpeed_mobile_before](/result/pagespeed/PageSpeed_mobile_before.png)
    + after
    ![PageSpeed_mobile_after](/result/pagespeed/PageSpeed_mobile_after.png)
- PageSpeed - mobile
    + before
    ![PageSpeed_pc_before](/result/pagespeed/PageSpeed_pc_before.png)
    + after
    ![PageSpeed_pc_after](/result/pagespeed/PageSpeed_pc_after.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선
    + gzip 압축
    + 캐싱
    + TLS, HTTP/2 설정
- WAS 성능 개선
    - Redis Spring Data Cache 적용

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-08c6e2cac8490e43e

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
  ![](/result/step2/cloudwatch1.png)
  ![](/result/step2/cloudwatch2.png)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- smoke
  ![smoke_test](/result/step2/smoke_test.png)
+ load
  ![load_test](/result/step2/load_test.png)
- stress
  ![stress_test](/result/step2/stress_test.png)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
    ```
    SELECT e.id as '사원번호'
         , e.last_name as '이름'
         , high_salary.annual_income as '연봉'
         , p.position_name as '직급명'
         , r.region as '지역'
         , r.record_symbol as '입출입구분'
         , r.time as '입출입시간'
      FROM tuning.employee e
           INNER JOIN (SELECT id
                            , annual_income
                         FROM tuning.salary s
                        WHERE id IN (SELECT employee_id
                                       FROM tuning.manager
                                      WHERE department_id in (SELECT id FROM tuning.department WHERE note = 'Active')
                                        AND start_date <= now() AND end_date >= now())
                          AND start_date <= now() AND end_date >= now()
                        ORDER BY annual_income desc
                        LIMIT 5) high_salary
                   ON high_salary.id = e.id
           INNER JOIN tuning.position p
                   ON p.id = e.id
                  AND p.start_date <= now() AND p.end_date >= now()
           INNER JOIN tuning.record r
                   ON r.employee_id = e.id
                  AND r.record_symbol = 'O'
    ```
    ![](/result/step3/result_grid.png)
    ![](/result/step3/output.png)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

#### PK 지정 및 인덱스 생성
```
/* subway.covid */
ALTER TABLE `subway`.`covid` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
ADD PRIMARY KEY (`id`),
ADD INDEX `idx_covid_hospital_id` (`hospital_id` ASC),
ADD INDEX `idx_covid_member_id` (`member_id` ASC),
ADD INDEX `idx_covid_programmer_id` (`programmer_id` ASC);

/* subway.hospital */
ALTER TABLE `subway`.`hospital` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL ,
ADD PRIMARY KEY (`id`);

/* subway.member */
ALTER TABLE `subway`.`member` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
ADD PRIMARY KEY (`id`),
ADD INDEX `idx_member_age` (`age` ASC);

/* subway.programmer */
ALTER TABLE `subway`.`programmer` 
CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
ADD PRIMARY KEY (`id`),
ADD INDEX `idx_programmer_member_id` (`member_id` ASC),
ADD INDEX `idx_programmer_country` (`country` ASC),
ADD INDEX `idx_programmer_hobby_student_years_coding` (`hobby` ASC, `student` ASC, `years_coding` ASC);
```

- Coding as a Hobby 와 같은 결과를 반환하세요.
    ```
    SELECT CONCAT(ROUND(COUNT(CASE WHEN hobby = 'Yes' THEN 1 END) / COUNT(*) * 100, 1), '%') as yes
         , CONCAT(ROUND(COUNT(CASE WHEN hobby = 'No' THEN 1 END) / COUNT(*) * 100, 1), '%') as No
      FROM subway.programmer
    ```
    ![실행계획](/result/step4/query1_explain.png)
    ![실행결과](/result/step4/query1_output.png)

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
    ```
    SELECT covid.id, hospital.name
      FROM subway.covid
           INNER JOIN subway.programmer
                   ON programmer.id = covid.programmer_id
           INNER JOIN subway.hospital
                   ON hospital.id = covid.hospital_id
    ```
    ![실행계획](/result/step4/query2_explain.png)
    ![실행결과](/result/step4/query2_output.png)

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
    ```
    SELECT covid.id, hospital.name, user.hobby, user.dev_type, user.years_coding
      FROM subway.covid
           INNER JOIN (SELECT id, hobby, dev_type, years_coding
                         FROM subway.programmer
                        WHERE (hobby = 'Yes' AND student like 'Yes%') OR years_coding = '0-2 years'
                       ) user
                   ON user.id = covid.programmer_id
           INNER JOIN subway.hospital
                   ON hospital.id = covid.hospital_id
     ORDER BY user.id
    ```
    ![실행계획](/result/step4/query3_explain.png)
    ![실행결과](/result/step4/query3_output.png)

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
    ```
    SELECT covid.stay, COUNT(*) AS COUNT
      FROM subway.covid
           INNER JOIN (SELECT id
                            FROM subway.member
                        WHERE age BETWEEN '20' AND 29
                       ) member
                   ON member.id = covid.member_id
           INNER JOIN (SELECT id
                         FROM subway.programmer
                        WHERE country='India'
                       ) programmer
                   ON programmer.id = covid.programmer_id
           INNER JOIN (SELECT id
                         FROM subway.hospital
                        WHERE name = '서울대병원'
                       ) hospital
                   ON hospital.id = covid.hospital_id
     GROUP BY covid.stay
    ```
    ![실행계획](/result/step4/query4_explain.png)
    ![실행결과](/result/step4/query4_output.png)

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
    ```
    SELECT programmer.exercise, COUNT(*) AS COUNT
      FROM subway.covid
           INNER JOIN (SELECT id
                         FROM subway.member
                        WHERE age BETWEEN '30' AND 39
                       ) member
                   ON member.id = covid.member_id
           INNER JOIN (SELECT id, exercise
                         FROM subway.programmer
                       ) programmer
                   ON programmer.id = covid.programmer_id
           INNER JOIN (SELECT id
                         FROM subway.hospital
                        WHERE name = '서울대병원'
                       ) hospital
                   ON hospital.id = covid.hospital_id
     GROUP BY programmer.exercise
    ```
    ![실행계획](/result/step4/query5_explain.png)
    ![실행결과](/result/step4/query5_output.png)

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
