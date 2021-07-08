-- 조회 쿼리
SELECT p.exercise, count(1) AS count
FROM programmer p
    INNER JOIN covid c
        ON c.programmer_id = p.id
        AND c.hospital_id = (SELECT id FROM hospital WHERE name = '서울대병원')
        AND c.member_id IN (SELECT id FROM subway.member WHERE age >= 30 AND age < 40)
GROUP BY p.exercise;
-- 조회 쿼리

-- 인덱스 설정 전 쿼리 수행 시간 77ms
-- 목표수치에 부합해서 추가 인덱스 설정 하지 않음
