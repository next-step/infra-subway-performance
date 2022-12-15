package nextstep.subway.config;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static nextstep.subway.config.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebMvcConfigTest {
    @Autowired
    private WebTestClient client;

    @Autowired
    private Version version;

    private static final Duration YEAR = Duration.ofDays(365);

    @Test
    @DisplayName("css를 max-age로 1년으로 설정")
    void get_static_resources_css() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() +"/css/main.css";
        EntityExchangeResult<String> response = client
                .get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(YEAR))
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @Test
    @DisplayName("js를 no-cache, private으로 설정")
    void get_static_resources_js() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = client
                .get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client
                .get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }
}
