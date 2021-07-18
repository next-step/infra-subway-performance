-- Q : 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요.

select count(distinct member_id)
from programmer -- 96206
union all
select count(distinct id)
from member -- 96206
;
select count(distinct member_id)
from programmer p inner join member m on p.member_id = m.id
-- 96206
-- 위의 데이터를 봤을 때 programmer 와 member 는 inner join 가능
;
create index COVID_IDX_1 on covid(programmer_id)
-- programmer 테이블과 join을 위함
;
create index MEMBER_IDX_1 on member(age)
-- member 테이블에서 where 조건절의 age between 20 and 29 부분의 성능 향상을 위함
;
create index PROGRAMMER_IDX_2 on programmer(country)
-- programmer 테이블에서 where 조건절의 country 부분의 성능 향상을 위함
;
select stay, count(p.id)
from programmer p
	inner join member m on p.id = m.id
	left outer join covid c on p.id = c.programmer_id
    inner join hospital h on c.hospital_id = h.id
where 1=1
and h.name = '서울대병원'
and m.age between 20 and 29
and p.country = 'India'
group by stay
-- 기존 : 500ms 응답 속도
-- 변경 : 80ms 응답 속도
;
