-- Q1
-- Coding as a Hobby 와 같은 결과를 반환하세요.
select hobby, count(hobby) / (select count(1) from programmer) * 100 as 'Coding as a Hobby'
from programmer
group by hobby
order by hobby desc