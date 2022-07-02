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


### 1단계 - 화면 응답 개선하기
1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

#### 개선 전

1. Smoke Test(http://3.35.223.220/d/bvXIAmq7z/k6-smoke-testing-results?orgId=1&from=1656599880000&to=1656600599000)
2. Load Test(http://3.35.223.220/d/080n0mqnz/k6-load-testing-results?orgId=1&from=1656597900000&to=1656599100000)
3. Stress Test(http://3.35.223.220/d/TMFSAmq7k/k6-stress-test-result?orgId=1&from=1656598800000&to=1656599699000)

#### 개선 후
1. Smoke Test(http://3.35.223.220/d/bvXIAmq7z/k6-smoke-testing-results?orgId=1&from=1656668640000&to=1656669299000)
2. Load Test(http://3.35.223.220/d/080n0mqnz/k6-load-testing-results?orgId=1&from=1656669300000&to=1656670200000)
3. Stress Test(http://3.35.223.220/d/TMFSAmq7k/k6-stress-test-result?orgId=1&from=1656667140000&to=1656667920000)

#### 성능 비교 표(pageSpeed)

| https://jhsong2580.kro.kr/ | FCP(Sec) | LCP(Sec) | TTI(Sec) | TBT(mSec) |
|:--------------------------:|:--------:|:--------:|:--------:|:---------:|
|            개선전             |   14.8   |   15.3   |   15.4   |    510    |
|     1차 개선 (캐싱/Gzip설정)      |   5.4    |   6.0    |   6.1    |    560    |
|      3차 개선 (Http2설정)       |   5.2    |   5.7    |   5.7    |    400    |


| https://jhsong2580.kro.kr/stations | FCP(Sec) | LCP(Sec) | TTI(Sec) | TBT(mSec) |
|:--------------------------:|:--------:|:--------:|:--------:|:---------:|
|            개선전             |   16.7   |   16.7   |   25.7   |   8_510   |
|      1차 개선 (캐싱/Gzip설정)       |   7.1    |   7.1    |   17.1   |   9_720   |
|      2차 개선 (redis 캐싱설정)       |   6.8    |   6.8    |   13.4   |   6_330   |
|      3차 개선 (Http2설정)       |   6.8    |   6.8    |   12.9   |   5_950   |

| https://jhsong2580.kro.kr/lines | FCP(Sec) | LCP(Sec) | TTI(Sec) | TBT(mSec) |
|:-------------------------------:|:--------:|:--------:|:--------:|:---------:|
|               개선전               |   16.2   |   16.2   |   17.9   |   1_210   |
|        1차 개선 (캐싱/Gzip설정)        |   6.8    |   6.8    |   8.3    |   1_240   |
|       2차 개선 (redis 캐싱설정)        |   6.8    |   6.8    |   8.0    |   1_040   |
|       3차 개선 (Http2설정)         |   6.7    |   6.8    |   7.9    |   1_030   |

#### 성능비교 표(K6)
| testType |                      개선전                      |                      개선후                      | 
|:--------:|:---------------------------------------------:|:---------------------------------------------:|
|  smoke   | <img src="readmeSource/step1/개선전_smoke.png">  | <img src="readmeSource/step1/개선후_smoke.png">  |   
|   load   |  <img src="readmeSource/step1/개선전_load.png">  |  <img src="readmeSource/step1/개선후_load.png">  |   
|  stress  | <img src="readmeSource/step1/개선전_stress.png"> | <img src="readmeSource/step1/개선후_stress.png"> |   
 
###개선 전 후 vuser 수용 가능량

|      | 수용 가능한 vuser |                  Cloudwatch                  |                  비고                  |
|:----:|:------------:|:--------------------------------------------:|:------------------------------------:|
| 개선 전 |     338      | <img src="readmeSource/step1/개선전_vuser.png"> | vuser 338명 이후로 급격하게 요청시간이 늘어남을 알수 있다 |
| 개선 후 |     360      | <img src="readmeSource/step1/개선후_vuser.png"> | vuser 360명 이후로 급격하게 요청시간이 늘어남을 알수 있다 |


2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요
   0. 점검
      1. thread.dump 점검 결과 교착 걸린 부분이나 지나치게 CPU 점유기간이 긴 쓰레드는 없음
   1. 1차 개선
      1. Gzip : 가장 높은 압축률(9)로 압축 설정
      2. Proxy Cache 설정 : css,이미지들을 한달간 캐싱한다.
   2. 2차개선 (/lines, /stations)
      1. line.findAll(), station.findAll()에 대해 캐싱한다
   3. 3차개선 (Http2 설정)


---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.
   https://ap-northeast-2.console.aws.amazon.com/ec2/home?region=ap-northeast-2#LaunchTemplateDetails:launchTemplateId=lt-090ddf89b23bf5e3c
2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)
   <img src="readmeSource/step2/scaleout결과.png">
  
   1. 세션별 Reverse proxy기능을 위해 Target Group에 대해 Stickiness옵션 설정
```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

### 개선 전/후 vuser 수용 가능량

|                        | 수용 가능한 vuser |                       Cloudwatch                       |                  비고                   |
|:----------------------:|:------------:|:------------------------------------------------------:|:-------------------------------------:|
|          개선 전          |     338      | <img width=350 src="readmeSource/step1/개선전_vuser.png"> | vuser 338명 이후로 급격하게 요청시간이 늘어남을 알수 있다  |
|      개선 후(step1)       |     360      | <img width=350 src="readmeSource/step1/개선후_vuser.png"> | vuser 360명 이후로 급격하게 요청시간이 늘어남을 알수 있다  |
| 개선 후(step2 - scaleOut) |     1340     | <img width=350 src="readmeSource/step2/개선후_vuser.png"> | vuser 1340명 이후로 급격하게 요청시간이 늘어남을 알수 있다 |



#### 성능비교 표(K6)
| testType |                      개선전                      |                      개선후                      | 
|:--------:|:---------------------------------------------:|:---------------------------------------------:|
|  smoke   | <img src="readmeSource/step1/개선전_smoke.png">  | <img src="readmeSource/step2/개선후_smoke.png">  |   
|   load   |  <img src="readmeSource/step1/개선전_load.png">  |  <img src="readmeSource/step2/개선후_load.png">  |   
|  stress  | <img src="readmeSource/step2/개선전_stress.png"> | <img src="readmeSource/step2/개선후_stress.png"> | 

---

### 1단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 2단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요









### study
http2 protocol
1. 헤더를 압축해서 보낸다
2. Connection 1개로 여러개의 요청을 처리할수 있다(비동기적)
3. 리소스간 의존관계에 따른 우선 순위를 설정하여, 리소스 로드문제를 해결할수 있다




