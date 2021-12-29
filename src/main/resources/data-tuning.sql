SELECT A.사원번호
	, (SELECT EMPLOYEE.이름 FROM tuning.사원 AS EMPLOYEE WHERE EMPLOYEE.사원번호 = A.사원번호) AS 이름
	, (SELECT POSITION.직급명 FROM tuning.직급 AS POSITION WHERE POSITION.사원번호 = A.사원번호 AND POSITION.종료일자 = A.종료일자) AS 직급명
	, MAX(B.입출입시간) AS 입출입시간
	, B.지역
	, B.입출입구분
FROM (
SELECT A.사원번호, A.연봉, A.종료일자, A.RNUM
	FROM (
		SELECT A.사원번호, A.연봉, A.종료일자,  @rownum := @rownum+1 AS RNUM
		FROM (
			SELECT A.사원번호, A.연봉, A.종료일자
			FROM tuning.급여 AS A,
				(
				SELECT C.사원번호, MAX(C.종료일자) AS 종료일자
				FROM tuning.부서관리자 AS A
				INNER JOIN tuning.급여 AS C
				ON A.사원번호 = C.사원번호
				WHERE A.종료일자 > sysdate()
				GROUP BY C.사원번호
				) AS B
			, (SELECT @rownum :=0) AS R
			WHERE A.사원번호 = B.사원번호
			  AND A.종료일자 = B.종료일자
			ORDER BY A.연봉 DESC
		) AS A
	) AS A
WHERE RNUM < 6
) AS A
, tuning.사원출입기록 AS B
WHERE A.사원번호 = B.사원번호
  AND B.입출입구분 = 'O'
GROUP BY A.사원번호, B.지역, B.입출입구분

