# 8주차 - 안정적인 서비스 만들기
## 2단계 - 2단계 - 스케일 아웃 (with ASG)

## 요구사항
- [x] springboot에 HTTP Cache, gzip 설정하기
- [ ] Launch Template 작성하기
- [ ] Auto Scaling Group 생성하기
- [ ] Smoke, Load, Stress 테스트 후 결과를 기록


- [x] 미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
- [x] 미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
- [x] 미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?
  - no-store의 경우 캐시를 하지 않는다는 설정인데, no-cache는 매번 유효성을 확인한다는 설정이라서
  <br>두 설정을 함께쓰는 의미가 없다고 생각합니다!
  ```
  * no-cache, no-store
    - `no-cache`: 매 요청마다 `ETag`를 통해 자원의 유효성을 확인. Cache-Control의 `max-age=0`와 같음
    - `no-store`: 자원을 캐시하지 않음
  * private, public
    - `public`: 중간 단계를 포함해 모든 캐시 서버에 캐시가 가능
    - `private`: 요청한 사용자만 캐시할 수 있음
  ```
---

* Spring Boot에 컨테이너 설정 및 HTTP 캐싱 적용하기
  - 소스코드는 Spring Boot 학습 저장소의 step1-container-http 브랜치 참고
    ```
    git clone https://github.com/woowacourse/jwp-spring-boot
    git checkout -t origin/step1-container-http
    ```


* 캐싱 설정, 테스트 코드
  - myblog.WebMvcConfig: Spring Boot에서 캐싱, ETag 설정
  - support.handlebars.BlogHandlebarsHelper: 캐싱 무효화를 위한 Handlerbars.java template engine Helper
    - Helper가 사용된 곳은 src/main/resources/templates의 include/header.html에서 확인 가능합니다.
  - myblog.web.StaticResourcesTest: 테스트 코드를 활용해 ETag 학습할 수 있어요.



* Spring Boot에 gzip 설정하기
```
# gzip 압축
server.compression.enabled: true
server.compression.mime-types: text/html,text/plain,text/css,application/javascript,application/json
server.compression.min-response-size: 500
```





