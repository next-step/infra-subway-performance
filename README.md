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
- http_req_duration P(95) 기준
  - smoke : 4.66ms -> 4.1ms
    - 스크린샷 : /docs/smoke
  - load : 87.78ms -> 89.7ms
    - 스크린샷 : /docs/load
  - stress : 343.08ms -> 337.8ms
    - 스크린샷 : /docs/stress

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Reverse Proxy 개선 (/docs/nginx.conf)
  - gzip 압축
  - cache
- Spring Data Cache 설정
  - Redis Server 설정

---

### 2단계 - 스케일 아웃

- 모든 정적자원에 대해 no-cache, no-store 설정을 한다. 가능한가??
```text
no-store 만으로도 캐시가 무효화 되어야 하지만, 구 브라우저 호환성 등 여러 이유로 no-cache등의 옵션들도 함께 사용해야 합니다.
(https://www.inflearn.com/questions/112647)
```

1. Launch Template 링크를 공유해주세요.
- https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-0258d1f21cb938936

3. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```
- **_CPU 사용량_**
  - ![CPU 사용량](/docs/cpu_stress/cloudwatch_cpu.png)
- **_인스턴스 추가_**
  - ![추가 인스턴스](/docs/cpu_stress/addInstance.png)

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
- http_req_duration p(95) 값 기준.

- smoke : 4.1ms -> 3.158ms

  - **_before_**
    - ![smoke before](/docs/smoke/smoke_after_k6.png)

  - **_after_**
    - ![smoke after](/docs/smoke/smoke_autoscaling_k6.png)

- load : 89.7ms -> 35.19ms

  - **_before_**
    - ![load before](/docs/load/load_after_k6.png)

  - **_after_**
    - ![load after](/docs/load/load_autoscaling_k6.png)

- stress : 337.8ms -> 91.77ms

  - **_before_**
    - ![stress before](/docs/stress/stress_after_k6.png)

  - **_after_**
    - ![stress after](/docs/stress/stress_autoscaling_k6.png)

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
SELECT
    t.id AS 사원번호,
    t.first_name AS 이름,
    t.annual_income AS 연봉,
    t.position_name AS 직급명,
    r.region AS 지역,
    r.record_symbol AS 입출입구분,
    r.time AS 입출입시간
FROM (
    SELECT
        e.id,
        e.first_name,
        s.annual_income,
        p.position_name
    FROM department d
    JOIN manager m
        ON d.id = m.department_id
        AND NOW() BETWEEN m.start_date AND m.end_date
    JOIN position p
        ON m.employee_id = p.id
        AND p.position_name = 'Manager'
    JOIN employee e
        ON m.employee_id = e.id
    JOIN salary s
        ON m.employee_id = s.id
        AND NOW() BETWEEN s.start_date AND s.end_date
    WHERE d.note = 'active'
    ORDER BY s.annual_income DESC
    LIMIT 5
) t
JOIN record r
    ON t.id = r.employee_id
    AND r.record_symbol = 'O'
;
```

- **_result_**
  - ![duration](/docs/step3/result.png)


- **_duration_**
  - ![duration](/docs/step3/duration.png)


- **_plan_**
  - ![duration](/docs/step3/plan.png)


---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- Coding as a Hobby 와 같은 결과를 반환하세요.
  - Duration : 4.314s -> 0.464s
  - 개선점
    - 인덱스 추가1 : programmer.hobby
```sql
Select hobby, ROUND((Count(hobby)* 100 / (Select Count(*) From programmer)), 1) as Score
From programmer
Group By hobby
order by hobby desc
;
```
![plan1](/docs/step4/q1_plan.png)
<br>

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
  - Duration : 4.056s -> 0.024s
  - 개선점
    - PK 생성 1 : covid.id
    - PK 생성 2 : hospital.id
    - 인덱스 추가 1 : covid = hospital_id, programmer_id
```sql
SELECT c.id, h.name
FROM covid c
JOIN hospital h
    ON c.hospital_id = h.id
WHERE c.programmer_id IS NOT NULL
;
```
![plan2](/docs/step4/q2_plan.png)
<br>

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
  (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
  - Duration : 30.010s -> 0.042s
  - 개선점
    - PK 생성 1 : covid.id
    - PK 생성 2 : hospital.id
    - PK 생성 3 : programmer.id
    - 인덱스 추가 1 : covid = programmer_id, hospital_id
```sql
SELECT c.id, h.name, user.hobby, user.dev_type, user.years_coding
FROM (
	SELECT id, hobby, dev_type, years_coding
	FROM programmer
	WHERE years_coding = '0-2 years'
	OR (hobby = 'Yes' AND student like 'Yes%')
	ORDER BY id
) user
JOIN covid c
	ON user.id = c.programmer_id
JOIN hospital h
	ON c.hospital_id = h.id    
;
```
![plan3](/docs/step4/q3_plan.png)
<br>

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
  - Duration : 300s 이상 -> 0.563s
  - 개선점
    - PK 생성 1 : covid.id
    - PK 생성 2 : hospital.id
    - PK 생성 3 : programmer.id
    - PK 생성 4 : member.id
    - 인덱스 추가 1 : covid = member_id, hospital_id, covid.programmer_id
    - 인덱스 추가 2 : hospital.name
```sql
SELECT c.stay, COUNT(c.stay) AS count
FROM covid c
JOIN (SELECT id FROM member WHERE age BETWEEN 20 AND 29) m
	ON c.member_id = m.id
JOIN (SELECT id FROM hospital WHERE name = '서울대병원') h
	ON c.hospital_id = h.id
JOIN (SELECT id FROM programmer WHERE country = 'India') p
	ON c.programmer_id = p.id
GROUP BY c.stay
;
```
![plan4](/docs/step4/q4_plan.png)
<br>

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
  - Duration : 300s 이상 -> 0.564s
  - 개선점
    - PK 생성 1 : covid.id
    - PK 생성 2 : hospital.id
    - PK 생성 3 : programmer.id
    - PK 생성 4 : member.id
    - 인덱스 추가 1 : covid = hospital_id, member_id, covid.programmer_id
    - 인덱스 추가 2 : hospital.name
```sql
SELECT user.exercise, COUNT(user.exercise) AS count
FROM (
	SELECT p.exercise
    FROM covid c
	JOIN (SELECT id FROM member WHERE age BETWEEN 30 AND 39) m
		ON c.member_id = m.id
	JOIN (SELECT id FROM hospital WHERE name = '서울대병원') h
		ON c.hospital_id = h.id
	JOIN programmer p
		ON c.programmer_id = p.id
) user
GROUP BY user.exercise
;
```
![plan5](/docs/step4/q5_plan.png)
<br>


---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
