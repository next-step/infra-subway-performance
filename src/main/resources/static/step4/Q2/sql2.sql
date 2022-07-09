-- Q2
-- 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
select C.id, H.name 
from programmer as P 
inner join covid as C on P.id = C.programmer_id
inner join hospital as H on H.id = C.hospital_id 