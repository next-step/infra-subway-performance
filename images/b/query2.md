##### Q2. 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
SELECT covid.id, name
FROM programmer
         INNER JOIN covid ON programmer.id = covid.programmer_id
         INNER JOIN hospital ON covid.hospital_id = hospital.id;
```

##### BEFORE (약 490ms)
```text
+--+-----------+----------+----------+----+--------------------+--------------------+-------+--------------------------+------+--------+--------------------------------------------------+
|id|select_type|table     |partitions|type|possible_keys       |key                 |key_len|ref                       |rows  |filtered|Extra                                             |
+--+-----------+----------+----------+----+--------------------+--------------------+-------+--------------------------+------+--------+--------------------------------------------------+
|1 |SIMPLE     |h         |NULL      |ALL |NULL                |NULL                |NULL   |NULL                      |32    |100     |NULL                                              |
|1 |SIMPLE     |covid     |NULL      |ALL |NULL                |NULL                |NULL   |NULL                      |315397|10      |Using where; Using join buffer (Block Nested Loop)|
|1 |SIMPLE     |programmer|NULL      |ref |programmer_id_uindex|programmer_id_uindex|9      |subway.covid.programmer_id|1     |100     |Using index                                       |
+--+-----------+----------+----------+----+--------------------+--------------------+-------+--------------------------+------+--------+--------------------------------------------------+
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
alter table covid modify id bigint not null;
create unique index covid_id_uindex	on covid (id);
alter table covid	add constraint covid_pk	primary key (id);

alter table programmer modify id bigint not null;
alter table programmer add constraint programmer_pk primary key (id);

alter table hospital modify id int not null;
create unique index hospital_id_uindex on hospital (id);
alter table hospital add constraint hospital_pk primary key (id);
 ```

#### 결과
- 96180 rows retrieved starting from 1 in 463 ms (`execution: 9 ms`, fetching: 454 ms)