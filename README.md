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
```sql
SELECT DRI.사원번호, DRI.이름, DRI.연봉, DRI.직급명, AR.입출입시간, AR.지역, AR.입출입구분
  FROM 사원출입기록 AR
  JOIN (
        SELECT PY.사원번호, MAX(PY.연봉) AS 연봉, HR.이름, RK.직급명
          FROM 급여 PY
          JOIN 부서관리자 BA ON (BA.사원번호 = PY.사원번호)
          JOIN 부서 DM ON (DM.부서번호 = BA.부서번호)
          JOIN 사원 HR ON (HR.사원번호 = PY.사원번호)
          JOIN 직급 RK ON (RK.사원번호 = HR.사원번호)
          WHERE 'ACTIVE' = upper(DM.비고)
            AND PY.종료일자 = '9999-01-01'
            AND BA.종료일자 = '9999-01-01'
            AND RK.직급명 = 'Manager'
          GROUP BY PY.사원번호
          ORDER BY MAX(PY.연봉) DESC
          LIMIT 5
    ) DRI ON (DRI.사원번호 = AR.사원번호)
 WHERE AR.입출입구분 = 'O'
 ORDER BY DRI.연봉 DESC;
```

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
```sql
# Q.프로그래머별로 해당하는 병원 이름을 반환하세요.
# (covid.id, hospital.name)
# A. 테이블의 PK 인덱스가 없어 TABLE FULL SCAN으로 응답이 느렸습니다.
# 따라서 covid, hospital, programmer 테이블의 id를 PRIMARY KEY로 선언하여 쿼리 속도를 개선하였습니다.
ALTER TABLE covid ADD PRIMARY KEY(id);
ALTER TABLE hospital ADD PRIMARY KEY(id);
ALTER TABLE programmer ADD PRIMARY KEY(id);
# QUERY
SELECT CV.id, HP.name
FROM covid CV
         JOIN programmer PM ON (PM.ID = CV.ID)
         JOIN hospital HP ON (HP.id = CV.hospital_id);


# Q.프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
# (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
# A. progammer의 hobbey와 years_coding이 조회 조건이므로 인덱스(idx_programmer_hobby_yearsCoding)를 추가하여 쿼리 속도를 개선하였습니다.
ALTER TABLE programmer ADD INDEX idx_programmer_hobby_yearsCoding(hobby, years_coding);
# QUERY
SELECT PM.id, HP.name, PM.hobby, PM.dev_type, PM.years_coding
FROM programmer PM
         JOIN covid CV ON (CV.id = PM.id)
         JOIN hospital HP ON (HP.id = CV.hospital_id)
WHERE PM.hobby = 'Yes' OR PM.years_coding = '0-2 years';


# Q.서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
# (covid.Stay)
# A. 조회 조건에 해당하는 컬럼(HP.name, PM.country, PM.member_id, MB.age)을 개별 인덱스로 추가하여 쿼리 속도를 개선하였습니다.
ALTER TABLE member ADD PRIMARY KEY(id);
ALTER TABLE member ADD INDEX idx_member_age(age);
ALTER TABLE programmer ADD INDEX idx_programmer_country(member_id, country);
ALTER TABLE covid ADD INDEX idx_covid_hospital_id(hospital_id);
ALTER TABLE hospital ADD INDEX idx_hospital_name(name);
# QUERY
SELECT CV.stay, COUNT(CV.stay)
FROM covid CV
         JOIN hospital HP ON (HP.id = CV.hospital_id)
         JOIN programmer PM ON (PM.member_id = CV.member_id)
         JOIN member MB ON (MB.id = PM.member_id)
WHERE HP.name = '서울대병원'
AND PM.country = 'India'
AND PM.member_id IS NOT NULL
AND MB.age BETWEEN 20 AND 29
GROUP by CV.stay;


# Q.서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
# (user.Exercise)
# A. 위 과정을 진행하며 필요한 인덱스를 추가하여 빠르게 조회할 수 있었습니다.
# QUERY
SELECT PM.exercise, COUNT(PM.exercise)
FROM programmer PM
         JOIN member MB ON (MB.id = PM.member_id)
         JOIN covid CV ON (CV.member_id = MB.id)
         JOIN hospital HP ON (HP.id = CV.hospital_id)
WHERE HP.name = '서울대병원'
AND PM.member_id IS NOT NULL
AND MB.age BETWEEN 30 AND 39
GROUP BY PM.exercise
ORDER BY count(PM.exercise) DESC;

# 참고. 쿼리 총 개수 조회(범위 확인)
SELECT
    table_name,
    table_rows,
    round(data_length/(1024*1024),2) as 'DATA_SIZE(MB)',
        round(index_length/(1024*1024),2) as 'INDEX_SIZE(MB)'
FROM information_schema.TABLES
WHERE table_schema = 'subway';
```

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
