### 1. 성능 개선 결과를 공유해주세요 (Smoke, Load, Stress 테스트 결과)

- 기준치는 이전 미션에서 진행했던 수치를 기반으로 진행
- https://github.com/next-step/infra-subway-monitoring/blob/heowc/docs/step2/index.md

### Smoke

#### Before

- ![img before-smoke-grafana](./smoke/before/before-smoke-grafana.png)
- ![img before-smoke-k6](./smoke/before/before-smoke-k6.png)

#### After

- ![img after-smoke-grafana](./smoke/after/after-smoke-grafana.png)
- ![img after-smoke-k6](./smoke/after/after-smoke-k6.png)

### Load

#### Before

- ![img before-load-grafana](./load/before/before-load-grafana.png)
- ![img before-load-k6](./load/before/before-load-k6.png)

#### After

- ![img after-load-grafana](./load/after/after-load-grafana.png)
- ![img after-load-k6](./load/after/after-load-k6.png)

### Stress

#### Before

- ![img before-stress-grafana](./stress/before/before-stress-grafana.png)
- ![img before-stress-k6](./stress/before/before-stress-k6.png)

#### After

- ![img after-stress-grafana](./stress/after/after-stress-grafana.png)
- ![img after-stress-k6](./stress/after/after-stress-k6.png)

### 2. 어떤 부분을 개선해보셨나요? 과정을 설명해주세요

- nginx cache 적용
- nginx compression(gzip) 적용
- nginx http2 적용
- was cache(redis) 적용