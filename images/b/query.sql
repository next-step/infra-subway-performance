-- 1. Coding as a Hobby 와 같은 결과를 반환하세요.
SELECT hobby, CONCAT(ROUND(COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100, 1), '%') AS RESPONSE
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;

SELECT hobby, CONCAT(FORMAT(ROUND(COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100), 1), '%') AS response
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;


-- 2.
-- is not null이 index를 타지 않아 full scan을 하게 됨 최대성능
SELECT covid.id, hospital.name
FROM hospital, covid
WHERE hospital.id = covid.hospital_id
  AND covid.programmer_id IS NOT NULL
order by covid.id