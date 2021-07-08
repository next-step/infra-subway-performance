-- 조회 쿼리
SELECT p.id, h.name
FROM programmer p
INNER JOIN covid c
    ON c.programmer_id = p.id
INNER JOIN hospital h
    ON h.id = c.hospital_id
WHERE p.hobby = 'Yes'
  AND (p.dev_type = 'Student' OR p.years_coding = '0-2 years');
-- 조회 쿼리

-- PK 설정 전 쿼리 수행 시간 15808ms , Table Full Scan

-- PK 생성 쿼리
ALTER TABLE `subway`.`programmer`
    CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL ,
    ADD PRIMARY KEY (`id`);
-- PK 생성 쿼리

-- PK 설정 후 쿼리 수행 시간 13ms, Unique Key LookUp
