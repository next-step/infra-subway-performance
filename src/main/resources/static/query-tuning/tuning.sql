/**
  1. 활동중인 부서(Active)의 현재 관리자
     * 묵시적 형변환 방지를 위해 convert를 명시적으로 사용하였다.
     * department가 9건으로 manager 24건보다 작기 때문에 드라이빙 테이블로 선택되도록 하였.
     * manager가 드리븐 테이블이 되면서 활동 중인 부서만큼 `idx_manager_department_id` 인덱스를 반복해서 스캔하게 된다.
*/
select man.department_id, man.employee_id
from department dep
inner join manager man
on man.department_id = dep.id
where man.end_date = convert('9999-01-01', date)
and dep.note = 'ACTIVE';

/**
  2. 활동중인 부서(Active)의 현재 관리자의 이름 정보를 알기 위해 employee 테이블 조인
     * 1번에서 조회한 결과를 A라고 하면 A가 다시 드라이빙 테이블이 되어 employee와 조인되고, primary key로 unique access한다.
*/
select emp.id, emp.last_name
from department dep
inner join manager man
on man.department_id = dep.id
inner join employee emp
on emp.id = man.employee_id
where man.end_date = convert('9999-01-01', date)
and dep.note = 'ACTIVE';

/**
  3. 관리자의 직급명을 알기 위해 position 테이블 조인
     * position 테이블의 primary key(id, position_name, start_date) 인덱스를 사용하여 가장 최근 값을 가져온다.
       * position_name이 없기 때문에 약간의 비효율이 있지만 id 정보로 많지 않은 데이터가 있으므로 괜찮은 수준이라고 판단하였다.
       * 다만, [mysql 8.0 이하 버전에서는 desc 상에서 약간의 비효율이 추가적으로 있다고 한다.](https://tech.kakao.com/2018/06/19/mysql-ascending-index-vs-descending-index/)
*/
select
    emp.id,
    emp.last_name,
    (select position_name from position where id = emp.id order by start_date desc limit 1) as position_name
from department dep
inner join manager man
on man.department_id = dep.id
inner join employee emp
on emp.id = man.employee_id
where man.end_date = convert('9999-01-01', date)
and dep.note = 'ACTIVE';

/**
  4. 관리자의 급여 정보를 알기 위해 salary 테이블 조인
     * salary 테이블의 primary key(id, start_date) 인덱스를 사용하여 가장 최근 값을 가져온다.
     * 요구사항에 맞게 급여가 높은 순으로 5명만 범위를 제한한다.
*/
select
    emp.id,
    emp.last_name,
    (select annual_income from salary where id = emp.id order by start_date desc limit 1) as annual_income,
    (select position_name from position where id = emp.id order by start_date desc limit 1) as position_name
from department dep
inner join manager man
on man.department_id = dep.id
inner join employee emp
on emp.id = man.employee_id
where man.end_date = convert('9999-01-01', date)
  and dep.note = 'ACTIVE'
order by annual_income desc
limit 5;

/**
    5. 활동중인(Active) 부서의 현재 부서관리자(manager) 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실(O)했는지 조회
       * record 테이블이 660,000건으로 데이터가 많은 편이고 적합한 인덱스도 없어 쿼리 부하의 큰 부분을 차지하는 것으로 판단하였다.
         그래서 5개로 범위를 좁힌 결과로 가장 바깥쪽에서 조인하였다.
         * 실행계획에서 idx_record_record_symbol 인덱스를 사용한다고 되어있지만 조건인 record_symbol = 'O'인 건수는 360,000으로
           절반 이상이므로 성능 개선으로는 큰 의미는 없을 것으로 보인다.
*/
select
    result.id as 사원번호,
    result.last_name as 이름,
    result.annual_income as 연봉,
    result.position_name as 직급명,
    rec.time as 입출입시간,
    rec.region as 지역,
    rec.record_symbol as 입출입구분
from (select
          emp.id,
          emp.last_name,
          (select annual_income from salary where id = emp.id order by start_date desc limit 1) as annual_income,
          (select position_name from position where id = emp.id order by start_date desc limit 1) as position_name
      from department dep
      inner join manager man
      on man.department_id = dep.id
      inner join employee emp
      on emp.id = man.employee_id
      where man.end_date = convert('9999-01-01', date)
      and dep.note = 'ACTIVE'
      order by annual_income desc
      limit 5
) result
left join record rec
on rec.employee_id = result.id
where rec.record_symbol = 'O'
order by result.annual_income desc;
