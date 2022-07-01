package nextstep.subway.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("정적 리소스 관련 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourceTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        restTemplate = new TestRestTemplate(new RestTemplateBuilder().rootUri("http://localhost:" + port));
    }

    @DisplayName("css를 제외한 모든 자원은 cache-control이 no-cache, private 이다.")
    @Test
    void cacheControl() {
        ResponseEntity<String> response = restTemplate.getForEntity("/js/main.js", String.class);
        assertThat(response.getHeaders().getCacheControl()).contains("no-cache", "private");
    }
}
