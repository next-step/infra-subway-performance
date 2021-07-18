-- Q : 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요.
create index COVID_IDX_2 on covid(hospital_id)
-- hospital 과 join 시 성능향상을 위함
-- 기존 full table scan에서 -> non-unique key loop으로 변경
;
select exercise, count(p.id)
from programmer p
    inner join member m on p.id = m.id
    left outer join covid c on p.id = c.programmer_id join hospital h on c.hospital_id = h.id
where 1=1
and m.age between 30 and 39
and h.name = '서울대병원'
group by exercise
order by 2
-- 기존 : 응답 속도 350ms
-- 변경 : 응답 속도 80ms
;
