select c.id, h.name, user.hobby, user.dev_type, user.years_coding
from hospital h
         join covid c
              on c.hospital_id = h.id
         join (
    select p.id, p.hobby, p.dev_type, p.years_coding
    from programmer p
    where (hobby = 'Yes'
        and student like 'Yes%')
       or years_coding = '0-2 years'
) user
on c.programmer_id = user.id;
