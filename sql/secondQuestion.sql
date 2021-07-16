-- Q : 프로그래머별로 해당하는 병원 이름을 반환하세요.

select count(*)
from covid
-- 318325
;
select count(distinct programmer_id), count(*)
from covid -- 96180, 318325
union all
select count(distinct id), count(*)
from programmer -- 98855, 98855
;
select count(*)
from covid inner join programmer on covid.programmer_id = programmer.id
-- 96180
-- 위의 세 수치의 값을 봤을 때 programmer에는 존재하지만, covid의 programmer_id에 존재하지 않는 값이 존재
-- covid와 hospital만 inner join 할 경우 programmer의 특정 값들은 노출 불가
-- 따라서 programmer 테이블 기준으로 left outer join 필요
;
select count(distinct hospital_id)
from covid -- 32
union all
select count(distinct id)
from hospital -- 32
;
select count(distinct h.id)
from covid c join hospital h on c.hospital_id = h.id
-- 32
-- 위의 수치를 봤을 때 covid 의 hospital_id 와 hospital의 id는 정확히 같은 값을 양쪽 테이블에서 가지고 있음
-- 따라서 inner join 가능
;
alter table programmer modify id bigint primary key
-- 기존 full table scan 에서 programmer pk 기준 unique key lookup 실행
;
select p.id, h.name
from programmer p left outer join covid c on p.id = c.programmer_id inner join hospital h on c.hospital_id = h.id
where 1=1
limit 1000
-- 기존 : 2s 정도의 응답속도
-- 변경 : 15ms 정도의 응답속도
;
