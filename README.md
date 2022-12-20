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

<details>
<summary>smoke 스크립트 실행 이전 결과</summary>

```text

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: smoke.js
     output: InfluxDBv1 (http://localhost:8086)

  scenarios: (100.00%) 1 scenario, 1 max VUs, 1m30s max duration (incl. graceful stop):
           * default: 1 looping VUs for 1m0s (gracefulStop: 30s)


running (1m00.1s), 0/1 VUs, 502 complete and 0 interrupted iterations
default ✓ [======================================] 1 VUs  1m0s

     ✓ [Result] Main Page
     ✓ [Result] Login Page
     ✓ [Result] Login
     ✓ [Result] me
     ✓ [Result] Path Page
     ✓ [Result] Search Path

     checks.........................: 100.00% ✓ 3012     ✗ 0
     data_received..................: 19 MB  316 kB/s
     data_sent......................: 4.7 MB 78 kB/s
     http_req_blocked...............: avg=6.89µs  min=1.5µs    med=2.61µs  max=29.22ms  p(90)=3.57µs  p(95)=4.18µs
     http_req_connecting............: avg=231ns   min=0s       med=0s      max=330.12µs p(90)=0s      p(95)=0s
    ✓ http_req_duration..............: avg=2.42ms  min=529µs    med=1.88ms  max=55.54ms  p(90)=4.5ms   p(95)=6.23ms
        { expected_response:true }...: avg=2ms     min=529µs    med=1.1ms   max=47.52ms  p(90)=3.45ms  p(95)=5.25ms
     http_req_failed................: 16.66% ✓ 3900       ✗ 19500
     http_req_receiving.............: avg=55.7µs  min=25.28µs  med=47.37µs max=29.33ms  p(90)=69.81µs p(95)=77.51µs
     http_req_sending...............: avg=15.11µs min=6.39µs   med=12.42µs max=8.27ms   p(90)=18.61µs p(95)=23.01µs
     http_req_tls_handshaking.......: avg=3µs     min=0s       med=0s      max=12.62ms  p(90)=0s      p(95)=0s
     http_req_waiting...............: avg=2.35ms  min=486.83µs med=1.82ms  max=55.45ms  p(90)=4.41ms  p(95)=6.13ms
     http_reqs......................: 23400  389.846775/s
     iteration_duration.............: avg=15.37ms min=8.26ms   med=12.43ms max=109.12ms p(90)=23.9ms  p(95)=30.79ms
     iterations.....................: 3900   64.974463/s
     vus............................: 1      min=1        max=1
     vus_max........................: 1      min=1        max=1
```

</details>



<details>
<summary>stress 스크립트 실행 이전 결과</summary>

```text

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: InfluxDBv1 (http://localhost:8086)

  scenarios: (100.00%) 1 scenario, 14 max VUs, 29m40s max duration (incl. graceful stop):
           * default: Up to 14 looping VUs for 29m10s over 12 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (29m10.2s), 00/14 VUs, 37082 complete and 0 interrupted iterations
default ✓ [======================================] 00/14 VUs  29m10s

     ✓ [Result] Main Page
     ✓ [Result] Login Page
     ✓ [Result] Login
     ✓ [Result] me
     ✓ [Result] Path Page
     ✓ [Result] Search Path

     hecks.........................: 83.30% ✓ 368179     ✗ 73773
     data_received..................: 362 MB 214 kB/s
     data_sent......................: 89 MB  53 kB/s
     http_req_blocked...............: avg=9.54ms   min=0s       med=2.69µs   max=49.35s p(90)=4.34µs   p(95)=5.7µs
     http_req_connecting............: avg=1.71ms   min=0s       med=0s       max=1m2s   p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=411.95ms min=0s       med=32.1ms   max=4m58s  p(90)=262.85ms p(95)=420.4ms
       { expected_response:true }...: avg=146.29ms min=513.73µs med=29.07ms  max=4m58s  p(90)=248.94ms p(95)=396.03ms
     http_req_failed................: 16.71% ✓ 73906      ✗ 368190
     http_req_receiving.............: avg=43.9ms   min=0s       med=45.59µs  max=4m58s  p(90)=478.32µs p(95)=1.31ms
     http_req_sending...............: avg=2.06ms   min=0s       med=13.52µs  max=30.22s p(90)=27.14µs  p(95)=55.4µs
     http_req_tls_handshaking.......: avg=6.37ms   min=0s       med=0s       max=46.59s p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=365.98ms min=0s       med=31.55ms  max=4m58s  p(90)=258.94ms p(95)=413.19ms
     http_reqs......................: 442096 261.593439/s
     iteration_duration.............: avg=2.63s    min=7.22ms   med=224.14ms max=7m6s   p(90)=1.59s    p(95)=2.84s
     iterations.....................: 73674  43.593778/s
     vus............................: 1      min=1        max=331
     vus_max........................: 384    min=384      max=384
```

