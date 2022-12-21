select
    hobby as 'hobby', ROUND((count(hobby) * 100) / (SELECT count(hobby) from programmer), 1) as 'percent'
from programmer
group by hobby
order by hobby desc;
