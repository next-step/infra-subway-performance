##1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

- 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환

    1.  Coding as a Hobby 와 같은 결과를 반환하세요.
      
        - `programmer.id` pk 추가
        - `programmer.hobby` index 추가
      
        ```renderscript
        SELECT hobby, COUNT(hobby) / (SELECT COUNT(id) FROM subway.programmer) * 100 AS percent
        FROM subway.programmer
        GROUP BY hobby;
        ```

    2.  프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name).
       
        - `covid.id` pk 추가
        - `hospital.id` pk 추가
        
        ```redercript
        SELECT covid.id, hospital.name
        FROM subway.covid covid
        JOIN subway.hospital hospital
        ON covid.hospital_id = hospital.id;
        ```
    
    3.  프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요.
        - `covid.hospital_id`, `covid.programmer_id`  index 추가
    
        ```renderscript
        SELECT programmer.id, hospital.name
        FROM subway.covid covid
        JOIN (
          SELECT id
          FROM subway.programmer
          WHERE hobby = 'Yes' AND (student like 'Yes%' OR years_coding like '0-2%')
          ) programmer
        ON programmer.id = covid.programmer_id
        JOIN subway.hospital hospital
        ON covid.hospital_id = hospital.id
        ORDER BY programmer.id;
        ```

    4.  서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
        - `programmer.country`, `programmer.member_id` index 추가     

        ```renderscript
        SELECT stay, COUNT(P.member_id)
        FROM (
               SELECT id FROM subway.member WHERE age BETWEEN 20 AND 29) member
        JOIN (
               SELECT member_id FROM subway.programmer WHERE country = 'India') programmer
        ON member.id = programmer.member_id
        JOIN (
               SELECT covid.id, covid.member_id, hospital_id, stay FROM subway.covid
               JOIN (SELECT id FROM subway.hospital WHERE name = '서울대병원') hospital ON covid.hospital_id = hospital.id) covid
        ON member.id = C.member_id
        GROUP BY Stay
        ```
    5.  서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
        ```renderscript
        SELECT exercise, COUNT(P.id)
        FROM (SELECT id FROM subway.member WHERE age BETWEEN 30 AND 39) AS M
        INNER JOIN (SELECT member_id, hospital_id, programmer_id FROM subway.covid) AS C
        ON C.member_id = M.id
        INNER JOIN (SELECT id, exercise FROM subway.programmer) AS P
        ON C.programmer_id = P.id
        INNER JOIN (SELECT id FROM subway.hospital WHERE name = '서울대병원') as H
        ON C.hospital_id = H.id
        GROUP BY exercise
        ORDER BY null
        ```
