##### Q5. 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
```sql
SELECT p.exercise, COUNT(*)
FROM (SELECT id FROM member WHERE age >= 30 AND age < 40) AS a
         INNER JOIN programmer p ON a.id = p.member_id
         INNER JOIN covid c ON c.programmer_id = p.id
         INNER JOIN hospital h ON c.hospital_id = h.id
WHERE h.name = '서울대병원'
GROUP BY p.exercise
ORDER BY NULL
```

##### BEFORE 
```text
+--+-----------+------+----------+------+-------------+-------+-------+------------------+------+--------+--------------------------------------------------+
|id|select_type|table |partitions|type  |possible_keys|key    |key_len|ref               |rows  |filtered|Extra                                             |
+--+-----------+------+----------+------+-------------+-------+-------+------------------+------+--------+--------------------------------------------------+
|1 |SIMPLE     |h     |NULL      |ALL   |NULL         |NULL   |NULL   |NULL              |32    |10      |Using where; Using temporary                      |
|1 |SIMPLE     |c     |NULL      |ALL   |NULL         |NULL   |NULL   |NULL              |315397|10      |Using where; Using join buffer (Block Nested Loop)|
|1 |SIMPLE     |p     |NULL      |ALL   |NULL         |NULL   |NULL   |NULL              |71210 |10      |Using where; Using join buffer (Block Nested Loop)|
|1 |SIMPLE     |member|NULL      |eq_ref|PRIMARY      |PRIMARY|8      |subway.p.member_id|1     |11.11   |Using where                                       |
+--+-----------+------+----------+------+-------------+-------+-------+------------------+------+--------+--------------------------------------------------+

```

##### AFTER
```text
+--+-----------+----------+----------+------+----------------------------+--------------------+-------+---------------------------+-----+--------+----------------------------+
|id|select_type|table     |partitions|type  |possible_keys               |key                 |key_len|ref                        |rows |filtered|Extra                       |
+--+-----------+----------+----------+------+----------------------------+--------------------+-------+---------------------------+-----+--------+----------------------------+
|1 |SIMPLE     |hospital  |NULL      |const |PRIMARY,hospital_name_uindex|hospital_name_uindex|1022   |const                      |1    |100     |Using index; Using temporary|
|1 |SIMPLE     |covid     |NULL      |ref   |covid__index001             |covid__index001     |8      |const                      |10165|100     |Using index condition       |
|1 |SIMPLE     |programmer|NULL      |eq_ref|PRIMARY                     |PRIMARY             |8      |subway.covid.programmer_id |1    |10      |Using where                 |
|1 |SIMPLE     |member    |NULL      |eq_ref|PRIMARY,member__index001    |PRIMARY             |8      |subway.programmer.member_id|1    |44.16   |Using where                 |
+--+-----------+----------+----------+------+----------------------------+--------------------+-------+---------------------------+-----+--------+----------------------------+

```

#### 변경 내용
 ```sql
ALTER TABLE covid MODIFY id bigint NOT NULL;
ALTER TABLE covid MODIFY hospital_id bigint NOT NULL;
CREATE INDEX covid__index001 ON covid (hospital_id, programmer_id);
ALTER TABLE covid ADD CONSTRAINT covid_pk PRIMARY KEY (id);

ALTER TABLE member MODIFY age int NOT NULL; 
CREATE INDEX member__index001 ON member (age);

ALTER TABLE programmer MODIFY id bigint NOT NULL;
ALTER TABLE programmer MODIFY exercise varchar(255) NOT NULL;
CREATE INDEX programmer__index001 ON programmer (exercise, member_id, id);
ALTER TABLE programmer ADD CONSTRAINT programmer_pk PRIMARY KEY (id);

ALTER TABLE hospital MODIFY name varchar(255) NOT NULL;
CREATE UNIQUE INDEX hospital_name_uindex ON hospital (name);
 ```

#### 결과
- 10 rows retrieved starting from 1 in 74 ms (execution: 44 ms, fetching: 30 ms)
