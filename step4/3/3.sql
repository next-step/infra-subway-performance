select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from hospital h
         inner join covid c on c.hospital_id = h.id
         inner join programmer p on c.programmer_id = p.id and ((p.years_coding = '0-2 years') or (p.hobby = 'Yes' and p.student != 'no'))
order by p.id;
