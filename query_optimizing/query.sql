select em.employee_id as '사원번호', em.last_name as '이름', em.annual_income as '연봉',
        (select position_name from position p where p.id = em.employee_id and p.end_date='9999-01-01') as '직급명',
        rc.time as '입출입시간', rc.region as '지역', rc.record_symbol as '입출입구분'
from  record rc, (
        select e.id as employee_id, d.id as department_id, e.last_name, sr.annual_income
        from employee e, department d, manager m, salary sr
        where 1=1
        and m.employee_id = e.id
        and m.department_id = d.id
        and m.end_date = '9999-01-01'
        and e.id = sr.id
        and sr.end_date = '9999-01-01'
        and 'ACTIVE' = upper(d.note)
        order by annual_income desc
        limit 5
) em
where 1 = 1
and em.employee_id = rc.employee_id
and rc.record_symbol = 'O'
;