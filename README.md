<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 인프라공방 샘플 서비스 - 지하철 노선도

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew clean build
```
<br>

## 미션

* 미션 진행 후에 아래 질문의 답을 작성하여 PR을 보내주세요.


## 1단계 - 화면 응답 개선하기
### 요구사항
- 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
  - 개선 전/후를 직접 계측하여 확인
- [ ] reverse proxy 개선
    - cache 활성화
    - http2 적용
    - gzip 압축 적용
- [ ] was 개선
  - redis 적용
- [ ] scale out - 초간단 blue-green 배포 구성
- [ ] 정적 파일 경량화

### 웹 성능
|               | RUNNINGMAP | 서울교통공사 | 네이버지도  | 카카오맵 |
|---------------|------------|----------|----------|--------|
| Total Bytes   | 2462kb     | 1067kb   | 941kb    | 1456kb |

- First Contentful Paint : 첫 번째 텍스트 또는 이미지가 표시되는 시간
- Largest Contentful Paint : 최대 텍스트 또는 이미지가 표시되는 시간
- Time to Interactive : 사용할 수 있을 때까지 걸리는 시간(완전히 페이지와 상호작용할 수 있게 될 때까지 걸리는 시간)
- Total Blocking Time : FCP와 상호작용 시간 사이의 모든 시간의 합
- Cumulative Layout Shift : 영역 내 이동을 측정

#### MOBILE

|     | RUNNINGMAP                              | 서울교통공사                              | 네이버지도                                   | 카카오맵                                   |
|-----|-----------------------------------------|-------------------------------------|-----------------------------------------|----------------------------------------|
| 성능 | <span style="color:red">34</span>       | <span style="color:red">33</span>   | <span style="color:orange">53</span>    | <span style="color:orange">66</span>   |
| FCP | <span style="color:red">14.9s</span>    | <span style="color:red">6.4s</span> | <span style="color:orange">2.4s</span>  | <span style="color:green">1.7s</span>  |
| LCP | <span style="color:red">15.4s</span>    | <span style="color:red">6.8s</span> | <span style="color:red">7.5s</span>     | <span style="color:red">6.8s</span>    |
| TTI | <span style="color:red">15.4s</span>    | <span style="color:red">8.5s</span> | <span style="color:orange">6.5s</span>  | <span style="color:orange">5.2s</span> |
| TBT | <span style="color:orange">460ms</span> | <span style="color:red">790</span>  | <span style="color:orange">420ms</span> | <span style="color:green">100ms</span> |
| CLS | <span style="color:green">0.042</span>  | <span style="color:green">0</span>  | <span style="color:green">0.03</span>   | <span style="color:green">0.005</span> |

#### PC

|     | RUNNINGMAP                             | 서울교통공사                                       | 네이버지도                                  | 카카오맵                                   |
|-----|----------------------------------------|----------------------------------------------|----------------------------------------|----------------------------------------|
| 성능 | <span style="color:orange">65</span>   | <span style="color:red">44</span>            | <span style="color:green">90</span>    | <span style="color:green">90</span>    |
| FCP | <span style="color:red">2.7s</span>    | <span style="color:orange1">1.4s</span>      | <span style="color:green">0.5s</span>  | <span style="color:green">0.5s</span>  |
| LCP | <span style="color:red">2.8s</span>    | <span style="color:red">3.8s</span>          | <span style="color:orange">1.5s</span> | <span style="color:orange">1.4s</span> |
| TTI | <span style="color:orange">2.8s</span> | <span style="color:green">2.2s</span>        | <span style="color:green">1.2s</span>  | <span style="color:green">0.7s</span>  |
| TBT | <span style="color:green">50ms</span>  | <span style="color:red">620ms</span> | <span style="color:green">10ms</span>  | <span style="color:green">0ms</span>   |
| CLS | <span style="color:green">0.004</span> | <span style="color:green">0.001</span>       | <span style="color:green">0.006</span> | <span style="color:green">0.039</span> |

#### 성능 개선 계획
<table>
  <tr>
    <td rowspan="2"></td>
    <td colspan="2" align="center">AS - IS</td>
    <td colspan="2" align="center">TO - BE</td>
  </tr>
  <tr>
    <td>MOBILE</td>
    <td>PC</td>
    <td>MOBILE</td>
    <td>PC</td>
  </tr>
  <tr>
    <td>TTI</td>
    <td>15.3s</td>
    <td>2.9s</td>
    <td>5s</td>
    <td>0.5s</td>
  </tr>
  <tr>
    <td>FCP</td>
    <td>14.7s</td>
    <td>2.8s</td>
    <td>1.7s</td>
    <td>0.5s</td>
  </tr>
  <tr>
    <td>성능</td>
    <td>33</td>
    <td>67</td>
    <td>80</td>
    <td>90</td>
  </tr>
</table>

1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

## 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

## 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요
