SELECT 
v.id '사원번호'
, emp.last_name '이름'
, v.annual_income '연봉'
, pos.position_name '직급명'
, rec.time '입출입시간'
, rec.region '지역'
, rec.record_symbol '입출입구분'
FROM 
		(  
        SELECT sal.id, sal.annual_income
		FROM department dept
			INNER JOIN manager mng ON dept.id = mng.department_id AND dept.note = 'Active'AND end_date > NOW()
			INNER JOIN salary sal ON mng.employee_id = sal.id AND sal.end_date > NOW()
		ORDER BY sal.annual_income desc LIMIT 5
        ) v
INNER JOIN employee emp ON emp.id = v.id
INNER JOIN position pos ON pos.id = v.id AND pos.end_date > NOW()
INNER JOIN record rec ON rec.record_symbol = 'O' AND rec.employee_id = v.id
;
  

  

		


