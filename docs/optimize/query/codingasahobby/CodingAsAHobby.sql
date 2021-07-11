-- 조회 쿼리
SELECT hobby,
       count(1) / (select count(1) from programmer) * 100 as percent
FROM programmer
GROUP BY hobby;
-- 조회 쿼리

-- 인덱스 설정 전 쿼리 수행 시간 385ms , Table Full Scan

-- 인덱스 생성 쿼리
CREATE INDEX `idx_programmer_hobby`  ON `subway`.`programmer` (hobby)
-- 인덱스 생성 쿼리

-- 인덱스 설정 후 쿼리 수행 시간 35ms , Full Index Scan
