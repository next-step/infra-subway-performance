##### Q4. 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.
```sql
SELECT covid.stay, count(*)
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

##### BEFORE (약 482ms)
```text
+--+-----------+----------+----------+------+--------------------------------------------------------+---------------------------+-------+---------------------------+-----+--------+--------------------------------------------------+
|id|select_type|table     |partitions|type  |possible_keys                                           |key                        |key_len|ref                        |rows |filtered|Extra                                             |
+--+-----------+----------+----------+------+--------------------------------------------------------+---------------------------+-------+---------------------------+-----+--------+--------------------------------------------------+
|1 |SIMPLE     |programmer|NULL      |ref   |PRIMARY,programmer_id_uindex,idx_user_Country_OpenSource|idx_user_Country_OpenSource|1023   |const                      |25612|100     |Using where                                       |
|1 |SIMPLE     |member    |NULL      |eq_ref|PRIMARY                                                 |PRIMARY                    |8      |subway.programmer.member_id|1    |11.11   |Using where                                       |
|1 |SIMPLE     |covid     |NULL      |ref   |covid__index001                                         |covid__index001            |9      |subway.programmer.id       |3    |100     |NULL                                              |
|1 |SIMPLE     |hospital  |NULL      |ALL   |PRIMARY,hospital_id_uindex                              |NULL                       |NULL   |NULL                       |32   |3.12    |Using where; Using join buffer (Block Nested Loop)|
+--+-----------+----------+----------+------+--------------------------------------------------------+---------------------------+-------+---------------------------+-----+--------+--------------------------------------------------+
```

##### AFTER
```text
+--+-----------+----------+----------+------+-------------------------------------------------+--------------------+-------+---------------------------+-----+--------+----------------------------+
|id|select_type|table     |partitions|type  |possible_keys                                    |key                 |key_len|ref                        |rows |filtered|Extra                       |
+--+-----------+----------+----------+------+-------------------------------------------------+--------------------+-------+---------------------------+-----+--------+----------------------------+
|1 |SIMPLE     |hospital  |NULL      |const |PRIMARY,hospital_id_uindex,hospital_name_uindex  |hospital_name_uindex|1022   |const                      |1    |100     |Using index; Using temporary|
|1 |SIMPLE     |programmer|NULL      |ref   |PRIMARY,programmer_id_uindex,programmer__index003|programmer__index003|1023   |const                      |26162|100     |Using where                 |
|1 |SIMPLE     |member    |NULL      |eq_ref|PRIMARY,member_index001                          |PRIMARY             |8      |subway.programmer.member_id|1    |44.16   |Using where                 |
|1 |SIMPLE     |covid     |NULL      |ref   |covid__index001,covid__index002                  |covid__index002     |9      |subway.programmer.id       |3    |10      |Using where                 |
+--+-----------+----------+----------+------+-------------------------------------------------+--------------------+-------+---------------------------+-----+--------+----------------------------+

```

#### 변경 내용
 ```sql
alter table hospital modify name varchar(255) not null;
create unique index hospital_name_uindex on hospital (name);
alter table member modify age int not null;
create index member_id_index001 on member (age);
create index programmer__index003 on programmer (country);
create index covid__index002 on covid (stay);
 ```

#### 결과
- 10 rows retrieved starting from 1 in 317 ms (`execution: 305 ms`, fetching: 12 ms)

## 쿼리 튜닝 생각해보기