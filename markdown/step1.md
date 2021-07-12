# 1단계 - 화면 응답 개선하기

1. 성능 개선 결과를 공유해주세요.
   - [ ] Smoke, Load, Stress 테스트 결과 요청시간 50ms 이하로 개선
   - [X] 개선전 Smoke 테스트 결과 [바로가기](../k6/before/smoke_test_result.md)
   - [X] 개선전 Load 테스트 결과 [바로가기](../k6/before/load_test_result.md)
   - [X] 개선전 Stress 테스트 결과 [바로가기](../k6/before/stress_test_result.md)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요.
   1. 웹서버 개선하기
      - 개선전 크롬 개발자 도구를 통해 Fast 3G, No cache 환경에서 테스트
        - 메인페이지 17.26s 로딩걸림
      - 힌트를 참고하여 nginx 최적화
        - CPU Core에 맞는 적절한 Worker 프로세스를 할당
        - 워커 커넥션 10240 설정
        - gzip 압축 설정 후 WebPageTest에서 A 받음
        - add_header Strict-Transport-Security "max-age=31536000" always 설정
        - access_log off
        - http2 적용
      - 개선 후 크롬 개발자 도구 같은 환경에서 6.67초로 대략 3배 감소
   2. WAS 개선하기
      - ㅇ