## 안정적인 서비스 만들기
### 1단계 - 화면 응답 개선하기
#### 요구사항
```
[X] Reverse Proxy 개선하기
    [X] gzip 압축
    [X] cache
    [X] TLS, HTTP/2 설정
[X] WAS 성능 개선하기
    [X] Spring Data Cache
[X] 부하 테스트(smoke, load, stress)
```

<br />

#### 캐시 적용 기준
* 사용자가 가장 많이 요청할 것으로 추측되는 요청들은 `메인 페이지 - 경로 검색 페이지 - 경로 검색 요청`로 이어지는 흐름이라고 판단했습니다.
  * 위의 판단을 기준으로 해당 흐름과 관련있는 처리 메서드들에 대해 집중적으로 `cache`를 적용하였습니다.
  * 지하철 호선 목록
  * 다익스트라 알고리즘을 이용해 최단거리 탐색
  * 지하철 역 조회

<br />

#### pageSpeed 개선 결과
| desktop | FCP  | SI   | LCP  | TTI  |
|--------|------|------|------|------|
| 기존     | 2.7s | 2.7s | 2.8s | 2.8s |
| 개선     | 1.2s | 1.6s | 1.3s | 1.3s |

<br />

<b> https://jisu1211.kro.kr에서 수행된 결과입니다. </b>

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* `src/main/resources/static/loadtest`에 포함해두었습니다.
  * before: 개선 전의 부하 테스트 결과를 포함하였습니다.
  * after: 개선 후의 부하 테스트 결과를 포함하였습니다.
    * nginx/gzip: nginx gzip 압축만 개선했을 때의 부하 테스트 결과입니다.
    * nginx/cache: nginx gzip 압축, cache를 적용했을 때의 부하 테스트 결과입니다.
    * nginx/http2: nginx gzip 압축, cache 적용, http2를 적용했을 때의 부하 테스트 결과입니다.
    * all: nginx 개선과 was 개선을 모두 적용했을 때의 부하 테스트 결과입니다.
  * smoke 테스트는 1분, load 테스트는 30분, stress 테스트는 1시간 10분 동안 수행하였습니다.

<br />

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
* Reverse Proxy 개선
  * gzip 압축
  * cache 적용
  * TLS, HTTP/2 설정
* WAS 성능 개선
  * Spring Data Cache
---

### 2단계 - 스케일 아웃
#### 요구사항
```
[X] springboot에서 HTTP Cache, gzip 설정하기
[X] Launch Template 작성하기
[X] Auto Scaling Group 생성하기
[X] Smoke, Load, Stress 테스트 후 결과를 기록
```

<br />

#### ETag로 캐시된 응답에 대한 유효성 검사 수행
* 서버는 ETag HTTP 헤더를 사용하여 유효성 검사 토큰을 전달한다.
* 유효성 검사 토큰을 사용하면 효율적인 리소스 업데이트 검사가 가능하다. 리소스가 변경되지 않은 경우 데이터가 전송되지 않는다.
  * <b> If-None-Match </b>: 캐시된 리소스의 `ETag`값과 현재 서버 리소스의 `ETag` 값이 같은지 확인한다.
  * <b> If-Modified-Since </b>: 캐시된 리소스의 `Last-Modified` 값 이후에 서버 리소스가 수정되었는지 확인한다.

#### Cache-Control
* 각 리소스는 Cache-Control HTTP 헤더를 통해 캐싱 정책을 정의할 수 있다.
* Cache-Control 지시문은 응답을 캐시할 수 있는 사용자, 해당 조건 및 기간을 제어한다.
* no-cache, no-store
  * `no-cache`: 매 요청마다 중간에 있는 캐시 서버들은 `ETag`를 통해 자원의 유효성을 확인한다. Cache-Control의 `max-age=0`와 같다.
    * 로컬 저장소에 저장하는 것을 막지는 않아서 변경이 있는 경우에만 바로 캐시에 업데이트해 최신 상태로 유지한다.
  * `no-store`: 자원은 캐시하지 않는다.
    * 로컬 저장소에 저장되는 것을 막아 데이터가 유출되는 것을 막기 위함이다.
