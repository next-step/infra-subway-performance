alter table hospital add constraint hospital_pk primary key (id);
create index covid_programmer_id_index on covid (programmer_id);
create index covid_hospital_id_index on covid (hospital_id);

select c.id, h.name, t.hobby, t.dev_type, t.years_coding
from (
select id, hobby, dev_type, years_coding
    from programmer
    where (hobby = 'yes' and student in ('Yes, part-time', 'Yes, full-time'))
    or years_coding = '0-2 years'
    order by id
) t
join covid c on t.id = c.programmer_id
join hospital h on c.hospital_id = h.id;