</details>



<details>
<summary>laod 스크립트 실행 이전 결과</summary>

```text

          /\      |‾‾| /‾‾/   /‾‾/
     /\  /  \     |  |/  /   /  /
    /  \/    \    |     (   /   ‾‾\
   /          \   |  |\  \ |  (‾)  |
  / __________ \  |__| \__\ \_____/ .io

  execution: local
     script: load.js
     output: InfluxDBv1 (http://localhost:8086)

  scenarios: (100.00%) 1 scenario, 14 max VUs, 29m40s max duration (incl. graceful stop):
           * default: Up to 14 looping VUs for 29m10s over 12 stages (gracefulRampDown: 30s, gracefulStop: 30s)


running (1m00.1s), 0/1 VUs, 502 complete and 0 interrupted iterations
default ✓ [======================================] 00/14 VUs  29m10s

     ✓ [Result] Main Page
     ✓ [Result] Login Page
     ✓ [Result] Login
     ✓ [Result] me
     ✓ [Result] Path Page
     ✓ [Result] Search Path

     checks.........................: 66.66%  ✓ 789980     ✗ 394990
     data_received..................: 965 MB  551 kB/s
     data_sent......................: 183 MB  104 kB/s
     http_req_blocked...............: avg=22.27µs  min=856ns    med=2.57µs  max=139.47ms p(90)=3.79µs   p(95)=4.66µs
     http_req_connecting............: avg=1.62µs   min=0s       med=0s      max=24.12ms  p(90)=0s       p(95)=0s
   ✓ http_req_duration..............: avg=11.99ms  min=482.8µs  med=7.38ms  max=204.49ms p(90)=28.3ms   p(95)=35.68ms
       { expected_response:true }...: avg=9.9ms    min=482.8µs  med=5.16ms  max=196.47ms p(90)=25.43ms  p(95)=32.32ms
     http_req_failed................: 50.00%  ✓ 592485     ✗ 592485
     http_req_receiving.............: avg=170.92µs min=13.13µs  med=41.63µs max=72.02ms  p(90)=233.25µs p(95)=534.94µs
     http_req_sending...............: avg=41.38µs  min=4.43µs   med=12.81µs max=62.47ms  p(90)=25.99µs  p(95)=42.32µs
     http_req_tls_handshaking.......: avg=13.71µs  min=0s       med=0s      max=132.29ms p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=11.78ms  min=445.86µs med=7.13ms  max=204.45ms p(90)=28.07ms  p(95)=35.45ms
     http_reqs......................: 1184970 677.118897/s
     iteration_duration.............: avg=73.88ms  min=6.15ms   med=53.64ms max=583.96ms p(90)=161.72ms p(95)=199.18ms
     iterations.....................: 197495  112.853149/s
     vus............................: 1       min=1        max=14
     vus_max........................: 14      min=14       max=14
```


3. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

---

### 2단계 - 스케일 아웃

1. Launch Template 링크를 공유해주세요.

2. cpu 부하 실행 후 EC2 추가생성 결과를 공유해주세요. (Cloudwatch 캡쳐)

```sh
$ stress -c 2
```

3. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

---

### 3단계 - 쿼리 최적화

1. 인덱스 설정을 추가하지 않고 아래 요구사항에 대해 1s 이하(M1의 경우 2s)로 반환하도록 쿼리를 작성하세요.

