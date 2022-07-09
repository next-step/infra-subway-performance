-- Q4
-- 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
select C.stay 
from (select id from member where age between 20 and 29) as M
inner join (select member_id from programmer where country = 'India') as P on M.id = P.member_id
inner join covid as C on C.member_id = M.id
inner join (select id from hospital where name = '서울대병원') as H on C.hospital_id = H.id
group by C.stay