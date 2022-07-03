select
    top5.id as '사원번호',
        top5.last_name as '이름',
        top5.annual_income as '연봉',
        p.position_name as '직급명',
        r.region as '지역',
        r.record_symbol as '입출입구분',
        r.time as '입출입시간'
from (
         select e.id, e.last_name, s.annual_income
         from department as d
                  inner join manager as m
                             on m.department_id = d.id and (now() between m.start_date and m.end_date)
                  inner join employee as e
                             on e.id = m.employee_id
                  inner join salary as s
                             on s.id = e.id and (now() between s.start_date and s.end_date)
         where d.note = 'active'
         order by s.annual_income desc
             limit 5
     ) as top5
         inner join position as p
                    on p.id = top5.id and (now() between p.start_date and p.end_date)
         inner join record as r
                    on r.employee_id = top5.id
where r.record_symbol = 'O';
