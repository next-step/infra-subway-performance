select c.stay, count(stay)
from hospital h
         join covid c
              on c.hospital_id = h.id
         join programmer p
              on c.programmer_id = p.id
         join member m
              on p.member_id = m.id
where h.id = 9
  and m.age > 19
  and m.age < 30
  and p.country = 'India'
group by c.stay;
