SELECT
    programmer.id,
    hospital.name
FROM programmer
JOIN covid
  ON covid.programmer_id = programmer.id
JOIN hospital
  ON hospital.id = covid.hospital_id
WHERE programmer.hobby = 'Yes'
  AND (programmer.student <> 'No' OR programmer.years_coding = '0-2 years')
ORDER BY programmer.id;
