-- Q : Coding as a Hobby 와 같은 결과를 반환하세요.

create index PROGRAMMER_IDX_1 on programmer(hobby)
-- group by의 대상이 되는 hobby 컬럼에 인덱스 생성
-- 기존 full table scan -> full index scan 으로 변경
;
select hobby, (count(*) / (select count(*) from programmer)) * 100
from programmer
group by hobby
-- 기존 : 2s 정도의 응답속도
-- 변경 : 100ms 정도의 응답속도
;
