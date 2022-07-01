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
  - [x] ~~TLS, HTTP/2는 성능 개선 전 해둔 상태~~
  - [x] HTTP2 설정하기
```shell
 ~ curl -i https://subway.mond.page
HTTP/2 200
server: nginx/1.21.6
date: Tue, 28 Jun 2022 17:29:26 GMT
content-type: text/html;charset=UTF-8
content-length: 1000
vary: Accept-Encoding
content-language: en
strict-transport-security: max-age=31536000
```
- [x] WAS 성능 개선하기
  - [x] redis로 cache 처리 
- [x] 성능 개선 후 smoke, stress, load 테스트 결과 확인
- [x] README에 답변 추가하기