- 활동중인(Active) 부서의 현재 부서관리자 중 연봉 상위 5위안에 드는 사람들이 최근에 각 지역별로 언제 퇴실했는지 조회해보세요. (사원번호, 이름, 연봉, 직급명, 지역, 입출입구분, 입출입시간)

---

### 4단계 - 인덱스 설계

1. 인덱스 적용해보기 실습을 진행해본 과정을 공유해주세요

---

### 추가 미션

1. 페이징 쿼리를 적용한 API endpoint를 알려주세요

---

### 🚀 1단계 - 화면 응답 개선하기
<details>
<summary> </summary>

#### 요구사항
* [ ] 부하테스트 각 시나리오의 요청시간을 목푯값 이하로 개선
  * 개선 전 / 후를 직접 계측하여 확인

#### 힌트
1. Reverse Proxy 개선하기
![img.png](src/main/resources/image/img.png)

**gzip 압축**
```
http {
  gzip on; ## http 블록 수준에서 gzip 압축 활성화
  gzip_comp_level 9;
  gzip_vary on;
  gzip_types text/plain text/css application/json application/x-javascript application/javascript text/xml application/xml application/rss+xml text/javascript image/svg+xml application/vnd.ms-fontobject application/x-font-ttf font/opentype;
}
```

**cache**
```
http {
  ## Proxy 캐시 파일 경로, 메모리상 점유할 크기, 캐시 유지기간, 전체 캐시의 최대 크기 등 설정
  proxy_cache_path /tmp/nginx levels=1:2 keys_zone=mycache:10m inactive=10m max_size=200M;

  ## 캐시를 구분하기 위한 Key 규칙
  proxy_cache_key "$scheme$host$request_uri $cookie_user";
  
    server {
        location ~* \.(?:css|js|gif|png|jpg|jpeg)$ {
            proxy_pass http://app;
      
            ## 캐시 설정 적용 및 헤더에 추가
            # 캐시 존을 설정 (캐시 이름)
            proxy_cache mycache;
            # X-Proxy-Cache 헤더에 HIT, MISS, BYPASS와 같은 캐시 적중 상태정보가 설정
            add_header X-Proxy-Cache $upstream_cache_status;
            # 200 302 코드는 20분간 캐싱
            proxy_cache_valid 200 302 10m;    
            # 만료기간을 1 달로 설정
            expires 1M;
            # access log 를 찍지 않는다.
            access_log off;
        }
    }
}    
```

**TLS, HTTP/2 설정**
```
http {
  server {
    listen 80;
    return 301 https://$host$request_uri;
  }
  server {  
  listen 443 ssl http2;
    
    ssl_certificate /etc/letsencrypt/live/[도메인주소]/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/[도메인주소]/privkey.pem;

    # Disable SSL
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;

    # 통신과정에서 사용할 암호화 알고리즘
    ssl_prefer_server_ciphers on;
    ssl_ciphers ECDH+AESGCM:ECDH+AES256:ECDH+AES128:DH+3DES:!ADH:!AECDH:!MD5;

    # Enable HSTS
    # client의 browser에게 http로 어떠한 것도 load 하지 말라고 규제합니다.
    # 이를 통해 http에서 https로 redirect 되는 request를 minimize 할 수 있습니다.
    add_header Strict-Transport-Security "max-age=31536000" always;
    
    # SSL sessions
    ssl_session_cache shared:SSL:10m;
    ssl_session_timeout 10m; 
  }
}
```
📌 HTTP2.0은 SSL 계층 위에서만 동작합니다.


2. WAS 성능 개선하기
* application server의 경우
  * 작업 스레드풀을 필요 이상으로 크게 설정하면 DB 부하가 증가할 수 있습니다.
  * 애플리케이션 서버가 낼 수 있는 최대 성능을 넘어서는 동시처리 요청이 들어오면 TPS가 증가하지 않은 채 응답시간만 증가하다가 큐가 쌓여 서비스 멈춤현상이 발생할 수 있습니다.
  
