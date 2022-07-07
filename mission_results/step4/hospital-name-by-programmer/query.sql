SELECT
    p.id AS programmer_id,
    h.name AS hospital_name
FROM programmer AS p
JOIN covid AS c
  ON c.programmer_id = p.id
JOIN hospital AS h
  ON h.id = c.hospital_id
