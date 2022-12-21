package nextstep.subway;

import nextstep.subway.configuration.ResourceVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.configuration.WebMvcConfig.MAX_AGE_CACHE_PERIOD;
import static nextstep.subway.configuration.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@DisplayName("정적 리소스 캐싱 테스트")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ResourceVersion version;

    @DisplayName("모든 정적 자원에 대해 no-cache, private 설정 테스트")
    @Test
    void get_static_js_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = webTestClient
                .get().uri(uri).exchange()
                .expectStatus().isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders()
                .getETag();

        assertIsNotModified(uri, etag);
    }

    @DisplayName("확장자가 .css 인 경우, max-age 를 1년으로 캐시 설정을 한다.")
    @Test
    void get_static_css_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/logo.css";
        EntityExchangeResult<String> response = webTestClient
                .get().uri(uri).exchange()
                .expectStatus().isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(MAX_AGE_CACHE_PERIOD, TimeUnit.SECONDS))
                .expectBody(String.class)
                .returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders()
                .getETag();

        assertIsNotModified(uri, etag);
    }

    @DisplayName("모든 정적 자원에 대해, no-cache, no-store 적용")
    @Test
    void get_static_all_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/logo_small.png";
        EntityExchangeResult<String> response = webTestClient
                .get().uri(uri).exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders()
                .getETag();

        assertIsNotModified(uri, etag);
    }

    private void assertIsNotModified(String uri, String etag) {
        webTestClient
                .get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }
}
