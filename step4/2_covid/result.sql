create index covid_programmer_id_index on covid (programmer_id);
create index covid_hospital_id_index on covid (hospital_id);

select c.id, h.name
from programmer p
join covid c on p.id = c.programmer_id
join hospital h on c.hospital_id = h.id;
