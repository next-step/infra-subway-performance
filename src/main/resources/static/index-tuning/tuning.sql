/**
  생성된 인덱스 목록
*/
alter table programmer add primary key (id);
alter table hospital add primary key (id);
alter table covid add foreign key (programmer_id) references programmer (id);
/** covid hospital_id와 hospital의 id 컬럼의 타입이 일치하지 않아서 일치하도록 수정하였다. */
alter table `subway`.`hospital` change column `id` `id` BIGINT(20) NOT NULL ;
alter table covid add foreign key (hospital_id) references hospital (id);
create index idx_programmer_01 on programmer (hobby);

alter table member add primary key (id);
alter table programmer add foreign key (member_id) references member (id);
create index idx_member_01 on member (age);

/**
  1.Coding as a Hobby와 같은 결과 반환한다.
    * Rows: 2 / Duration Time: 46ms
*/
select hobby, round((count(1) / (select count(1) from programmer) * 100), 2) as is_coding
from programmer
group by hobby;

/**
  2.프로그래머별로 해당하는 병원 이름을 반환한다.
    * Rows: 96180 / Duration Time: 26ms
*/
select c.id, h.name
from hospital h
inner join covid c on h.id = c.hospital_id
inner join programmer p on p.id = c.programmer_id;

/**
  3.프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 programmer.id 기준으로 정렬한다.
    * Rows: 25374 / Duration Time: 21ms
*/
select c.id, h.name, p.hobby, p.dev_type, p.years_coding
from programmer p
inner join covid c on c.programmer_id = p.id
inner join hospital h on h.id = c.hospital_id
where (p.hobby = 'Yes' and p.student like 'Yes%')
   or p.years_coding = '0-2 years'
order by p.id;

/**
  4.서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계한다.
    * Rows: 10 / Duration Time: 84ms
*/
select c.stay, count(1)
from hospital h
inner join covid c on c.hospital_id = h.id
inner join programmer p on p.id = c.programmer_id
inner join member m on m.id = p.member_id
where h.name = '서울대병원'
  and p.country = 'India'
  and m.age between 20 and 29
group by c.stay;

/**
  5.서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계한다.
    * Rows: 5 / Duration Time: 58ms
*/
select p.exercise, count(1)
from hospital h
inner join covid c on c.hospital_id = h.id
inner join programmer p on p.id = c.programmer_id
inner join member m on m.id = p.member_id
where h.name = '서울대병원'
  and m.age between 30 and 39
group by p.exercise;
