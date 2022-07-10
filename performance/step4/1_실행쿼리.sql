select hobby.hobby, round(100 * hobby.count / hobby_all.count, 1)
from
(
    select hobby, count(hobby) count
    from programmer
    group by hobby
) hobby
    join
(
    select count(hobby) count
    from programmer
) hobby_all;
