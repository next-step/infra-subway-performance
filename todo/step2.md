# 🚀 2단계 - 스케일 아웃 (with ASG)
## 요구사항
- springboot에 HTTP Cache, gzip 설정하기
- Launch Template 작성하기
- Auto Scaling Group 생성하기
- Smoke, Load, Stress 테스트 후 결과를 기록

### 📚 Todo List 📚
- [x] 리뷰사항 반영
- [ ] SpringBoot gzip, Cache 설정
  - [x] gzip 설정
````shell
> curl -i http://localhost:8080

HTTP/1.1 200
vary: accept-encoding
Content-Type: text/html;charset=UTF-8
Content-Language: ko-KR
Content-Length: 1000
Date: Wed, 29 Jun 2022 12:13:07 GMT
````
  - [ ] cache 설정
- [x] LaunchTemplate 작성
- [ ] Auto Scaling Group 생성하기
- [ ] smoke, stress, load 테스트 실행
- [ ] README 답 작성