* private, public
  * `public`: 중간 단계를 포함해 모든 캐시 서버에 캐시가 가능하다.
  * `private`: 요청한 사용자만 캐시할 수 있다. 즉, CDN과 같은 범용 캐시서버에서도 캐시할 수 있긴 하지만 그 응답을 모든 사용자에게 공유할 수는 없어서
     결국 최종 사용자의 브라우저에서만 응답을 캐시할 수 있다.

<br />

1. Launch Template 링크를 공유해주세요.
* [Launch Template 링크](https://ap-northeast-2.console.aws.amazon.com/ec2/v2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-019f4c7c7f0594cc0)

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
* `src/main/resources/static/scaleout`에 포함해두었습니다.
* 부하 테스트 진행 시 CPU 수치가 크게 올라가지 않아 Auto Scaling이 되지 않고 `Request Failed`만 발생하여 Auto Scaling이 발생하는 CPU 수치를 10%로 낮춰서 테스트하였습니다.
  그 결과로, VUser 350을 테스트하는데 6개의 ec2가 생성되었고 모든 요청이 정상적으로 수행되었습니다.
  6개의 ec2로 요청이 분산되어 CPU 수치도 낮아지고 요청도 정상적으로 빠르게 수행되는 것을 확인할 수 있었습니다.

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)
* `src/main/resources/static/scaleout`에 포함해두었습니다.

---

### 3단계 - 쿼리 최적화
#### 요구사항
```
[X] 활동중인(Active) 부서의 현재 부서관리자(manager) 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실(O)했는지 조회해보세요.
    * (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
[X] 인덱스 설정을 추가하지 않고 200ms 이하로 반환합니다.
    * M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 400ms를 기준으로 해보시고 어렵다면, 일단 리뷰요청 부탁드려요.
```

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 200ms 이하(M1의 경우 400ms)로 반환하도록 쿼리를 작성하세요.
```
활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
```
* M2 맥북을 사용하고 있어 400ms를 목표로 쿼리를 작성하였습니다.
* 쿼리는 `src/main/resources/static/query-tuning/tuning.sql`에 포함해두었습니다.
  * 각 단계별 진행을 포함하였고 최종 결과 쿼리는 5번 쿼리입니다.
  * 각 단계별 실행계획 이미지를 `src/main/resources/static/query-tuning/images`에 포함해두었습니다.

---

### 4단계 - 인덱스 설계
#### 요구사항
```
[X] 주어진 데이터셋을 활용하여 아래 조회 결과를 100ms 이하로 반환
    * M1의 경우에는 시간 제약사항을 달성하기 어려우므로 2배를 기준으로 함
    [X] Coding as a Hobby와 같은 결과를 반환
    [X] 프로그래머별로 해당하는 병원 이름을 반환(covid.id, hospital.name)
    [X] 프로그래밍이 취미인 학생 혹은 주니어(0-2년)들이 다닌 병원 이름을 반환하고 programmer.id 기준으로 정렬
        (covid.id, hospital.name, programmer.hobby, programmer.dev_type, programmer.years_coding)
    [X] 서울대병원에 다닌 20대 India 환자들을 병원에 머문 기간별로 집계(covid.stay)
    [X] 서울대병원에 다닌 30대 환자들을 운동 횟수별로 집계(programmer.exercise)
```

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요.
* M2 맥북을 사용하고 있어 200ms를 목표로 쿼리를 작성하였습니다.
* 쿼리는 `src/main/resources/static/index-tuning/tuning.sql`에 포함해두었습니다.
  * 각 쿼리별 실행계획 이미지를 `src/main/resources/static/index-tuning/images`에 포함해두었습니다.

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
