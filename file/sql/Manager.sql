/*
활동중인(Active) 부서의 현재 부서관리자(manager) 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실(O)했는지 조회해보세요.
(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
인덱스 설정을 추가하지 않고 1s 이하로 반환합니다.
M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 2배를 기준으로 해보시고 어렵다면, 일단 리뷰요청 부탁드려요
급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.
*/
SELECT manager.employee_id
	,  employee.last_name
	,  salary.annual_income
	,  `position`.position_name
	,  record.`time`
	,  record.region
	,  record.record_symbol
  FROM tuning.department
 INNER JOIN tuning.manager
 	ON department.id = manager.department_id
 INNER JOIN tuning.employee
 	ON manager.employee_id = employee.id
 INNER JOIN tuning.`position`
 	ON manager.employee_id = `position`.id
 INNER JOIN tuning.record
 	ON manager.employee_id = record.employee_id
 INNER JOIN tuning.salary
 	ON manager.employee_id = salary.id
   AND salary.end_date LIKE '9999%'
 WHERE (department.note LIKE 'a%'
    OR department.note LIKE 'A%')
   AND manager.end_date LIKE '9999%'
   AND `position`.end_date LIKE '9999%'
   AND record.record_symbol = 'O'
 ORDER BY salary.annual_income DESC
 		, record.`time` DESC

/*
employee_id|last_name|annual_income|position_name|time               |region|record_symbol|
-----------|---------|-------------|-------------|-------------------|------|-------------|
     110039|Vishwani |       106491|Manager      |2020-09-05 20:30:07|a     |O            |
     110039|Vishwani |       106491|Manager      |2020-08-05 21:01:50|b     |O            |
     110039|Vishwani |       106491|Manager      |2020-07-06 11:00:25|d     |O            |
     111133|Hauke    |       101987|Manager      |2020-05-07 16:30:37|b     |O            |
     111133|Hauke    |       101987|Manager      |2020-01-24 02:59:37|a     |O            |
     110114|Isamu    |        83457|Manager      |2020-11-12 02:29:00|c     |O            |
     110114|Isamu    |        83457|Manager      |2020-09-03 01:33:01|b     |O            |
     110114|Isamu    |        83457|Manager      |2020-05-29 19:38:12|a     |O            |
     110114|Isamu    |        83457|Manager      |2020-04-25 08:28:54|d     |O            |
     110567|Leon     |        74510|Manager      |2020-10-17 19:13:31|a     |O            |
     110567|Leon     |        74510|Manager      |2020-02-03 10:51:15|b     |O            |
     110228|Karsten  |        65400|Manager      |2020-09-23 06:07:01|b     |O            |
     110228|Karsten  |        65400|Manager      |2020-07-13 11:42:49|a     |O            |
     110228|Karsten  |        65400|Manager      |2020-01-11 22:29:04|d     |O            |
*/
