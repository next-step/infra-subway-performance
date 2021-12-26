<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 인프라공방 샘플 서비스 - 지하철 노선도

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew clean build
```
<br>

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.

### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
   - Load : [main](/k6/main/load_main_result.md), [line-update](/k6/line-update/load_update_result.md), [paths](/k6/path/load_path_result.md)
   - Smoke : [main](/k6/main/smoke_main_result.md), [line-update](/k6/line-update/smoke_update_result.md), [paths](/k6/path/smoke_path_result.md)
   - Stress : [main](/k6/main/stress_main_result.md), [line-update](/k6/line-update/stress_update_result.md), [paths](/k6/path/stress_path_result.md)
2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   ![img.png](img.png)
   ![img_1.png](img_1.png)
- 페이지에 대한 성능은 별로 차이가 없음을 볼 수 있습니다..
- k6로 다시 테스트 했을 때 paths 조회에서 http_req_duration이 4~5배 가량 좋아진 것을 볼 수 있습니다.
- [paths stress test](/k6/path/stress_path_result.md), [paths load test](/k6/path/load_path_result.md)
---

### 2단계 - 조회 성능 개선하기
1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

2. 페이징 쿼리를 적용한 API endpoint를 알려주세요

- 쿼리 최적화
   - [X] 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.
     (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
   1. 쿼리 작성만으로 1s 이하로 반환한다.
    ```
    select t1.사원번호, 이름, 연봉, 직급명, max(입출입시간) as 입출입시간, 지역, 입출입구분
    from
      (SELECT dm.사원번호, emp.이름, pay.연봉, job.직급명
      FROM 부서관리자 dm inner join 직급 job on dm.사원번호 = job.사원번호
      inner join 급여 pay on dm.사원번호 = pay.사원번호
      inner join 사원 emp on dm.사원번호 = emp.사원번호
      WHERE dm.부서번호 IN (SELECT 부서번호 I_출입문I_출입문I_출입문
                        FROM 부서
                        where upper(비고) = 'ACTIVE')
      AND pay.종료일자 > now()
      order by pay.연봉 desc
      limit 5) t1
      inner join 사원출입기록 io on t1.사원번호 = io.사원번호 AND io.입출입구분 = 'O'
    group by t1.사원번호, 이름, 연봉, 직급명, 입출입시간, 지역, 입출입구분
    order by t1.연봉 desc, io.입출입시간 desc;
    ```
   - 조회 시간 : *0.356 sec*
   2. 인덱스 설정을 추가하여 50 ms 이하로 반환한다.
   - `create index I_입출입구분 on 사원출입기록 (입출입구분);` 인덱스 추가
   - 조회 시간 : *0.266 sec*

- 인덱스 설계
   - 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
      - [ ] Coding as a Hobby 와 같은 결과를 반환하세요.
      - [ ] 프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)
      - [ ] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)
      - [ ] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)
      - [ ] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)
- [ ] 페이징 쿼리 적용해보기
