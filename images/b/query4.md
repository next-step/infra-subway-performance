##### Q4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
```sql
SELECT covid.stay, count(*) as count
FROM member
    INNER JOIN programmer ON member.id = programmer.member_id
    INNER JOIN covid ON programmer.id = covid.programmer_id
    INNER JOIN hospital ON covid.hospital_id = hospital.id
WHERE programmer.country = 'India'
  AND member.age >= 20 AND member.age < 30
  AND hospital.name = '서울대병원'
GROUP BY covid.stay
ORDER BY NULL;
```

##### BEFORE
```text
+--+-----------+----------+----------+------+-------------+-------+-------+---------------------------+------+--------+--------------------------------------------------+
|id|select_type|table     |partitions|type  |possible_keys|key    |key_len|ref                        |rows  |filtered|Extra                                             |
+--+-----------+----------+----------+------+-------------+-------+-------+---------------------------+------+--------+--------------------------------------------------+
|1 |SIMPLE     |hospital  |NULL      |ALL   |NULL         |NULL   |NULL   |NULL                       |32    |10      |Using where; Using temporary                      |
|1 |SIMPLE     |programmer|NULL      |ALL   |NULL         |NULL   |NULL   |NULL                       |71210 |10      |Using where; Using join buffer (Block Nested Loop)|
|1 |SIMPLE     |member    |NULL      |eq_ref|PRIMARY      |PRIMARY|8      |subway.programmer.member_id|1     |11.11   |Using where                                       |
|1 |SIMPLE     |covid     |NULL      |ALL   |NULL         |NULL   |NULL   |NULL                       |315397|1       |Using where; Using join buffer (Block Nested Loop)|
+--+-----------+----------+----------+------+-------------+-------+-------+---------------------------+------+--------+--------------------------------------------------+

```

##### AFTER
```text
+--+-----------+----------+----------+------+-----------------------------------------------+--------------------+-------+---------------------------+------+--------+------------------------+
|id|select_type|table     |partitions|type  |possible_keys                                  |key                 |key_len|ref                        |rows  |filtered|Extra                   |
+--+-----------+----------+----------+------+-----------------------------------------------+--------------------+-------+---------------------------+------+--------+------------------------+
|1 |SIMPLE     |hospital  |NULL      |const |PRIMARY,hospital_name_uindex,hospital__index001|hospital_name_uindex|1022   |const                      |1     |100     |Using index             |
|1 |SIMPLE     |covid     |NULL      |index |covid__index001                                |covid__index001     |1039   |NULL                       |315501|10      |Using where; Using index|
|1 |SIMPLE     |programmer|NULL      |eq_ref|PRIMARY,programmer__index001                   |PRIMARY             |8      |subway.covid.programmer_id |1     |35.14   |Using where             |
|1 |SIMPLE     |member    |NULL      |eq_ref|PRIMARY,member__index001                       |PRIMARY             |8      |subway.programmer.member_id|1     |11.11   |Using where             |
+--+-----------+----------+----------+------+-----------------------------------------------+--------------------+-------+---------------------------+------+--------+------------------------+
```

#### 변경 내용
```sql
ALTER TABLE hospital
    MODIFY id int NOT NULL;

ALTER TABLE hospital
    MODIFY name varchar(255) NOT NULL;

CREATE INDEX hospital__index001
    ON hospital (name);

CREATE UNIQUE INDEX hospital_name_uindex
    ON hospital (name);

ALTER TABLE hospital
    ADD CONSTRAINT hospital_pk
        PRIMARY KEY (id);

ALTER TABLE covid
    MODIFY id bigint NOT NULL;

ALTER TABLE covid
    MODIFY hospital_id bigint NOT NULL;

ALTER TABLE covid
    MODIFY stay varchar(255) NOT NULL;

CREATE INDEX covid__index001
    ON covid (stay, programmer_id, hospital_id);

ALTER TABLE covid
    ADD CONSTRAINT covid_pk
        PRIMARY KEY (id);

ALTER TABLE programmer
    MODIFY id bigint NOT NULL;

ALTER TABLE programmer
    MODIFY country varchar(255) NOT NULL;

CREATE INDEX programmer__index001
    ON programmer (member_id, id, country);

ALTER TABLE programmer
    ADD CONSTRAINT programmer_pk
        PRIMARY KEY (id);

ALTER TABLE member
    MODIFY age int NOT NULL;

CREATE INDEX member__index001
    ON member (id, age);
```
#### 결과
- 10 rows retrieved starting from 1 in 160 ms (`execution: 139 ms`, fetching: 21 ms)
