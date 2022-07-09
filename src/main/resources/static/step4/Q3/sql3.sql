-- Q3
-- 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
select C.id, H.name, P.hobby, P.dev_type, P.years_coding
from covid as C
inner join hospital as H on H.id = C.hospital_id
inner join (select id, hobby, dev_type, years_coding 
			from programmer
			 where student = 'Yes, full-time'
				or years_coding = '0-2 years') as P  on P.id = C.programmer_id
order by C.id