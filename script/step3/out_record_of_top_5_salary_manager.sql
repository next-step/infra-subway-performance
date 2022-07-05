SELECT 
    manager_salary_top5.사원번호,
    manager_salary_top5.이름,
    manager_salary_top5.연봉,
    manager_salary_top5.직급명,
    r.time AS 입출입시간,
    r.region AS 지역,
    r.record_symbol AS 입출입구분
FROM
    (SELECT 
        m.employee_id AS 사원번호,
            e.last_name AS 이름,
            s.annual_income AS 연봉,
            p.position_name AS 직급명
    FROM
        manager AS m
    JOIN department AS d ON d.id = m.department_id
    JOIN position AS p ON p.id = m.employee_id
    JOIN employee AS e ON e.id = m.employee_id
    JOIN salary AS s ON s.id = e.id
    WHERE
        d.note = 'active'
            AND p.position_name = 'Manager'
            AND NOW() BETWEEN m.start_date AND m.end_date
            AND NOW() BETWEEN s.start_date AND s.end_date
    ORDER BY s.annual_income DESC
    LIMIT 5) AS manager_salary_top5
JOIN record AS r ON r.employee_id = manager_salary_top5.사원번호
WHERE r.record_symbol = 'O'
ORDER BY manager_salary_top5.연봉 DESC;