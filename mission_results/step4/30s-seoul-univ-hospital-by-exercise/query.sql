SELECT
    p.exercise,
    COUNT(*) as count
FROM (SELECT id, hospital_id, member_id, programmer_id, stay FROM covid) AS c
JOIN (SELECT id FROM hospital WHERE name = '서울대병원') AS h
  ON c.hospital_id = h.id
JOIN (SELECT id, exercise FROM programmer) AS p
  ON p.id = c.programmer_id
JOIN (SELECT id FROM member WHERE age BETWEEN 31 AND 39) AS m
  ON m.id = c.member_id
GROUP BY p.exercise