성능 튜닝의 한 축은 서비스 간이나 서비스 내에서 반복되는 로직을 제거하는 것입니다. 기존에 작업한 결과를 저장해두었다가 이후에 다시 동일한 작업이 수행되었을 때 결과를 재사용하면 반복되는 로직을 제거할 수 있습니다.   
애플리케이션 캐시를 활용하여 기존에 작업한 결과를 저장해두었다가 이후에 다시 동일한 작업이 수행되었을 때 결과를 재사용하면 반복되는 로직을 제거할 수 있습니다. 또는 병렬 처리 등을 활용하여 `제한된 스레드 수 내에서 자원을 재사용하여 성능을 개선`할 수 있습니다.

A. Spring Data Cache
* Redis Server
```
$ docker pull redis
$ docker run -d -p 6379:6379 redis
```

* application.properties
```
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
```

* build.gradle
```
implementation('org.springframework.boot:spring-boot-starter-data-redis')
```

```
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Autowired
    RedisConnectionFactory connectionFactory;


    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder.
            fromConnectionFactory(connectionFactory).cacheDefaults(redisCacheConfiguration).build();
        return redisCacheManager;
    }
}
```

```
# 메서드 실행 전에 캐시를 확인하여 최소 하나의 캐시가 존재한다면 값을 반환한다.
# SpEL 표현식을 활용하여 조건부 캐싱이 가능하다. 
@Cacheable(value = "line", key = "#id")
public Line getLine(Long id) {

# 메서드 실행에 영향을 주지 않고 캐시를 갱신해야 하는 경우 사용한다.
@CachePut(value = "line", key = "#id")
public void updateLine(Long id, LineRequest lineUpdateRequest) {

# 캐시를 제거할 때 사용한다.
@CacheEvict(value = "line", key = "#id")
public void deleteLineById(Long id) {
```
* ResponseEntity는 Deserialize 되지 않으니 도메인 객체를 직접 반환하거나, Service Layer에 적용하여야 합니다.
* LocalDateTime은 Deserialize 되지 않으니 String으로 변환하여야 합니다.
* Spring AOP의 제약사항을 가집니다.
  * pulbic method에만 사용가능 합니다.
  * 같은 객체내의 method끼리 호출시 AOP가 동작하지 않습니다.
  * Runtime Weaving으로 처리 되기 때문에 약간의 성능저하가 있습니다.

**B. 비동기 처리**
외부 API를 활용할 경우 비동기처리를 하여 병목을 피할 수 있습니다. 또한, Thread pool을 활용하여 Thread를 재사용할 수 있습니다.

**\* blocking vs non-blocking / synchronous vs asynchronous** 
* Synchronous I/O와 Asynchronous I/O
  * 동기 : 작업을 요청한 후 작업의 결과가 나올 때까지 기다린 후 처리 (프로세스는 커널에 지속적으로 I/O 준비사항을 체크)
  * 비동기 : 직전 시스템 호출의 종료가 발생하면 그에 따른 처리를 진행

* Blocking I/O과 Non-Blocking I/O
  * Blocking : 유저 프로세스가 시스템 호출을 하고나서 결과가 반환되기까지 다음 처리로 넘어가지 않음
  * Non-Blocking : 호출한 직후에 프로그램으로 제어가 돌아와서 시스템 호출의 종료를 기다리지 않고 다음 처리로 넘어갈 수 있음

a. @Async
Spring data cache와 마찬가지로 Spring AOP의 제약사항을 가집니다.
```
@Async
public void sendMail(String to, String subject, String contents) {
```

b. 적절한 Thread pool size 구하기
 - https://brainbackdoor.tistory.com/27
```
## CPU 모델명
$ cat /proc/cpuinfo | grep "model name" | uniq -c | awk '{print $5 $6, $7,$8, $9, $10 $11}'

## CPU당 물리 코어 수
$ cat /proc/cpuinfo | grep "cpu cores" | tail -1 | awk '{print $4}'

## 물리 CPU 수 
$ cat /proc/cpuinfo | grep "physical id" | sort -u | wc -l

## 리눅스 전체 코어(프로세스)개수 
$ grep -c processor /proc/cpuinfo
```
* 적절한 스레드 수 = 사용 가능한 코어 수 * (1+대기 시간/서비스 시간)
  * 즉, 적절한 스레드 수는 사용 가능한 코어 수의 1 ~ 2 배 내로 수렴합니다.

