## 1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```sql
select r.employee_id as "사원번호",
       filter.name as "이름",
       filter.income as "연봉",
       filter.position as "직급명",
       r.region as "지역",
       r.record_symbol as "입출입구분",
       r.time as "입출입시간"
       from record r
inner join (
    select m.employee_id as id,
           e.last_name as name,
           s.annual_income as income,
           p.position_name as position from manager m
    inner join employee e on m.employee_id = e.id and m.end_date > sysdate()
    inner join department d on m.department_id = d.id and d.note = 'active'
    inner join position p on m.employee_id = p.id and position_name = 'Manager'
    inner join salary s on m.employee_id = s.id and s.end_date > sysdate()
    order by s.annual_income desc
    limit 5
    ) filter
on filter.id = r.employee_id
where r.record_symbol = 'O';

```
- 결과
```sh
+--------+----------+--------+---------+------+---+---------------------+
|        |          |        |         |      |   |                     |
+--------+----------+--------+---------+------+---+---------------------+
| 110039 | Vishwani | 106491 | Manager | a    | O | 2020-09-05 20:30:07 |
| 110039 | Vishwani | 106491 | Manager | b    | O | 2020-08-05 21:01:50 |
| 110039 | Vishwani | 106491 | Manager | d    | O | 2020-07-06 11:00:25 |
| 111133 | Hauke    | 101987 | Manager | a    | O | 2020-01-24 02:59:37 |
| 111133 | Hauke    | 101987 | Manager | b    | O | 2020-05-07 16:30:37 |
| 110114 | Isamu    |  83457 | Manager | a    | O | 2020-05-29 19:38:12 |
| 110114 | Isamu    |  83457 | Manager | b    | O | 2020-09-03 01:33:01 |
| 110114 | Isamu    |  83457 | Manager | c    | O | 2020-11-12 02:29:00 |
| 110114 | Isamu    |  83457 | Manager | d    | O | 2020-04-25 08:28:54 |
| 110567 | Leon     |  74510 | Manager | a    | O | 2020-10-17 19:13:31 |
| 110567 | Leon     |  74510 | Manager | b    | O | 2020-02-03 10:51:15 |
| 110228 | Karsten  |  65400 | Manager | a    | O | 2020-07-13 11:42:49 |
| 110228 | Karsten  |  65400 | Manager | b    | O | 2020-09-23 06:07:01 |
| 110228 | Karsten  |  65400 | Manager | d    | O | 2020-01-11 22:29:04 |
+--------+----------+--------+---------+------+---+---------------------+
```