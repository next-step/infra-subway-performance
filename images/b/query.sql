-- 1. Coding as a Hobby 와 같은 결과를 반환하세요. execution: 71 ms
SELECT hobby,
       CONCAT(
               ROUND(COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100, 1)
           , '%') AS response
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;

SELECT hobby,
       CONCAT(
               FORMAT(
                       ROUND(COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100)
                   , 1)
           , '%') AS response
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;


-- 2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
-- is not null이 index를 타지 않아 full scan을 하게 됨
-- execution: 29 ms
SELECT B.id, A.name
FROM hospital AS A
         INNER JOIN (
    SELECT id, hospital_id
    FROM covid
    WHERE programmer_id <> 'null'
) AS B ON A.id = B.hospital_id;

-- 3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
-- (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
-- execution: 12 ms
SELECT B.id, C.name, A.hobby, A.dev_type devType, A.years_coding yearsCoding
FROM (SELECT id, hobby, dev_type, years_coding
      FROM programmer
      WHERE hobby = 'Yes'
        AND (student LIKE 'Yes%' OR years_coding = '0-2 years')) AS A
         INNER JOIN (SELECT id, programmer_id, hospital_id FROM covid WHERE programmer_id IS NOT NULL) AS B
                    ON A.id = B.programmer_id
         INNER JOIN hospital C ON B.hospital_id = C.id

-- 4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
-- (covid.Stay)
-- execution: 92 ms
SELECT D.stay
FROM (SELECT id FROM hospital WHERE name = '서울대병원') AS A,
     (SELECT id, member_id
      FROM programmer
      WHERE country = 'India') AS B
         INNER JOIN (SELECT id FROM member WHERE age >= 20 AND age < 30) AS C ON B.member_id = C.id
         INNER JOIN (SELECT stay, programmer_id, hospital_id
                     FROM covid
                     WHERE programmer_id IS NOT NULL) AS D ON B.id = D.programmer_id
WHERE A.id = D.hospital_id

-- 5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
-- (user.Exercise)
-- execution: 31 ms

SELECT programmer.exercise, count(*) as response
FROM hospital,
     member,
     covid,
     programmer
WHERE covid.member_id = member.id
  AND covid.hospital_id = hospital.id
  AND member.age >= 30
  AND member.age < 40
  AND hospital.name = '서울대병원'
  AND covid.programmer_id = programmer.id
GROUP BY programmer.exercise
ORDER BY NULL;