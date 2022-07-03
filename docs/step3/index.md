## 1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.
- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)


```sql
select top5_currently_manager.id as '사원번호',
       top5_currently_manager.last_name as '이름',
       top5_currently_manager.annual_income as '연봉',
       top5_currently_manager.position_name as '직급명',
       r.region as '지역',
       r.record_symbol as '입출입구분',
       r.time as '입출입시간'
from (select currently_manager.id,
             currently_manager.last_name,
             s.annual_income,
             currently_manager.position_name
      from (select e.id, e.last_name, p.position_name
            from (select id from department where note = 'active') d
                  join manager m on d.id = m.department_id
                  join employee e on e.id = m.employee_id
                  join position p on p.id = m.employee_id
            where (m.start_date <= now() and m.end_date > now())
              and (p.start_date <= now() and p.end_date > now())) currently_manager
             join salary s on currently_manager.id = s.id
      where (s.start_date <= now() and s.end_date > now())
      order by s.annual_income desc
      limit 5) top5_currently_manager
join record r on top5_currently_manager.id = r.employee_id
where r.record_symbol = 'O';
```
