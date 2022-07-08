select c.id, h.name
from hospital h
         join covid c
              on c.hospital_id = h.id
         join programmer p
              on c.programmer_id = p.id;
