-- TABLE 별 PK / index 추가
ALTER TABLE programmer
    ADD PRIMARY KEY (id);
ALTER TABLE covid
    ADD PRIMARY KEY (id);
ALTER TABLE hospital
    ADD PRIMARY KEY (id);
ALTER TABLE member
    ADD PRIMARY KEY (id);

CREATE INDEX programmer_hobby_idx ON programmer (hobby);
CREATE INDEX covid_programmer_id_idx ON covid (programmer_id);
CREATE INDEX covid_hospital_id_idx ON covid (hospital_id);
CREATE INDEX UNIQUE hospital_name_idx ON hospital (name);

-- Coding as a Hobby 와 같은 결과를 반환
SELECT hobby,
       ROUND(COUNT(hobby) / (SELECT COUNT(hobby) FROM programmer) * 100, 2) AS 'Coding as Hobby'
FROM programmer
GROUP BY hobby;

-- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
SELECT co.id, co.hospital_id
FROM programmer prog
         INNER JOIN co
                    ON prog.id = co.programmer_id
         INNER JOIN hosp
                    ON co.hospital_id = hosp.id;

-- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
SELECT co.id AS covid_id, hosp.name, prog.hobby, prog.dev_type, prog.years_coding
FROM programmer AS prog
         INNER JOIN covid AS co
                    ON co.programmer_id = prog.id
         INNER JOIN hospital hosp
                    ON co.hospital_id = hosp.id
WHERE (prog.hobby = 'yes' AND prog.student LIKE 'yes%')
   OR prog.years_coding = '0-2 years'
ORDER BY prog.id;

-- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
SELECT co.stay, COUNT(co.stay) AS count
FROM hospital hosp
    INNER JOIN covid co
ON co.hospital_id = hosp.id
    AND hosp.name = '서울대병원'
    INNER JOIN programmer prog
    ON prog.id = co.programmer_id
    AND prog.country = 'India'
    INNER JOIN member mem
    ON mem.id = co.member_id
    AND mem.age like '2%'
GROUP BY co.stay;

-- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
SELECT prog.exercise, COUNT(prog.exercise) AS count
FROM hospital hosp
    INNER JOIN covid co
ON co.hospital_id = hosp.id
    AND hosp.name = '서울대병원'
    INNER JOIN programmer prog
    ON prog.id = co.programmer_id
    INNER JOIN member mem
    ON mem.id = co.member_id
    AND mem.age like '3%'
GROUP BY prog.exercise;


