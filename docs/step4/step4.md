## 요구사항
- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환

## 1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요
- Coding as a Hobby 와 같은 결과를 반환하세요.
```sql
alter table programmer
    add constraint programmer_pk
        primary key (id);

create index programmer_hobby_index
on programmer (hobby);

select hobby, round(count(*) * 100.0 / (select count(*) from programmer), 1)
from programmer
group by hobby
order by hobby desc;
```

```shell
+-------+----------------------------------------------------------------+
| hobby | round(count(*) * 100.0 / (select count(*) from programmer), 1) |
+-------+----------------------------------------------------------------+
| Yes   |                                                           80.8 |
| No    |                                                           19.2 |
+-------+----------------------------------------------------------------+

+----+-------------+------------+------------+-------+------------------------+------------------------+---------+------+-------+----------+-------------+
| id | select_type | table      | partitions | type  | possible_keys          | key                    | key_len | ref  | rows  | filtered | Extra       |
+----+-------------+------------+------------+-------+------------------------+------------------------+---------+------+-------+----------+-------------+
|  1 | PRIMARY     | programmer | NULL       | index | programmer_hobby_index | programmer_hobby_index | 259     | NULL | 71210 |   100.00 | Using index |
|  2 | SUBQUERY    | programmer | NULL       | index | NULL                   | programmer_hobby_index | 259     | NULL | 71210 |   100.00 | Using index |
+----+-------------+------------+------------+-------+------------------------+------------------------+---------+------+-------+----------+-------------+
```

- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
```sql
alter table covid
	add constraint covid_pk
		primary key (id);

alter table hospital
    add constraint hospital_pk
        primary key (id);

create index covid_programmer_id_index
    on covid (programmer_id);

create index covid_hospital_id_index
    on covid (hospital_id);


select c.id, h.name
from covid c
         inner join programmer p on c.programmer_id = p.id
         inner join hospital h on c.hospital_id = h.id;
```

```shell
+----+-------------+-------+------------+--------+---------------------------------------------------+---------------------------+---------+----------------------+-------+----------+-------------+
| id | select_type | table | partitions | type   | possible_keys                                     | key                       | key_len | ref                  | rows  | filtered | Extra       |
+----+-------------+-------+------------+--------+---------------------------------------------------+---------------------------+---------+----------------------+-------+----------+-------------+
|  1 | SIMPLE      | p     | NULL       | index  | PRIMARY                                           | programmer_hobby_index    | 259     | NULL                 | 74465 |   100.00 | Using index |
|  1 | SIMPLE      | c     | NULL       | ref    | covid_hospital_id_index,covid_programmer_id_index | covid_programmer_id_index | 9       | subway.p.id          |     3 |   100.00 | Using where |
|  1 | SIMPLE      | h     | NULL       | eq_ref | PRIMARY                                           | PRIMARY                   | 4       | subway.c.hospital_id |     1 |   100.00 | Using where |
+----+-------------+-------+------------+--------+---------------------------------------------------+---------------------------+---------+----------------------+-------+----------+-------------+
```

- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
```sql
select c.id, h.name, j.hobby, j.dev_type, j.years_coding
from (select id, years_coding, hobby, dev_type
      from programmer
      where (years_coding = '0-2 years' or student in ('Yes, full-time', 'Yes, part-time'))
        and hobby = 'Yes') j
         join (select id, hospital_id, programmer_id from covid) c
              on j.id = c.programmer_id
         join (select id, name from hospital) h
              on h.id = c.hospital_id
order by j.id;
```
```shell
+----+-------------+------------+------------+--------+---------------------------------------------------+---------------------------+---------+----------------------+-------+----------+------------------------------------+
| id | select_type | table      | partitions | type   | possible_keys                                     | key                       | key_len | ref                  | rows  | filtered | Extra                              |
+----+-------------+------------+------------+--------+---------------------------------------------------+---------------------------+---------+----------------------+-------+----------+------------------------------------+
|  1 | SIMPLE      | programmer | NULL       | ref    | PRIMARY,programmer_hobby_index                    | programmer_hobby_index    | 259     | const                | 37232 |    28.00 | Using index condition; Using where |
|  1 | SIMPLE      | covid      | NULL       | ref    | covid_hospital_id_index,covid_programmer_id_index | covid_programmer_id_index | 9       | subway.programmer.id |     3 |   100.00 | Using where                        |
|  1 | SIMPLE      | hospital   | NULL       | eq_ref | PRIMARY                                           | PRIMARY                   | 4       | func                 |     1 |   100.00 | Using where                        |
+----+-------------+------------+------------+--------+---------------------------------------------------+---------------------------+---------+----------------------+-------+----------+------------------------------------+
```

- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
```sql
alter table member
	add constraint member_pk
		primary key (id);

create index hospital_name_index
    on hospital (name);

create index covid_hospital_id_member_id_index
    on covid (hospital_id, member_id);

create index member_age_index
    on member (age);

select c.stay, count(*)
from programmer p
         inner join covid c on c.programmer_id = p.id
         inner join member m on c.member_id = m.id
         inner join hospital h on c.hospital_id = h.id
where h.name = '서울대병원'
  and p.country = 'India'
  and m.age BETWEEN 20 and 29
group by c.stay;
```

- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
```sql
create index covid_member_id_index
    on covid (member_id);

select p.exercise, count(p.exercise)
from programmer p
         inner join covid c on p.id = c.programmer_id
         inner join hospital h on c.hospital_id = h.id
         inner join member m on c.member_id = m.id
where m.age between 30 and 39
  and h.name = '서울대병원'
group by p.exercise
```