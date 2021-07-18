-- Q : 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 programmer.id 기준으로 정렬하세요.

select p.id, h.name
from programmer p left outer join covid c on p.id = c.programmer_id inner join hospital h on c.hospital_id = h.id
where 1=1
and hobby = 'Yes' and (student like 'Yes%' or years_coding = '0-2 years')
-- 2번 문제에서와 같은 이유로 programmer와 covid 는 programmer 기준으로 left outer join 및 covid 와 hospital 은 inner join
-- 특별히 튜닝이 없더라도 programmer에서 unique key lookup 실행되며 좋은 성능을 보여줌
-- 인덱스는 이미 정렬된 값으로 노출되므로 따로 order by 없이 수행
-- 50ms 응답 속도
;
