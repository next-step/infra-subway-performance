# 안정적인 서비스 만들기

## Step1. 화면 응답 개선하기

### 요구사항

- [ ] 부하테스트 각 시나리오의 요청시간을 목표값 이하로 개선
  - [ ] Reverse Proxy 개선하기
    - [ ] gzip 압축
    - [ ] cache
    - [ ] TLS, HTTP/2 설정
  - [ ] WAS 성능 개선하기
    - [ ] Spring Data Cache
      - [ ] StationService 캐시적용
      - [ ] LineService 캐시적용
      - [ ] MapService 캐시적용
      - 캐시 적용시, 추가보다는 evict 부분에 좀 더 신경쓰기
    - [-] 비동기 처리 : 외부 API 를 사용하지 않으므로 현재 서비스에선 미적용.
- [ ] 개선 전 / 후를 직접 계측하여 확인
  - [ ] 개선 전 k6 확인
    - [ ] 스크립트 작성 및 결과값 추가
  - [ ] 개선 후 k6 확인
