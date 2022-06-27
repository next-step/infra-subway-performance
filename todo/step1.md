# 🚀 1단계 - 화면 응답 개선하기
## 요구 사항
- 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
  - 개선 전 / 후를 직접 계측하여 확인

### 📚 Todo List 📚
- [x] yaml 파일 구성하기
- [x] 성능 개선 전 smoke, stress, load 테스트 결과 확인
  - [stress.js](https://github.com/mond-page/infra-subway-monitoring/blob/step3/todo/stress/stress.js)
  - [smoke.js](https://github.com/mond-page/infra-subway-monitoring/blob/step3/todo/smoke/smoke.js)
  - [load.js](https://github.com/mond-page/infra-subway-monitoring/blob/step3/todo/load/load.js)
- [x] Reverse Proxy 개선하기
  - [x] gzip 압축하기
  - [x] cache 처리하기
  - TLS, HTTP/2는 성능 개선 전 해둔 상태
- [ ] WAS 성능 개선하기
- [ ] 성능 개선 후 smoke, stress, load 테스트 결과 확인
- [ ] README에 답변 추가하기
