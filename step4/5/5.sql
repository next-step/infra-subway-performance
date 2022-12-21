select p.exercise, count(1)
from hospital h
         inner join covid c on c.hospital_id = h.id
         inner join programmer p on c.programmer_id = p.id
         inner join member m on m.id = c.member_id and m.age between 30 and 39
where h.name = '서울대병원'
group by p.exercise;
