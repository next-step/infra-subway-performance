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
#### 개선 전
- lighthouse 테스트

![lighthouse_개선전](https://user-images.githubusercontent.com/36442984/147405759-db2df1d1-fd26-4528-9569-b8d9fd0c79d7.png)
- smoke 테스트

![smoke_개선전](https://user-images.githubusercontent.com/36442984/147405758-6731e09d-7db8-4748-9a94-b390c2eccce0.png)
- load 테스트

![load_개선전](https://user-images.githubusercontent.com/36442984/147405757-c1ab7ffe-d8eb-454d-9d74-88494e6ae564.png)
- stress 테스트

![stress_개선전](https://user-images.githubusercontent.com/36442984/147405755-0161c3fa-120d-4214-839f-cf137ea551fc.png)
--- 
#### 개선 후
- lighthouse 테스트

![lighthouse_개선후](https://user-images.githubusercontent.com/36442984/147405754-7fe5a40e-ccc6-4b25-bd4c-e26701fe1546.png)
- smoke 테스트

![smoke_개선후](https://user-images.githubusercontent.com/36442984/147405752-fd51d61e-9674-406b-90d7-b72117779447.png)
- load 테스트

![load_개선후](https://user-images.githubusercontent.com/36442984/147405751-27fb28e6-c33c-44e8-8269-157c5b74fe9d.png)
- stress 테스트

![stress_개선후](https://user-images.githubusercontent.com/36442984/147405748-c41d9877-04ec-4085-b1cf-878aabea4f57.png)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
- Nginx와 Redis를 통한 개선을 시도해보았으나 결과적으로는 Nginx를 통한 성능 개선만 적용하였습니다. Redis의 경우 AOP로 인한 성능 저하가 캐시로 얻는 이점보다 더 큰 것으로 보였습니다.
- Nginx 성능 개선 요소
  1. response에 대한 gzip 압축
  2. 정적 파일들에 대한 캐싱
  3. Http/2 적용
  4. Strict-Transport-Security 헤더를 통해 클라이언트에서 http로 요청을 보내지 않도록 하여 redirect되는 요청을 최소화
---

### 2단계 - 조회 성능 개선하기

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요 

   * [X] **활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요.(사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)**
   
   ![스크린샷 2021-12-30 오전 1 13 50](https://user-images.githubusercontent.com/36442984/147682403-4643090f-587c-4194-93bf-84de465aa5b0.png)
   - 개선 전 : 602 ms 
   
   ![1_before](https://user-images.githubusercontent.com/36442984/147682606-2ec54e7b-33ff-4a4c-be07-cbbc62a7eb34.png)
   - 개선 후 : 1.5 ms
   
   ![1_after](https://user-images.githubusercontent.com/36442984/147682609-05981907-55c8-4ea2-8566-ecdbf0d153a0.png)
   
   * [X] **Coding as a Hobby 와 같은 결과를 반환하세요.** : programmer의 pk 추가를 통해 count 성능 향상

   ![스크린샷 2021-12-30 오전 2 20 37](https://user-images.githubusercontent.com/36442984/147687726-6fe755a8-10e7-4d7e-a64b-aa3b3b9d7e2f.png)

   ![2](https://user-images.githubusercontent.com/36442984/147687744-73f6028b-9a7c-41ff-a202-0d4368a3fcd8.png)

   * [X] **프로그래머별로 해당하는 병원 이름을 반환하세요. (covid.id, hospital.name)**
   
   ![스크린샷 2021-12-30 오전 2 34 11](https://user-images.githubusercontent.com/36442984/147688744-1d771efd-75bd-4c93-adb3-f102dc534dc2.png)

   ![3](https://user-images.githubusercontent.com/36442984/147690903-d3a98844-4533-4eb4-8286-8e1984a035be.png)

   * [X] **프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 user.id 기준으로 정렬하세요. (covid.id, hospital.name, user.Hobby, user.DevType, user.YearsCoding)**
   : 이 경우, 이미 충분한 성능이 나와서 인덱스를 추가하지 않음. 과도한 인덱스는 insert, update, delete 성능을 저하시키기 때문

   ![스크린샷 2021-12-30 오전 3 04 41](https://user-images.githubusercontent.com/36442984/147691003-9d419159-9f8e-4255-8aa1-45631e6545d1.png)

   ![4](https://user-images.githubusercontent.com/36442984/147690907-a9f0f3a4-b0ff-4841-a6c4-97568cde9eff.png)

   * [X] **서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계하세요. (covid.Stay)**
   
   ![스크린샷 2021-12-30 오전 3 35 54](https://user-images.githubusercontent.com/36442984/147693095-c77c5565-4001-4a7c-a286-7252aca230c1.png)

   ![5](https://user-images.githubusercontent.com/36442984/147693098-aadc90ac-a771-4a89-9b1f-3764be9f53a6.png)

   * [X] **서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계하세요. (user.Exercise)**

   ![스크린샷 2021-12-30 오전 3 52 01](https://user-images.githubusercontent.com/36442984/147694351-fa3b853e-7c13-4764-8e68-5e6b01c81ed3.png)
   
   ![7](https://user-images.githubusercontent.com/36442984/147694347-ae032bc6-8572-4ed9-8822-75eaab0507d6.png)

   
2. 페이징 쿼리를 적용한 API endpoint를 알려주세요 : GET /stations