대기 시간은 I/O waiting, 원격 서비스에 대한 HTTP response wating 등 작업 하나가 완료되기까지 소모되는 시간을 의미합니다. CPU를 많이 쓰는 계산 작업의 경우 대기를 거의 하지 않으므로 (대기 시간/서비스 시간)의 값이 0에 수렴합니다. 이 경우, 스레드의 수는 사용 가능한 코어의 수와 동일합니다.

c. Thread pool 설정   
@Async에 대한 별도 설정이 없더라도 TaskExecutionAutoConfiguration에 의해 Thread pool이 생성됩니다. 다만, 이 때 설정은 TaskExecutionProperties.Pool에 정의된 설정을 기본으로 따릅니다. 따라서 애플리케이션이 구동되는 상황에 따라 적절히 변경해줍니다.
```
    public static class Pool {
        private int queueCapacity = 2147483647;
        private int coreSize = 8;
        private int maxSize = 2147483647;
        private boolean allowCoreThreadTimeout = true;
        private Duration keepAlive = Duration.ofSeconds(60L);        
```

```
@Configuration 
@EnableAsync 
public class AsyncThreadConfig { 

    @Bean 
    public Executor asyncThreadTaskExecutor() { 
        ThreadPoolTaskExecutor exexcutor = new ThreadPoolTaskExecutor();     
        /* 기본 Thread 사이즈 */
        exexcutor.setCorePoolSize(2); 
        /* 최대 Thread 사이즈 */        
        exexcutor.setMaxPoolSize(4); 
        /* MaxThread가 동작하는 경우 대기하는 Queue 사이즈 */                
        exexcutor.setQueueCapacity(100)
        exexcutor.setThreadNamePrefix("subway-async-"); 
        return exexcutor; 
    } 
}
```

3. Scale out - 초간단 Blue-Green 배포 구성하기
* nomad 를 활용하여 배포 구성을 합니다.
* nomad 도구 사용이 미션의 목표가 아니니, 배포 전략 학습 외의 용도로는 굳이 사용하지 않으셔도 좋습니다.
```
# nomad 설치 및 실행
$ curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -
$ sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
$ sudo apt-get update && sudo apt-get install nomad
$ sudo nomad agent -dev -bind=0.0.0.0 > /dev/null 2>&1 &

# nomad 명령어
$ nomad run deploy.nomad
$ watch nomad status app
```
1. 배포하려는 도커 이미지를 빌드합니다.
2. 설정파일의 image 값을 변경한 후, 배포해봅니다.
3. 변경된 버전의 도커 이미지를 빌드하고 설정 파일을 수정해봅니다.
4. [서버 공인 IP]:4646 페이지를 통해 배포과정을 살펴봅니다.

**\* Blue-Green 배포**
* 배포하려는 docker image 이름을 설정하여 작성합니다.
```
$ vi deploy.nomad

job "app" {
  datacenters = ["dc1"]
  type = "service"

  group "app" {
    count = 3

    update {
      max_parallel     = 1
      canary = 3
      min_healthy_time = "10s"
      healthy_deadline = "1m"
      auto_revert      = true
      auto_promote     = true
    }

    task "app" {
      driver = "docker"

      config {
        image = "docker image 이름"
        port_map {
          http = 8080
        }
      }

      resources {
        cpu    = 500 # 500 MHz
        memory = 250 # 256MB

        network {
          mbits = 10
          port "http" {}
        }
      }

      service {
        name = "app"
        tags = ["app"]
        port = "http"

        check {
          name     = "alive"
          type     = "tcp"
          interval = "10s"
          timeout  = "2s"
        }
      }
    }
  }
}
```

