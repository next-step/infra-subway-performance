##### Q2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
SELECT covid.id, name
FROM covid
         INNER JOIN hospital ON covid.hospital_id = hospital.id
WHERE covid.programmer_id IS NOT NULL;

```

##### BEFORE
```text
+--+-----------+--------+----------+----+-------------+----+-------+----+------+--------+--------------------------------------------------+
|id|select_type|table   |partitions|type|possible_keys|key |key_len|ref |rows  |filtered|Extra                                             |
+--+-----------+--------+----------+----+-------------+----+-------+----+------+--------+--------------------------------------------------+
|1 |SIMPLE     |hospital|NULL      |ALL |NULL         |NULL|NULL   |NULL|32    |100     |NULL                                              |
|1 |SIMPLE     |covid   |NULL      |ALL |NULL         |NULL|NULL   |NULL|315397|9       |Using where; Using join buffer (Block Nested Loop)|
+--+-----------+--------+----------+----+-------------+----+-------+----+------+--------+--------------------------------------------------+
```

##### AFTER
```text
+--+-----------+-----+----------+------+--------------------------+---------------+-------+--------------------+------+--------+------------------------+
|id|select_type|table|partitions|type  |possible_keys             |key            |key_len|ref                 |rows  |filtered|Extra                   |
+--+-----------+-----+----------+------+--------------------------+---------------+-------+--------------------+------+--------+------------------------+
|1 |SIMPLE     |c    |NULL      |range |covid__index001           |covid__index001|9      |NULL                |158949|100     |Using where; Using index|
|1 |SIMPLE     |h    |NULL      |eq_ref|PRIMARY,hospital_id_uindex|PRIMARY        |4      |subway.c.hospital_id|1     |100     |Using where             |
+--+-----------+-----+----------+------+--------------------------+---------------+-------+--------------------+------+--------+------------------------+
```

#### 변경 내용
 ```sql
ALTER TABLE covid
    ADD CONSTRAINT covid_pk PRIMARY KEY (id);
ALTER TABLE covid
    MODIFY id bigint NOT NULL;
ALTER TABLE covid
    MODIFY hospital_id bigint NOT NULL;
CREATE INDEX covid__index001 ON covid (programmer_id, hospital_id);

ALTER TABLE programmer
    MODIFY id bigint NOT NULL;
ALTER TABLE programmer
    ADD CONSTRAINT programmer_pk PRIMARY KEY (id);

ALTER TABLE hospital
    MODIFY id int NOT NULL;
ALTER TABLE hospital
    ADD CONSTRAINT hospital_pk PRIMARY KEY (id);
 ```

#### 결과
- 96,180 rows retrieved starting from 1 in 783 ms (`execution: 14 ms`, fetching: 769 ms)
