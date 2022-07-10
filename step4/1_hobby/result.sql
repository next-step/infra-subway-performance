create index programmer_hobby_index on programmer (hobby);

select hobby, concat(round(count(1) / (select count(1) from programmer) * 100, 1), '%') as ratio
from programmer
group by hobby
order by hobby desc;
