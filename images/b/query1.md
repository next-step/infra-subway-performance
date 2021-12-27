##### Q1. Coding as a Hobby 와 같은 결과를 반환하세요.
```sql
SELECT hobby,
       CONCAT(
               ROUND(COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100, 1)
           , '%') AS response
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;

SELECT hobby,
       CONCAT(
               FORMAT(
                       ROUND(COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100)
                   , 1)
           , '%') AS response
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC
```

##### BEFORE (약 840ms)
```text
+--+-----------+----------+----------+-----+-------------+--------------------+-------+----+-----+--------+-------------------------------+
|id|select_type|table     |partitions|type |possible_keys|key                 |key_len|ref |rows |filtered|Extra                          |
+--+-----------+----------+----------+-----+-------------+--------------------+-------+----+-----+--------+-------------------------------+
|1 |PRIMARY    |programmer|NULL      |ALL  |NULL         |NULL                |NULL   |NULL|81739|100     |Using temporary; Using filesort|
|2 |SUBQUERY   |programmer|NULL      |index|NULL         |programmer_id_uindex|9      |NULL|81739|100     |Using index                    |
+--+-----------+----------+----------+-----+-------------+--------------------+-------+----+-----+--------+-------------------------------+
```


##### AFTER
```text
+--+-----------+----------+----------+-----+--------------------+--------------------+-------+----+-----+--------+-----------+
|id|select_type|table     |partitions|type |possible_keys       |key                 |key_len|ref |rows |filtered|Extra      |
+--+-----------+----------+----------+-----+--------------------+--------------------+-------+----+-----+--------+-----------+
|1 |PRIMARY    |programmer|NULL      |index|programmer__index001|programmer__index001|258    |NULL|81739|100     |Using index|
|2 |SUBQUERY   |programmer|NULL      |index|NULL                |programmer_id_uindex|9      |NULL|81739|100     |Using index|
+--+-----------+----------+----------+-----+--------------------+--------------------+-------+----+-----+--------+-----------+
```



#### 변경 내용
 ```sql
alter table programmer modify hobby varchar(64) not null;
create index programmer__index001 on programmer (hobby);
 ```

#### 결과
- 2 rows retrieved starting from 1 in 83 ms (`execution: 69 ms`, fetching: 14 ms)