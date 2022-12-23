# 8주차 - 안정적인 서비스 만들기
## 1단계 - 화면 응답 개선하기

## 요구사항

- [ ] 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
  - 개선 전 / 후를 직접 계측하여 확인

---

### 개선 항목

* Reverse Proxy Server (nginx)
  1. gzip 압축
  2. chashing
  3. 프로토콜 개선 (HTTP 2.0)

* WAS
  1. Redis 적용 (Spring Date chaching)

---

### 개선 전 성능 측정 및 유사사이트와의 비교

- 웹 성능 측정 결과 (데스크탑 기준)

|             | [runningmap] | [사이버스테이션] | [서울대중교통] | [네이버맵] | [카카오맵] |
|-------------|--------------|-----------|----------|--------|--------|
| FCP         | 2.9s         | 1.4s      | 1.6s     | 0.5s   | 0.5s   |
| LCP         | 2.9s         | 1.6s      | 1.7s     | 1.4s   | 1.0s   |
| TTI         | 4.9s         | 1.9s      | 8.7s     | 0.6s   | 0.6s   |
| TBT         | 1,800ms      | 190ms     | 70ms     | 0ms    | 0ms    |
| CLS         | 0            | 0.001     | 0.002    | 0.006  | 0      |
| Speed Index | 2.9s         | 2.0s      | 4.1s     | 2.7s   | 2.7s   |

- 측정 페이지 URL
    + runningmap : https://runningmap.kro.kr/stations (로딩이 제일 느린 페이지를 측정대상으로 선정)
    + 사이버스테이션 : http://www.seoulmetro.co.kr/kr/cyberStation.do
    + 서울대중교통 : https://bus.go.kr/subWayMain.jsp?mnuNm=3
    + 네이버맵 : https://m.map.naver.com/subway/subwayLine.naver?region=1000
    + 카카오맵 : https://m.map.kakao.com/

- 측정 사이트 : [PageSpeed]

---

### 개선 목표

| 지표  | 목표값  |
|-----|------|
| TTI | 1.5s |
| FCP | 1.0s |
| LCP | 0.5s |

[runningmap]: https://runningmap.kro.kr/stations
[사이버스테이션]: http://www.seoulmetro.co.kr/kr/cyberStation.do
[서울대중교통]: https://bus.go.kr/subWayMain.jsp?mnuNm=3
[네이버맵]: https://m.map.naver.com/subway/subwayLine.naver?region=1000
[카카오맵]: https://m.map.kakao.com/
[PageSpeed]: https://pagespeed.web.dev
[WebPageTest]: https://www.webpagetest.org/

* PageSpeed 권장 수정방안 및 그에 따른 예상단축 시간
  - gzip 사용하여 텍스트 압축 `FCP, LCP 1.48s 단축 예상`
  - 렌더링 차단 리소스 제거 `FCP, LCP 0.4s 단축 예상`
  - 사용하지 않는 js파일 지연로딩 `LCP 0.52s 단축 예상`


* 예상 결과
  - FCP 1.88s 단축 예상 `2.9 -> 1.02s`
  - LCP 2.4s 단축 예상 `2.9 -> 0.5s`

---

### 개선작업 전후 비교

* PageSpeed 측정 결과

|                    | FCP  | LCP  | TTI  | TBT     | CLS   | Speed Index |
|--------------------|------|------|------|---------|-------|-------------|
| 개선 작업 전            | 2.9s | 2.9s | 4.9s | 1,800ms | 0     | 2.9s        |
| gzip 압축 적용 후       | 1.2s | 1.3s | 1.3s | 80ms    | 0.004 | 1.5s        |
| nginx caching 적용 후 | 1.2s | 1.2s | 1.2s | 50ms    | 0.004 | 1.6s        |
| HTTP 2.0 적용 후      | 1.1s | 1.2s | 1.2s | 20ms    | 0.004 | 1.3s        |
| Redis 적용 후         | 1.1s | 1.2s | 1.2s | 20ms    | 0.003 | 1.1s        |


| 지표  | 개선 전 | gzip 압축 적용 후 | nginx caching 적용 후 | HTTP 2.0 적용 후 | Radis 적용 후 | 목표값  |
|-----|------|--------------|--------------------|---------------|------------|------|
| TTI | 4.9s | 1.3s         | 1.2s               | 1.2s          | 1.2s       | 1.5s |
| FCP | 2.9s | 1.2s         | 1.2s               | 1.1s          | 1.1s       | 1.0s |
| LCP | 2.9s | 1.3s         | 1.2s               | 1.2s          | 1.2s       | 0.5s |

> FCP와 LCP는 목표값에 도달하지는 못했지만 FCP는 1.8s, LCP는 1.7s 단축되었고
> <br>우선순위를 가장 높게 설정한 TTIsms 4.9s에서 1.2s로 3.7s 단축되어 목표값인 1.5s 보다 좋은 결과를 보였습니다.


* K6로 진행한 smoke, load, stress 테스트 결과

  - http request duration 기준

    | smoke   | load    | stress   |
    |---------|---------|----------|
    | 24.22ms | 27.7ms  | 295.55ms |
    | 26.55ms | 16.45ms | 15.82ms  |

> smoke 테스트에서 개선 작업 후가 전보다 2.33ms 더 큰 duration 값이 출력되었지만
> <br>load 테스트와 stress 테스트에서 개선된 것을 확인할 수 있었습니다.
> <br>특히, stress 테스트의 경우, 개선 전에는 응답을 아예 하지 못하는 구간이 있었지만 (치솟는 RPS!)
> <br>개선 후에는 100% 응답이 확인되었습니다! (VUser 그래프와 RPS 그래프가 동일한 형태!)
> <br>개선작업이 작은 부하에는 큰 개선을 주지는 못하지만 부하가 커질 수록 그 효과가 커짐을 알 수 있었습니다.