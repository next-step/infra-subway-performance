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

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

```
SELECT A.사원번호, A.이름, A.연봉, 직급.직급명, 사원출입기록.입출입시간, 사원출입기록.지역
FROM 
	(
	SELECT 사원.사원번호, 사원.이름, 급여.연봉 
	FROM 
		사원 
	JOIN 부서관리자 
		ON 사원.사원번호 = 부서관리자.사원번호 
	JOIN 부서
		ON 부서관리자.부서번호 = 부서.부서번호
	JOIN 급여 
		ON 사원.사원번호 = 급여.사원번호 
	WHERE  부서관리자.종료일자 >= NOW() 
	AND 부서.비고 = 'active' 
	AND 급여.종료일자 >= NOW()
	ORDER BY 연봉 DESC
	LIMIT 5
	) AS A
JOIN 직급 
	ON A.사원번호 = 직급.사원번호
JOIN 사원출입기록 
	ON A.사원번호 = 사원출입기록.사원번호 
WHERE 직급.종료일자 >= NOW()
AND 사원출입기록.입출입구분 = 'o'
```

![](https://user-images.githubusercontent.com/63947424/162609917-960fba72-5eb7-421d-8f67-03b3ffb563f0.png)


---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- Coding as a Hobby 와 같은 결과를 반환하세요.
```
CREATE INDEX `idx_hobby` ON `subway`.`programmer` (hobby);

SELECT ROUND((SELECT COUNT(*) FROM programmer WHERE programmer.hobby = 'YES') * 100.0/ (SELECT COUNT(*) FROM programmer),1) AS yes, 
	 ROUND((SELECT COUNT(*) FROM programmer WHERE programmer.hobby = 'NO') * 100.0/ (SELECT COUNT(*) FROM programmer),1) AS no
```
![](https://user-images.githubusercontent.com/63947424/163800046-264983b1-147d-4b3b-bde5-559db14c970d.png)

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```
ALTER TABLE covid ADD PRIMARY KEY (id);
ALTER TABLE programmer ADD PRIMARY KEY (id);
ALTER TABLE hospital ADD PRIMARY KEY (id);
CREATE INDEX `idx_programmer_id` ON `subway`.`covid` (programmer_id);

SELECT A.id, A.name
FROM programmer
JOIN (
	SELECT covid.id, hospital.name, covid.programmer_id
    FROM covid 
    JOIN hospital
	ON hospital.id = covid.hospital_id
    ) A
ON A.programmer_id = programmer.id
```
![](https://user-images.githubusercontent.com/63947424/163799723-c604f95c-551c-4203-a4b8-d3e6d0d3a7b4.png)
![](https://user-images.githubusercontent.com/63947424/163799728-fac9ee3f-6a39-45b7-a774-4e2034d92ec3.png)
![](https://user-images.githubusercontent.com/63947424/163908903-eea1f4f0-394f-46ea-a399-88362bc09a5e.png)


- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```
CREATE INDEX `idx_student_years_coding_hobby` ON `subway`.`programmer` (hobby, student, years_coding);

SELECT A.id, A.name, programmer.hobby, programmer.dev_type, programmer.years_coding, programmer.student
FROM (
  SELECT covid.id, hospital.name, covid.programmer_id
  FROM covid 
  JOIN hospital
    ON hospital.id = covid.hospital_id
  ) A
JOIN programmer
ON A.programmer_id = programmer.id
WHERE programmer.hobby = 'YES'
    AND (programmer.student LIKE 'YES%' OR programmer.years_coding = '0-2 years')
ORDER BY programmer.id ASC

```
![](https://user-images.githubusercontent.com/63947424/163905141-3b918b81-efff-4023-a170-134ea13a23e2.png)
![](https://user-images.githubusercontent.com/63947424/163799731-8a57ce83-433c-48b2-919c-96f89ff8fe23.png)
![](https://user-images.githubusercontent.com/63947424/163905005-9f71b6c0-bab7-4a8d-af1a-bdd6cc1312d2.png)


- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```
ALTER TABLE member ADD PRIMARY KEY (id);
CREATE INDEX `idx_name` ON `subway`.`hospital` (name);
CREATE INDEX `idx_age` ON `subway`.`member` (age);
CREATE INDEX `idx_hospital_member` ON `subway`.`covid` (hospital_id, member_id);
CREATE INDEX `idx_country_member` ON `subway`.`programmer` (country, member_id);

SELECT A.stay, COUNT(A.stay)
FROM (
	SELECT covid.stay, covid.member_id
	FROM covid
	JOIN hospital
	ON covid.hospital_id = hospital.id
	WHERE hospital.name = '서울대병원' OR hospital.name = '분당서울대병원'
)A
JOIN (
	SELECT member.id
    FROM member
    JOIN programmer
    ON programmer.member_id = member.id
	WHERE member.age BETWEEN 20 AND 29
	AND programmer.country = 'INDIA'
) B
ON A.member_id = B.id
GROUP BY A.stay
```
![](https://user-images.githubusercontent.com/63947424/163907113-a29d8341-4228-450a-a351-d48b6d911ec7.png)
![](https://user-images.githubusercontent.com/63947424/163907111-63e35bbc-b64d-42f2-bbc8-cbf2c2c39d58.png)
![](https://user-images.githubusercontent.com/63947424/163907105-f18e01f6-0317-4c79-ab9a-43ef71c7f700.png)

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```
CREATE INDEX `idx_member` ON `subway`.`programmer` (member_id);

SELECT A.exercise, COUNT(A.exercise)
FROM (
	SELECT member.id, programmer.exercise
    FROM member
    JOIN programmer
    ON programmer.member_id = member.id
	WHERE member.age BETWEEN 30 AND 39
)A
JOIN (
	SELECT covid.stay, covid.member_id
	FROM covid
	JOIN hospital
	ON covid.hospital_id = hospital.id
	WHERE hospital.name = '서울대병원' OR hospital.name = '분당서울대병원'
)B
ON A.id = B.member_id
GROUP BY A.exercise
```
![](https://user-images.githubusercontent.com/63947424/163907305-98c2aef5-f8af-4f84-a860-2d096a10999a.png)
![](https://user-images.githubusercontent.com/63947424/163907315-36e71d9f-81ed-4585-9bdd-6b358df8f95f.png)
![](https://user-images.githubusercontent.com/63947424/163907324-064d3aa9-57ff-4f7d-bc84-5a7b425a83a3.png)
---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
