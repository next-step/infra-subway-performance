select managers.id 사원번호,
       managers.last_name 이름,
       managers.annual_income 연봉,
       managers.position_name 직급명,
       r.region 지역,
       r.record_symbol 입출입구분
from
    (
        select e.id,
               e.last_name,
               s.annual_income,
               p.position_name
        from department d
                 join manager m
                      on d.id= m.department_id
                 join employee e
                      on m.employee_id = e.id
                 join position p
                      on e.id = p.id
                 join salary s
                      on e.id = s.id
        where d.note = 'active'
          and m.end_date > now()
          and p.end_date > now()
          and s.end_date > now()
        order by s.annual_income desc
            limit 5
    ) managers
        join record r
             on managers.id = r.employee_id
where r.record_symbol = 'O'
group by r.region, managers.id, managers.last_name, managers.annual_income, managers.position_name, r.time, r.record_symbol
order by managers.annual_income desc, r.region;
