-- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
-- 1. Coding as a Hobby 와 같은 결과(98,855 responses 80.8 yes 19.2 no)를 반환하세요.
-- id 컬럼 PK 추가 : 0.219 -> 0.187
-- (id, hobby) 인덱스 추가 : 0.187 -> 0.063
SELECT COUNT(1) AS RESPONSES, SUM(A.YES) AS YES, SUM(A.NO) AS NO
FROM (
SELECT CASE WHEN hobby = 'Yes' THEN 1 ELSE 0 END AS YES, CASE WHEN hobby = 'No' THEN 1 ELSE 0 END AS NO
FROM subway.programmer
) A
;

-- 2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
-- fetch : 1.469 s
-- covid id/ hospital id PK 지정 및 covid 테이블에 hospital_id 인덱스 추가 후 fetch : 0.453
-- duration 은 0.000 s 로 동일
SELECT A.id, B.name
FROM subway.covid AS A, subway.hospital AS B
WHERE A.hospital_id = B.id;

-- 3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
-- duration : 1.797 s / fetch : 0.063 s
-- covid 테이블 programmer_id 인덱스 추가 후 0.015 s / 0.516 s
-- programmer 테이블 (id, years_coding) 인덱스 추가 후 0.016 s / 0.438 s
SELECT covid.id, hospital.name, user.Hobby, user.dev_type, user.years_coding
FROM subway.covid as covid
, subway.hospital as hospital
, subway.programmer as user
WHERE covid.hospital_id = hospital.id
  AND covid.programmer_id = user.id
  AND ((user.hobby = 'Yes' and user.dev_type = 'Student') OR (user.years_coding = '0-2 years'))
order by user.id;

-- 4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
-- duration : 0.625 s
-- member 테이블 age 인덱스 추가 후 0.093 s
SELECT a.stay ,count(1)
FROM covid a
, hospital b
, programmer c
, member d
WHERE a.hospital_id = b.id
  AND a.programmer_id = c.id
  AND a.member_id = d.id
  AND b.name = '서울대병원'
  AND c.country = 'India'
  AND d.age between 20 and 29
group by stay
order by stay;

-- 5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
-- duration : 0.063s
SELECT c.exercise, count(1)
FROM covid a
, hospital b
, programmer c
, member d
WHERE a.hospital_id = b.id
  AND a.programmer_id = c.id
  AND a.member_id = d.id
  AND b.name = '서울대병원'
  AND d.age between 30 and 39
group by exercise
order by exercise;