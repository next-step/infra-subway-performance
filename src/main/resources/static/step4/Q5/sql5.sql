-- Q5
-- 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
select P.exercise 
from (select id from member where age between 30 and 39) as M
inner join programmer as P on M.id = P.member_id
inner join covid as C on C.member_id = M.id
inner join (select id from hospital where name = '서울대병원') as H on C.hospital_id = H.id
group by P.exercise