**Nginx dynamic port**   
배포시 Port를 동적으로 할당하도록 구성한다면 앞단에 있는 Reverse Proxy 혹은 Load Balancer의 설정도 변경해야 합니다. 이를 위해 Service Discovery인 consul를 활용하여 nginx를 재배포합니다.
```
# consul 설치 및 실행
$ curl -fsSL https://apt.releases.hashicorp.com/gpg | sudo apt-key add -
$ sudo apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
$ sudo apt-get update && sudo apt-get install consul
$ consul agent -dev > /dev/null 2>&1 &

$ nomad run nginx.nomad
$ nomad status nginx
...
Allocations
ID        Node ID   Task Group  Version  Desired  Status   Created    Modified
1c1b2f65  5ed4b90d  nginx       16       run      running  3m30s ago  3m17s ago

$ sudo docker ps -a

$ nomad alloc fs 1c1b2f65 nginx/local/load-balancer.conf
upstream backend {
  server 127.0.0.1:21538;
  server 127.0.0.1:31189;
...  
  
$ curl -I localhost
HTTP/1.1 200
Server: nginx/1.19.6
...
```

```
$ vi nginx.nomad

job "nginx" {
  datacenters = ["dc1"]

  group "nginx" {
    count = 1

    task "nginx" {
      driver = "docker"

      config {
        image = "nginx"

	network_mode = "host"

        volumes = [
          "local:/etc/nginx/conf.d",
        ]
      }
      template {
        data = <<EOF
upstream backend {
{{ range service "app" }}
  server {{ .Address }}:{{ .Port }};
{{ else }}server 172.17.0.1:65535; # force a 502
{{ end }}
}

server {
   listen 80;

   location / {
      proxy_pass http://backend;
      proxy_http_version 1.1;
      proxy_set_header Upgrade $http_upgrade;
      proxy_set_header Connection 'upgrade';
      proxy_set_header Host $host;
   }
}
EOF

        destination   = "local/load-balancer.conf"
        change_mode   = "signal"
        change_signal = "SIGHUP"
      }

      resources {
        network {
          mbits = 10

          port "http" {
            static = 80
 	    host_network = "public"
          }
        }
      }

      service {
        name = "nginx"
        port = "http"
      }
    }
  }
}
```

4. 정적 파일 경량화
![img.png](src/main/resources/image/img2.png)

* 미션의 경우 정적 리소스가 많지 않고, 이 과정은 프론트엔드 과정이 아니니, 컨셉만 이해하고 넘어가도록 해요 🙏🏻
* 크롬 브라우저 도구의 Network 탭을 활용하여 실제로 업로드, 다운로드되고 있는 리소스와 각 리소스의 속성(HTTP 헤더, 콘텐츠, 크기 등)을 확인할 수 있으며, 위의 그림과 같이 네트워크 대역폭 제한, 브라우저 캐시 비활성화 등의 설정을 할 수 있습니다. Performance 탭을 활용하면 페이지로드 혹은 사용자 상호작용 후 발생하는 이벤트를 모두 분석할 수 있습니다.

* 번들 크기 줄이기 : 모듈을 필요한 부분만 import하거나 불필요한 라이브러리 제거를 제거 혹은 용량이 작은 라이브러리로 교체하여 성능을 개선합니다.
```
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

module.exports = {
  plugins : [
    new BundleAnalyzerPlugin()
  ]
}
```

* Code Splitting :
```
const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin')
const TerserPlugin = require('terser-webpack-plugin')

module.exports = {
  optimization: {
    splitChunks: {
      chunks: 'all',
      cacheGroups: {
        vendors: {
          test: /[\\/]node_modules[\\/]/,
          name: 'js/vendors'
        }
      }
    },
    minimizer: [
      new TerserPlugin({
        cache: true,
        parallel: true,
        terserOptions: {
          warnings: false,
          compress: {
            warnings: false
          },
          ecma: 6,
          mangle: true
        },
        sourceMap: true
      }),
      new OptimizeCssAssetsPlugin()
    ]
  }
}
```

* Dynamic import
```
const mainRoutes = [
  {
    path: '/',
    component: () => import(/* webpackChunkName: "main" */ '@/views/main/MainPage')
  }
]
```

* 웹 폰트 최적화
```
@font-face {
  font-family: 'hanna';
  src: url(/fonts/BMHANNAPro.otf) format('woff2');
}
```
</details>
