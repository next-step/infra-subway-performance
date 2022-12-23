### 1. Coding as a Hobby 와 같은 결과를 반환하세요.
```sql
CREATE INDEX `idx_programmer_hobby`  ON `subway`.`programmer` (hobby) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

EXPLAIN
SELECT 
round((yes.count / total.sum) * 100, 1) Yes
, round((no.count / total.sum) * 100, 1) No
FROM(
      (SELECT count(hobby) count FROM programmer WHERE hobby = 'NO' GROUP BY hobby) no,
      (SELECT count(hobby) count FROM programmer WHERE hobby = 'Yes' GROUP BY hobby) yes,
      (SELECT count(hobby) sum FROM programmer) total
)
;
```

### 2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
ALTER TABLE `programmer` ADD CONSTRAINT `pk_programmer` PRIMARY KEY(`id`);
CREATE INDEX `idx_covid_programmer_id`  ON `subway`.`covid` (programmer_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;
CREATE INDEX `idx_covid_hospital_id`  ON `subway`.`covid` (hospital_id) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;
ALTER TABLE `hospital` ADD CONSTRAINT `pk_hospital` PRIMARY KEY(`id`);

EXPLAIN
SELECT c.id, h.name 
FROM programmer p
INNER JOIN covid c ON p.id = c.programmer_id
INNER JOIN hospital h ON h.id = c.hospital_id;
```

### 3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
```sql
SELECT c.id, h.name, p.hobby, p.dev_type, p.years_coding
FROM programmer p
INNER JOIN covid c
 ON c.programmer_id = p.id 
INNER JOIN hospital h
 ON h.id = c.hospital_id
WHERE hobby = 'Yes' AND student IN ('Yes, full-time', 'Yes, part-time') OR years_coding = '0-2 years' 
ORDER BY p.id
;
```

### 4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
```sql
ALTER TABLE `member` ADD CONSTRAINT `pk_member` PRIMARY KEY(`id`);
CREATE INDEX `idx_covid_stay`  ON `subway`.`covid` (stay) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;
CREATE INDEX `idx_hospital_name`  ON `subway`.`hospital` (name) COMMENT '' ALGORITHM DEFAULT LOCK DEFAULT;

EXPLAIN
SELECT c.stay, count(c.stay) 
FROM programmer p
INNER JOIN member m
  ON m.id = p.member_id 
INNER JOIN covid c
  ON c.programmer_id = p.id
INNER JOIN hospital h
  ON h.id = c.hospital_id
where p.country = 'India' and m.age between '20' and '29' and h.name ='서울대병원'
group by c.stay
;
```

### 5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
```sql
SELECT p.exercise, count(p.exercise) 
FROM programmer p
INNER JOIN member m
  ON m.id = p.member_id 
INNER JOIN covid c
  ON c.programmer_id = p.id
INNER JOIN hospital h
  ON h.id = c.hospital_id
where m.age between '30' and '39' and h.name ='서울대병원'
group by p.exercise
;
```
