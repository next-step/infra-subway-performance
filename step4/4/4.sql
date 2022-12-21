select c.stay, count(1)
from hospital h
         inner join covid c on c.hospital_id = h.id and h.name = '서울대병원'
         inner join programmer p on c.programmer_id = p.id and p.country = 'India'
         inner join member m on p.member_id = m.id and m.age between 20 and 29
group by c.stay;
