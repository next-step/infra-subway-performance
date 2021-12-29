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

---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

```sql
SELECT 사원.사원번호, 사원.이름, 사원.연봉, 직급.직급명, 사원출입기록.입출입시간, 사원출입기록.지역, 사원출입기록.입출입구분
FROM (
       SELECT 사원.사원번호, 사원.이름, 급여.연봉
       FROM 사원, 부서, 부서관리자, 급여, 부서사원_매핑
       WHERE 사원.사원번호 = 부서사원_매핑.사원번호
         AND 사원.사원번호 = 부서관리자.사원번호
         AND 사원.사원번호 = 급여.사원번호
         AND 부서사원_매핑.부서번호 = 부서.부서번호
         AND 부서.비고 = 'active'
         AND now() BETWEEN 부서사원_매핑.시작일자 and 부서사원_매핑.종료일자
         AND now() BETWEEN 급여.시작일자 and 급여.종료일자
         AND now() BETWEEN 부서관리자.시작일자 and 부서관리자.종료일자
       ORDER BY 급여.연봉 DESC LIMIT 5
     ) 사원, 직급, 사원출입기록
WHERE 사원.사원번호 = 직급.사원번호
  AND 사원.사원번호 = 사원출입기록.사원번호
  AND 사원출입기록.입출입구분 = 'O'
  AND now() BETWEEN 직급.시작일자 AND 직급.종료일자
ORDER BY 사원.연봉 DESC, 사원출입기록.지역;
```
* 급여 - 사용여부 인덱스 추가
* 부서관리자 - 사원번호, 시작일자, 종료일자 인덱스 추가
* 사원출입기록 - 사원번호 인덱스 추가

```sql
SELECT
    ROUND((SUM(CASE WHEN hobby = 'YES' THEN 1 ELSE 0 END) / COUNT(hobby) * 100), 1) AS Yes,
    ROUND((SUM(CASE WHEN hobby = 'YES' THEN 0 ELSE 1 END) / COUNT(hobby) * 100), 1) AS No
FROM programmer;
```
* programmer - hobby 인덱스 추가

```sql
SELECT covid.id, hospital.name 
FROM covid, hospital, programmer
WHERE covid.hospital_id = hospital.id
  AND covid.programmer_id = programmer.id;
```

* covid - pk 추가, hospital_id, programmer_id 인덱스 추가
* hospital - pk 추가, name 인덱스 추가
* programmer - pk 추가

```sql
SELECT 
       cp.id, hospital.name, cp.hobby, cp.dev_type, cp.years_coding 
FROM 
(
    SELECT 
           covid.id, covid.hospital_id, p.hobby, p.dev_type, p.years_coding
    FROM covid, member, (SELECT * FROM programmer WHERE years_coding = '0-2 years' OR  programmer.student != 'No') p
    WHERE covid.programmer_id = p.id
    AND covid.member_id = member.id
    AND p.member_id = member.id
) cp, hospital
WHERE cp.hospital_id = hospital.id;
```

* programmer - member_id 인덱스 추가
* member - pk 추가

```sql
SELECT covid.stay, COUNT(*)
FROM covid,
(SELECT id FROM hospital WHERE name = '서울대병원') h,
(SELECT m.id 
FROM (SELECT member_id FROM programmer WHERE country = 'India') p,
(SELECT id FROM member WHERE age > 19 AND age < 30) m
WHERE p.member_id = m.id) m
WHERE covid.hospital_id = h.id
AND covid.member_id = m.id
GROUP BY covid.stay;
```

* programmer - country 인덱스 추가
* member - age 인덱스 추가
* covid - stay 인덱스 추가

```sql
SELECT programmer.exercise, COUNT(programmer.exercise) 
FROM programmer,
(SELECT programmer_id
FROM covid,
(SELECT id FROM hospital WHERE name = '서울대병원') h,
(SELECT id FROM member WHERE age > 29 AND age < 40) m
WHERE covid.hospital_id = h.id
AND covid.member_id = m.id) c
WHERE programmer.id = c.programmer_id
GROUP BY programmer.exercise;
```

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

