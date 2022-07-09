select
    A.employee_id as '사원번호'
    , A.employee_name as '이름'
    , A.annual_income as '연봉'
    , A.position_name as '직급명'
    , R.time as '입출입시간'
    , R.region as '지역'
    , R.record_symbol as '입출입구분'
from
    (select M.employee_id,
            concat(E.first_name, ' ', E.last_name) as employee_name,
            S.annual_income, P.position_name
        from manager as M
        inner join department as D on (M.department_id = D.id)
        inner join salary as S on (S.id = M.employee_id)
        inner join position as P on (P.id = M.employee_id)
        inner join employee as E on (E.id = M.employee_id)
        where D.note = 'active'
            and Date(now()) between S.start_date and S.end_date
            and P.position_name = 'Manager'
            and Date(now()) between P.start_date and P.end_date
        order by S.annual_income desc
        limit 5) A
inner join record R on R.employee_id = A.employee_id
where R.record_symbol = 'O'
