# 1. 쿼리 작성만으로 1s 이하로 반환한다.
# 2. 급여인덱스 설정을 추가하여 50 ms 이하로 반환한다.

# 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
# (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
# 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.

SELECT 사원.사원번호, 사원.이름, 직급.직급명, 사원출입기록.입출입구분, 사원출입기록.지역, 사원출입기록.입출입시간
FROM (SELECT 부서관리자.사원번호
      FROM (SELECT 부서번호 FROM 부서 WHERE 부서.비고 = 'ACTIVE') AS 부서
               INNER JOIN (SELECT 부서번호, 사원번호 FROM 부서관리자 WHERE NOW() BETWEEN 시작일자 AND 종료일자) AS 부서관리자
                          ON 부서관리자.부서번호 = 부서.부서번호
               INNER JOIN (SELECT 급여.사원번호, 급여.연봉 FROM 급여 WHERE NOW() BETWEEN 시작일자 AND 종료일자) 급여
                          ON 급여.사원번호 = 부서관리자.사원번호
      ORDER BY 급여.연봉 DESC
      LIMIT 5) AS 상위연봉관리자
         INNER JOIN (SELECT 사원번호, 직급명 FROM 직급 WHERE NOW() BETWEEN 시작일자 AND 종료일자) AS 직급
                    ON 상위연봉관리자.사원번호 = 직급.사원번호
         INNER JOIN (SELECT 사원번호, 이름 FROM 사원) AS 사원
                    ON 상위연봉관리자.사원번호 = 사원.사원번호
         INNER JOIN (SELECT * FROM 사원출입기록 WHERE 입출입구분 = 'O') AS 사원출입기록
                    ON 상위연봉관리자.사원번호 = 사원출입기록.사원번호
ORDER BY 사원.사원번호;




CREATE INDEX I_사원번호 ON 사원출입기록 (사원번호);
