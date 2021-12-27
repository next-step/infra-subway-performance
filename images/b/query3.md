##### Q3. 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.(covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql
SELECT c.id, h.name, p.hobby, p.dev_type as devType, p.years_coding as YearsCoding
FROM programmer p
         INNER JOIN covid c ON p.id = c.programmer_id
         INNER JOIN hospital h ON c.hospital_id = h.id
WHERE p.hobby = 'Yes'
  AND (p.student LIKE 'Yes%' OR years_coding = '0-2 years')
```

##### BEFORE (약 1.700ms)
```text
+--+-----------+-----+----------+------+-------------------------------------------------+--------------------+-------+--------------------+-----+--------+-----------+
|id|select_type|table|partitions|type  |possible_keys                                    |key                 |key_len|ref                 |rows |filtered|Extra      |
+--+-----------+-----+----------+------+-------------------------------------------------+--------------------+-------+--------------------+-----+--------+-----------+
|1 |SIMPLE     |p    |NULL      |ref   |PRIMARY,programmer_id_uindex,programmer__index001|programmer__index001|258    |const               |37232|20      |Using where|
|1 |SIMPLE     |c    |NULL      |ref   |covid__index001                                  |covid__index001     |9      |subway.p.id         |3    |100     |Using index|
|1 |SIMPLE     |h    |NULL      |eq_ref|PRIMARY,hospital_id_uindex                       |PRIMARY             |4      |subway.c.hospital_id|1    |100     |Using where|
+--+-----------+-----+----------+------+-------------------------------------------------+--------------------+-------+--------------------+-----+--------+-----------+
```

##### AFTER
```text
+--+-----------+-----+----------+------+-------------------------------------------------+--------------------+-------+--------------------+-----+--------+---------------------+
|id|select_type|table|partitions|type  |possible_keys                                    |key                 |key_len|ref                 |rows |filtered|Extra                |
+--+-----------+-----+----------+------+-------------------------------------------------+--------------------+-------+--------------------+-----+--------+---------------------+
|1 |SIMPLE     |p    |NULL      |ref   |PRIMARY,programmer_id_uindex,programmer__index002|programmer__index002|258    |const               |37232|20      |Using index condition|
|1 |SIMPLE     |c    |NULL      |ref   |covid__index001                                  |covid__index001     |9      |subway.p.id         |3    |100     |Using index          |
|1 |SIMPLE     |h    |NULL      |eq_ref|PRIMARY,hospital_id_uindex                       |PRIMARY             |4      |subway.c.hospital_id|1    |100     |Using where          |
+--+-----------+-----+----------+------+-------------------------------------------------+--------------------+-------+--------------------+-----+--------+---------------------+
```

#### 변경 내용
 ```sql
alter table programmer modify student varchar(64) not null;
alter table programmer modify years_coding varchar(255) not null;
drop index programmer__index001 on programmer;
create index programmer__index002 on programmer (hobby, student, years_coding);
 ```

#### 결과
- 24537 rows retrieved starting from 1 in 675 ms (`execution: 9 ms`, fetching: 666 ms)