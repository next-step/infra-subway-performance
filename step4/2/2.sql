select c.id, h.name
from hospital h
         inner join covid c on c.hospital_id = h.id
         inner join programmer p on c.programmer_id = p.id;
