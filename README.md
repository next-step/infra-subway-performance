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
  - k6 디렉토리에 부하테스트 결과 포함
  - pagespeed 디렉토리에 웹 성능 테스트 결과 포함

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
  #### Reverse Proxy
   - gzip 압축 적용
   - 캐싱
   - HTTP2 적용
  #### WAS
   - Spring Data Cache(redis)

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplates:sort=launchTemplateId;search=lt-0bd2361c81213d79e
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```
- autoscaling 디렉토리에 Cloudwatch 캡쳐 이미지 추가해두었습니다.
- 아래와 같이 실습 진행하였습니다.
  1. 1번 서버 cpu 부하 실행
  2. CPU 사용률 평균이 50%를 넘어 2번 서버 스케일 아웃
  3. 2번 서버 스케일 아웃했음에도 CPU 사용률 평균 50%를 넘어 3,4번 서버 스케일 아웃
  4. 1번 서버 cpu 부하 종료
  5. CPU 사용률 평균이 50%대로 감소, CPU 사용률 평균이 50%가 유지되도록 1, 2, 3 서버 점진적으로 종료
  6. 마지막으로 생성된 4번 서버만 남음.

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- autoscaling 디렉토리 이하 smoke, load, stress 디렉토리에 캡쳐 이미지 추가해두었습니다.
- stress 테스트간 VU가 높아짐에 따라 자동으로 autoscaling됨을 볼수 있었습니다.
  - stress1 테스트에서 VU가 400으로 높아지자 Instance를 1개 추가로 스케일 아웃됨을 확인할 수 있었고, 안정적인 테스트 결과를 유지했습니다.
  - stress2 테스트에서 VU가 800으로 높이지자 Instance를 2개 추가로 스케일 아웃됨을 확인할 수 있었고, 약간의 지연이 발생하는것을 확인할 수 있었습니다.(약간의 지연은 단일 인스턴스로 운영중인 DB 부하가 원인이 아닐까 추측중입니다.)
  
---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 200ms 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```sql
SELECT 
	top_salary_manager.id AS id,
	top_salary_manager.name AS name,
	top_salary_manager.max_salary AS max_salary,
	top_salary_manager.position_name AS position_name,
	MAX(r.time) AS time,
	r.region AS region,
	r.record_symbol AS record_symbol
FROM(
	SELECT 
		e.id id, 
		e.last_name name,
		p.position_name position_name,
		MAX(s.annual_income) max_salary
	FROM department d
	INNER JOIN manager m ON m.department_id = d.id
		AND m.start_date < NOW()
		AND m.end_date > NOW()
	INNER JOIN employee e ON e.id = m.employee_id
	INNER JOIN position p
	ON p.id = e.id
	AND position_name = 'Manager'
	INNER JOIN salary s ON s.id = e.id
	WHERE d.note = 'active'
	GROUP BY s.id
	ORDER BY max_salary DESC
	LIMIT 5
) top_salary_manager
INNER JOIN record r
ON r.employee_id = top_salary_manager.id
AND r.record_symbol = 'O'
GROUP BY id, region
```

실행 계획 캡처본을 step3.explain 디렉토리에 동봉해두었습니다.


---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
#### Coding as a Hobby 와 같은 결과를 반환하세요.
- index 추가
  ```sql
    ALTER TABLE programmer ADD CONSTRAINT pk_programmer PRIMARY KEY (id);
    ALTER TABLE programmer ADD INDEX idx_programmer_hobby(hobby);
  ```
- 조회 쿼리
  ```sql
  SELECT 
      hobby,
      ROUND((COUNT(id) / (SELECT COUNT(id) FROM programmer) * 100), 1) as rate
  FROM programmer
  GROUP BY hobby
  ORDER BY hobby DESC
  ;
  ```
- 실행 계획
  - step4.explain 디렉토리의 hobby.png 확인
  - 45ms

#### 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
- index 추가
  ```sql
  ALTER TABLE hospital ADD CONSTRAINT pk_hospital PRIMARY KEY(id);
  ALTER TABLE covid ADD CONSTRAINT pk_covid PRIMARY KEY(id);
  ALTER TABLE covid ADD INDEX idx_covid_programmer_id(programmer_id);
  ALTER TABLE covid ADD INDEX idx_covid_hospital_id(hospital_id);
  ```
- 조회 쿼리
  ```sql
  SELECT 
      c.id, 
      h.name
  FROM hospital h
  INNER JOIN covid c ON h.id = c.hospital_id
  INNER JOIN programmer p ON c.programmer_id = p.id
  ;
  ```
- 실행 계획
  - step4.explain 디렉토리의 programmer_hospital.png 확인
  - 5ms

#### 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
- index 추가
  - 위에서 생성된 인덱스로 커버
- 조회 쿼리
  ```sql
  SELECT
      c.id,
      h.name,
      p.hobby,
      p.dev_type,
      p.years_coding
  FROM hospital h
  INNER JOIN covid c ON h.id = c.hospital_id
  INNER JOIN programmer p ON p.id = c.programmer_id
  WHERE p.hobby = 'Yes'
      AND (p.years_coding = '0-2 years' OR p.student LIKE 'yes%')
  ORDER BY p.id
  ;
  ```
- 실행 계획
  - step4.explain 디렉토리의 programmer_hobby_student_junior.png 확인
  - 5ms

#### 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
- index 추가
  ```sql
  ALTER TABLE member ADD CONSTRAINT pk_member PRIMARY KEY(id);
  ALTER TABLE hospital ADD INDEX idx_hospital_name(name);
  ALTER TABLE member ADD INDEX idx_member_age(age);
  ALTER TABLE programmer ADD INDEX idx_programmer_country(country);
  ```
- 조회 쿼리
  ```sql
  SELECT
      c.stay,
      COUNT(c.id)
  FROM hospital h
      INNER JOIN covid c ON c.hospital_id = h.id
      INNER JOIN programmer p ON p.id = c.programmer_id
      INNER JOIN member m ON m.id = c.member_id
  WHERE h.name = '서울대병원'
      AND m.age BETWEEN 20 AND 29
      AND p.country = 'india'
  GROUP BY c.stay
  ;
  ```
- 실행 계획
  - step4.explain 디렉토리의 covid_stay_count.png 확인
  - 50ms
  - pk_member, idx_hospital_name 인덱스를 타는것으로 확인
  - idx_member_age, idx_programmer_country 는 유효 인덱스이나 옵티마이저에서 태우지 않는것으로 확인.

#### 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
- index 추가
  - 위에서 생성된 인덱스로 커버
- 조회 쿼리
  ```sql
  SELECT
      p.exercise,
      COUNT(p.id)
  FROM hospital h
  INNER JOIN covid c ON c.hospital_id = h.id
  INNER JOIN programmer p ON p.id = c.programmer_id
  INNER JOIN member m ON m.id = c.member_id
  WHERE h.name = '서울대병원'
      AND m.age BETWEEN 30 AND 39
  GROUP BY p.exercise
  ;
  ```
- 실행 계획
  - step4.explain 디렉토리의 programmer_exercise_count.png 확인
  - 30ms
  - idx_hospital_name, idx_covid_hospital_id 인덱스를 타는 것으로 확인
  - 위에서와 마찬가지로 idx_member_age 는 유효 인덱스이나 옵티마이저에서 태우지 않는것으로 확인.
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
   - `/favorites?page=0&size=10`
2. MySQL Replication with JPA
  - replication 디렉토리 내용 참고
