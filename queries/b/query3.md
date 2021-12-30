##### Q3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.(covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql
SELECT c.id, h.name, p.hobby, p.dev_type as devType, p.years_coding as YearsCoding
FROM programmer p
         INNER JOIN covid c ON p.id = c.programmer_id
         INNER JOIN hospital h ON c.hospital_id = h.id
WHERE p.hobby = 'Yes'
  AND (p.student LIKE 'Yes%' OR years_coding = '0-2 years')
```

##### BEFORE
```text
+--+-----------+-----+----------+----+-------------+----+-------+----+------+--------+--------------------------------------------------+
|id|select_type|table|partitions|type|possible_keys|key |key_len|ref |rows  |filtered|Extra                                             |
+--+-----------+-----+----------+----+-------------+----+-------+----+------+--------+--------------------------------------------------+
|1 |SIMPLE     |h    |NULL      |ALL |NULL         |NULL|NULL   |NULL|32    |100     |NULL                                              |
|1 |SIMPLE     |p    |NULL      |ALL |NULL         |NULL|NULL   |NULL|71210 |2       |Using where; Using join buffer (Block Nested Loop)|
|1 |SIMPLE     |c    |NULL      |ALL |NULL         |NULL|NULL   |NULL|315397|1       |Using where; Using join buffer (Block Nested Loop)|
+--+-----------+-----+----------+----+-------------+----+-------+----+------+--------+--------------------------------------------------+

```

##### AFTER
```text
+--+-----------+-----+----------+------+----------------------------+--------------------+-------+--------------------+-----+--------+------------------------+
|id|select_type|table|partitions|type  |possible_keys               |key                 |key_len|ref                 |rows |filtered|Extra                   |
+--+-----------+-----+----------+------+----------------------------+--------------------+-------+--------------------+-----+--------+------------------------+
|1 |SIMPLE     |p    |NULL      |ref   |PRIMARY,programmer__index001|programmer__index001|258    |const               |37232|20      |Using index condition   |
|1 |SIMPLE     |c    |NULL      |ref   |covid__index001             |covid__index001     |9      |subway.p.id         |3    |100     |Using where; Using index|
|1 |SIMPLE     |h    |NULL      |eq_ref|PRIMARY                     |PRIMARY             |4      |subway.c.hospital_id|1    |100     |Using where             |
+--+-----------+-----+----------+------+----------------------------+--------------------+-------+--------------------+-----+--------+------------------------+
```

#### 변경 내용
 ```sql
ALTER TABLE programmer
    MODIFY id bigint NOT NULL;

ALTER TABLE programmer
    MODIFY hobby varchar(64) NOT NULL;

ALTER TABLE programmer
    MODIFY student varchar(64) NOT NULL;

ALTER TABLE programmer
    MODIFY years_coding varchar(255) NOT NULL;

CREATE INDEX programmer__index001
    ON programmer (hobby, student, years_coding);

ALTER TABLE programmer
    ADD CONSTRAINT programmer_pk
        PRIMARY KEY (id);

ALTER TABLE covid
    MODIFY id bigint NOT NULL;

CREATE INDEX covid__index001
    ON covid (programmer_id, hospital_id);

ALTER TABLE covid
    ADD CONSTRAINT covid_pk
        PRIMARY KEY (id);

ALTER TABLE hospital
    MODIFY id int NOT NULL;

ALTER TABLE hospital
    ADD CONSTRAINT hospital_pk
        PRIMARY KEY (id);


 ```

#### 결과
- 24537 rows retrieved starting from 1 in 675 ms (`execution: 9 ms`, fetching: 666 ms)
