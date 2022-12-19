# 안정적인 서비스 만들기

## Step1. 화면 응답 개선하기

### 요구사항

- [x] 부하테스트 각 시나리오의 요청시간을 목표값 이하로 개선
  - [x] Reverse Proxy 개선하기
    - [x] gzip 압축
    - [x] cache
    - [x] TLS, HTTP/2 설정
  - [x] WAS 성능 개선하기
    - [x] Spring Data Cache
      - [x] StationService 캐시적용
      - [x] LineService 캐시적용
      - [x] MapService 캐시적용
      - 캐시 적용시, 추가보다는 evict 부분에 좀 더 신경쓰기
    - [-] 비동기 처리 : 외부 API 를 사용하지 않으므로 현재 서비스에선 미적용.
- [x] 개선 전 / 후를 직접 계측하여 확인
  - [x] 개선 전 k6 확인
    - [x] 스크립트 작성 및 결과값 추가
  - [x] 개선 후 k6 확인
    - [x] Reverse Proxy 개선하기 적용 후 결과
    - [x] 캐시 적용 후 결과
      - [x] 캐시 적용 후 결과 (MAX 727)
      - [x] 캐시 적용 후 결과 (MAX 50) - task.js의 STATION_MAX_COUNT 값을 변경

### 결과 정리
- 목표 : p(99) <200

- 개선 전 -> 1차개선(리버스프록시) -> 2차개선 (캐시적용) -> 3차개선 (캐시적용 - 경로제한)
- 검증 항목 : http-req-duration p(95)
  - smoke
    - 30ms -> 22ms -> 17ms
  - load
    - 25ms -> 15ms -> 15ms
  - stress
    - 350ms -> 21ms -> 49ms -> 17ms
- 결론
  - 가장 큰 결과 변화는, 리버스프록시 개선 (gzip 압축)에서 이루어졌다. 
  - 캐시 적용의 경우, 727개의 항목 경로조회에서는 cash-hit가 많지 않을 것같아 유의미한 변화가 없었음.
    - 오히려 캐시 저장하는 프로세스가 추가되어서인지 스트레스 테스트에서는 오히려 응답시간이 지연됨
  - 그리하여, 경로조회 대상 역을 50개로 줄였을때는 cache-hit가 많이 올라가서인지 결과 개선이 많이 되었음


## Step2. 스케일 아웃

## 요구사항
- [x] 실습
  - [x] WebMvcConfig 캐시컨트롤, Etag 설정
  - [x] Etag 동작 Test코드를 통해 확인
- [x] springboot에 HTTP Cache, gzip 설정하기
- [x] 배포 스크립트 업로드
- [x] Launch Template 작성하기
  - [x] Ubuntu 이미지 선택
  - [x] EC2 설정
  - [x] IAM 권한 설정
  - [x] 배포 명령어 설정
- [x] Auto Scaling Group 생성하기
  - [x] Launch Template 설정
  - [x] 네트워크 설정
  - [x] 로드밸런서 생성
  - [x] 타겟 대상 생성
  - [x] 그룹 크기 설정
  - [x] 임계값 설정
- [x] DNS 설정
- [x] TLS 설정
- [x] Smoke, Load, Stress 테스트 후 결과를 기록
  - smoke
    - <img src="/k6/smoke/smoke_asg.png">
  - load
    - <img src="/k6/smoke/load_asg.png">
  - stress
    - <img src="/k6/smoke/stress_asg.png">
    - <img src="/k6/smoke/stress_asg_550.png">
  
  
## 결과 정리

- ASG 전(캐싱) <> 후
  - smoke : 18ms <> 21ms
  - load : 15ms <> 21ms
  - stress : 17ms <> 25ms

- ASG 적용 이후, 전반적으로 속도는 증가하였다. 
  - 예상 이유
    - 인스턴스 타입을 줄임 -> t4g ->t3m
    - 캐시와 프록시 설정으로 이미 stress 테스트 350(~550)에서는 부하문제가 없었어서 인스턴스 추가생성 없었음

