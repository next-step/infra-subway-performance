##1. Coding as a Hobby 와 같은 결과를 반환하세요.
###쿼리문
```SQL
SELECT (COUNT(id) / (SELECT COUNT(id) FROM subway.programmer) * 100) as 'Hobby' 
FROM subway.programmer
GROUP BY hobby;
```
###개선 사항
1. Programmer 테이블의 id 칼럼 PK 추가
2. Programmer 테이블의 hobby 칼럼 index 추가

### 속도 비교
|개선 전|개선 후|
|:---------|-------|
|0.204 sec | 0.032 sec|

##2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
###쿼리문
```SQL
SELECT  co.id,
        ho.name
FROM subway.covid AS co	JOIN subway.hospital AS ho
                          ON ho.id = co.hospital_id;
```
###개선 사항
1. Hospital 테이블의 id 칼럼 PK 추가
2. Covid 테이블의 id 칼럼 PK 추가

### 속도 비교
|개선 전|개선 후|
|:---------|-------|
|0.016 sec | 0.000 sec|

##3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.(covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
###쿼리문
```SQL
SELECT pro.id, coho.name
FROM ( SELECT *
       FROM subway.programmer
       WHERE hobby = 'Yes' AND (student LIKE 'Yes%' OR years_coding = '0-2 years')) AS pro
JOIN ( SELECT co.id, ho.name FROM subway.covid as co 
                            JOIN subway.hospital AS ho ON ho.id = co.hospital_id) AS coho
ON coho.id = pro.id;
```
###개선 사항
1. Programmer 테이블의 student 인덱스 추가 

### 속도 비교
|개선 전|개선 후|
|:---------|-------|
|0.016 sec | 0.000 sec|

##4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
###쿼리문
```SQL
SELECT co.stay, COUNT(co.id)
FROM subway.covid AS co
         JOIN ( SELECT id FROM subway.hospital WHERE name = '서울대병원') AS ho ON ho.id = co.hospital_id
         JOIN ( SELECT id FROM subway.programmer WHERE country = 'India') AS pro ON pro.id = co.id
         JOIN ( SELECT id FROM subway.member WHERE age BETWEEN 20 AND 29 ) AS mem ON mem.id = co.id
GROUP BY co.stay;
```
###개선 사항
1. Programmer 테이블의 country 인덱스 추가
2. Member 테이블의 age 인덱스 추가 

### 속도 비교
|개선 전|개선 후|
|:---------|-------|
|0.031 sec | 0.016 sec|

##5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
###쿼리문
```SQL
SELECT pro.exercise, count(pro.id)
FROM subway.covid AS co
         JOIN ( SELECT id FROM subway.hospital WHERE name = '서울대병원') AS ho ON ho.id = co.hospital_id
         JOIN ( SELECT id FROM subway.member WHERE age BETWEEN 30 AND 39 ) AS mem ON mem.id = co.id
         JOIN ( SELECT id, exercise FROM subway.programmer) AS pro ON pro.id = co.id
GROUP BY pro.exercise;
```
###개선 사항
1. 이미 앞 단계에서 필요한 PK와 INDEX를 추가하여 별다른 작업은 하지 않았습니다. 

### 속도 비교
|개선 전|개선 후|
|:---------|-------|
|0.047 sec | - |