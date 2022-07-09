SELECT
    covid.id AS covid_id,
    hospital.name AS hospital_name
FROM programmer
JOIN covid
  ON covid.programmer_id = programmer.id
JOIN hospital
  ON hospital.id = covid.hospital_id
