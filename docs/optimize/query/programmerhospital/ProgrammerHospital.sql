-- 조회 쿼리
select c.id, h.name
from covid c
inner join hospital h
    on h.id = c.hospital_id;
-- 조회 쿼리

-- PK 설정 전 쿼리 수행 시간 1.9ms , Table Full Scan

-- PK 생성 쿼리
ALTER TABLE `subway`.`hospital`
    CHANGE COLUMN `id` `id` INT(11) NOT NULL ,
    ADD PRIMARY KEY (`id`);
-- PK 생성 쿼리

-- PK 설정 후 쿼리 수행 시간 1.4ms, Unique Key LookUp
