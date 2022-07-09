SELECT
    covid.id,
    hospital.name,
    programmer.hobby,
    programmer.dev_type,
    programmer.years_coding
FROM programmer
JOIN covid
  ON covid.programmer_id = programmer.id
JOIN hospital
  ON hospital.id = covid.hospital_id
WHERE programmer.hobby = 'Yes'
  AND (programmer.student LIKE 'Yes%' OR programmer.years_coding = '0-2 years')
ORDER BY programmer.id;
