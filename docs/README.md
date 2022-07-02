## step2 스케일 아웃
실습
- Spring Boot에 컨테이너 설정 및 HTTP 캐싱 적용하기
```shell
git clone https://github.com/woowacourse/jwp-spring-boot
git checkout -t origin/step1-container-http
```

#### 캐싱 설정, 테스트 코드
- myblog.WebMvcConfig: Spring Boot에서 캐싱, ETag 설정
- support.handlebars.BlogHandlebarsHelper: 캐싱 무효화를 위한 Handlerbars.java template engine Helper
- Helper가 사용된 곳은 src/main/resources/templates의 include/header.html에서 확인 가능합니다.
- myblog.web.StaticResourcesTest: 테스트 코드를 활용해 ETag 학습할 수 있어요.
#### 미션 요구사항
- 미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다.
- 미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다.
- 미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?
  
### 요구사항
- [] springboot에 HTTP Cache, gzip 설정하기
- [] Launch Template 작성하기
- [] Auto Scaling Group 생성하기
- [] Smoke, Load, Stress 테스트 후 결과를 기록
