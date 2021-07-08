-- 조회 쿼리
SELECT c.stay, count(1) AS count
FROM covid c
WHERE c.hospital_id = (SELECT id FROM hospital WHERE name = '서울대병원')
  AND c.programmer_id IN (
                            SELECT id
                            FROM programmer
                            WHERE id in (SELECT id FROM subway.member WHERE age >= 20 AND age < 30)
                              AND country = 'India')
GROUP BY c.stay
;
-- 조회 쿼리

-- 인덱스 설정 전 쿼리 수행 시간 203ms

-- 인덱스 생성 쿼리
CREATE INDEX `idx_member_age`  ON `subway`.`member` (age);
CREATE INDEX `idx_programmer_country`  ON `subway`.`programmer` (country);
CREATE INDEX `idx_covid_hospital_id`  ON `subway`.`covid` (hospital_id);
-- 인덱스 생성 쿼리

-- 인덱스 설정 후 쿼리 수행 시간 67ms
