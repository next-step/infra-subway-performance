### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

### Smoke

#### Before

- ![img before-smoke-grafana](./smoke/before/grafana-smoke.png)
- ![img before-smoke-k6](./smoke/before/k6-smoke.JPG)

#### After

- ![img after-smoke-grafana](./smoke/after/grafana-smoke.png)
- ![img after-smoke-k6](./smoke/after/k6-smoke.JPG)

### Load

#### Before

- ![img before-load-grafana](./load/before/grafana-load.png)
- ![img before-load-k6](./load/before/k6-load.JPG)

#### After

- ![img after-load-grafana](./load/after/grafana-load.JPG)
- ![img after-load-k6](./load/after/k6-load.JPG)

### Stress

#### Before

- ![img before-stress-grafana](./stress/before/grafana-stress.png)
- ![img before-stress-k6](./stress/before/k6-stress.JPG)

#### After

- ![img after-stress-grafana](./stress/after/grafana-stress.png)
- ![img after-stress-k6](./stress/after/k6-stress.JPG)

### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- nginx cache 적용
- nginx compression(gzip) 적용
- nginx http2 적용
- was cache(redis) 적용
