설정값 계산
```
1일 사용자 수 : 200,000
1명당 1일 평균 요청 건수 : 5 
피크 시간대의 집중률 : 2 (08-09시 하루 평균 지하철 승하차 인원은 12-13시 보다 2배 높음)

Throughput : 시간당 평균, 최대 처리량
- 1일 사용자 수 X 1명당 1일 평균 요청 건수 = 1일 총접속 수 = 1,000,000
- 1일 총접속 수 / 86,400s = 1일 평균 RPS = 11.574
- 1일 평균 RPS X 피크 시간대의 집중률 = 1일 최대 RPS = 23.148

Latency : 지연 시간
- ~100ms

VUser : 가상 사용자
- Request Rate : measured by the number of requests per second
- R : the number of requests per VU iteration
- T : a value larger than the time needed to complete a VU iteration

T = (R * http_req_duration) (+L) ; 내부망에서 테스트할 경우 예상 latency를 추가한다
VUser = (목표 rps * T) / R

예를 들어, 5개의 요청이 있고 왕복 시간이 0.5s, 지연 시간을 0.1s로 한다면
평균 트래픽 VUser = 11.574 * (5 * 0.5 + 0.1) / 5 = 30.092 → 31
최대 트래픽 VUser = 23.148 * (5 * 0.5 + 0.1) / 5 = 60.184 → 61
```

목표값 설정
```
Smoke
- 평균 VUser : 2
- 최대 VUser : 2
- Throughput : ~115.74
- Latency : ~100ms
- 측정 시간 : 10분

Load
- 평균 VUser : 31
- 최대 VUser : 61
- Throughput : ~115.74
- Latency : ~100ms
- 측정 시간 : 30분

Stress
- VUser : 점진적으로 증가시킨다
- 측정 시간 : 10분
```
