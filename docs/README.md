# 🚀 1단계 - 화면 응답 개선하기

요구사항
- 저장소를 활용하여 아래 요구사항을 해결합니다.
- README 에 있는 질문에 답을 추가한 후 PR을 보내고 리뷰요청을 합니다.

- [X] 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
  - 개선 전 / 후를 직접 계측하여 확인
  - 개선전 목표는 다음의 [기존 미션](https://github.com/next-step/infra-subway-monitoring/tree/14km/docs) 페이지에서 기반

## 개선전 목표
타사에 비하여 모바일과 Desktop 성능에 많은 지표가 저하로 보임
- 모바일 기준 네이버 FCP(2.2초), 카카오 LCP(5.8초), TTI(5.4초) 기준으로 최소 성능 향상을 목표
- 더 나아가 FCP(1.8)초, LCP(2.5)초, TTI(5초)를 기준으로 우수한 사용자 경험을 제공할 수 있어야함.

---
## 캐싱 설정시 참고 문서
- [스프링 캐시](https://blog.outsider.ne.kr/1094)
- [스프링 Cache](https://jaehun2841.github.io/2018/11/07/2018-10-03-spring-ehcache/#%EB%93%A4%EC%96%B4%EA%B0%80%EB%A9%B0)

---

# 🚀 2단계 - 스케일 아웃 (with ASG)

## 실습

- Spring Boot에 컨테이너 설정 및 HTTP 캐싱 적용하기 소스코드는 Spring Boot 학습 저장소의 step1-container-http 브랜치 참고하시면 되어요
  - git clone https://github.com/woowacourse/jwp-spring-boot
  - git checkout -t origin/step1-container-http

### 캐싱 설정, 테스트 코드
- myblog.WebMvcConfig: Spring Boot에서 캐싱, ETag 설정 
- support.handlebars.BlogHandlebarsHelper: 캐싱 무효화를 위한 Handlerbars.java template engine Helper 
- Helper가 사용된 곳은 src/main/resources/templates의 include/header.html에서 확인 가능합니다. 
- myblog.web.StaticResourcesTest: 테스트 코드를 활용해 ETag 학습할 수 있어요.

### 미션 요구사항
- 미션1: 모든 정적 자원에 대해 no-cache, private 설정을 하고 테스트 코드를 통해 검증합니다. 
- 미션2: 확장자는 css인 경우는 max-age를 1년, js인 경우는 no-cache, private 설정을 합니다. 
- 미션3: 모든 정적 자원에 대해 no-cache, no-store 설정을 한다. 가능한가요?

## 요구사항

- [X] Spring Boot에 HTTP Cache, gzip 설정하기
- [X] Launch Template 작성하기
- [X] Auto Scaling Group 생성하기
- [X] Smoke, Load, Stress 테스트 후 결과를 기록

## Web Cache 다루기

- [웹 서비스 캐시 똑똑하게 다루기](https://toss.tech/article/smart-web-service-cache)

---

# 🚀 3단계 - 쿼리 최적화

* 요구사항

- [X] 활동중인(Active) 부서의 현재 부서관리자(manager) 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실(O)했는지 조회해보세요.
  - (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)
- [X] 인덱스 설정을 추가하지 않고 1s 이하로 반환합니다.
  - M1의 경우엔 시간 제약사항을 달성하기 어렵습니다. 2배를 기준으로 해보시고 어렵다면, 일단 리뷰요청 부탁드려요
- 급여 테이블의 사용여부 필드는 사용하지 않습니다. 현재 근무중인지 여부는 종료일자 필드로 판단해주세요.
