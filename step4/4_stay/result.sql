create index member_id_index on member (id);
create index programmer_member_id_country_index on programmer (country, member_id);
create index covid_programmer_id_hospital_id_index on covid (programmer_id, hospital_id);
create index hospital_name_index on hospital (name);

select c.stay, count(1)
from programmer p
inner join member m on p.member_id = m.id
inner join covid c on p.id = c.programmer_id
inner join hospital h on c.hospital_id = h.id
where h.name = '서울대병원'
and p.country = 'india'
and m.age between 20 and 29
group by c.stay;