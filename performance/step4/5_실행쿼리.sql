select p.exercise, count(p.exercise)
from hospital h
         join covid c
              on c.hospital_id = h.id
         join programmer p
              on c.programmer_id = p.id
         join member m
              on p.member_id = m.id
where h.id = 9
  and m.age > 29
  and m.age < 40
group by p.exercise;
