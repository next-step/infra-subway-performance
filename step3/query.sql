select limit_employee.id            as 사원번호,
       limit_employee.last_name     as 이름,
       limit_employee.annual_income as 연봉,
       limit_employee.position_name as 직급명,
       record.time                  as 입출입시간,
       record.region                as 지역,
       record.record_symbol         as 입출입구분
from (
         select employee.id, employee.last_name, salary.annual_income, position_name
         from manager
                  inner join salary
                             on salary.id = manager.employee_id
                                 and salary.end_date > now()
                  inner join department
                             on manager.department_id = department.id
                                 and department.note = 'Active'
                                 and manager.end_date > now()
                  inner join employee
                             on employee.id = manager.employee_id
                  inner join position
                             on position.id = employee.id
         order by salary.annual_income desc limit 5
     ) as limit_employee
         Inner join record
                    on record.employee_id = limit_employee.id
where record.record_symbol = 'O';
