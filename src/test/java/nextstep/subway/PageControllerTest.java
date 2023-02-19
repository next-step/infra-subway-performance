package nextstep.subway;

import nextstep.subway.support.ResourceVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static nextstep.subway.config.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PageControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PageControllerTest.class);

    @Autowired
    private ResourceVersion version;

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("js, image no-cache 검증")
    @ValueSource(strings = {"/images/logo_small.png", "/js/main.js"})
    @ParameterizedTest
    void testNoCachePrivate(String path) {
        String uri = String.format("%s/%s/%s", PREFIX_STATIC_RESOURCES, version.getVersion(), path);
        EntityExchangeResult<String> response = webTestClient
                .get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class).returnResult();

        log.info("response body\n{}", response.getResponseBody());
    }

    @DisplayName("css cache 적용 검증")
    @Test
    void testCacheBustingOfStaticResources() {
        String uri = String.format("%s/%s/css/sample.css", PREFIX_STATIC_RESOURCES, version.getVersion());

        // 정적 파일에 ETag를 사용한 캐싱이 적용되었는지 확인한다.
        EntityExchangeResult<String> response = webTestClient
                .get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().exists(HttpHeaders.ETAG)
                .expectHeader().cacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic())
                .expectBody(String.class).returnResult();

        log.info("response body\n{}", response.getResponseBody());

        String etag = response.getResponseHeaders().getETag();

        // 캐싱되었다면 다시 호출했을때 HTTP status는 304를 반환한다.
        webTestClient.get()
                .uri(uri)
                .header(HttpHeaders.IF_NONE_MATCH, etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("gzip 설정 검증")
    @Test
    void testCompression() {
        EntityExchangeResult<String> response = webTestClient
                .get()
                .uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals(HttpHeaders.TRANSFER_ENCODING, "chunked")
                .expectBody(String.class).returnResult();

        log.info("response body\n{}", response.getResponseBody());
    }
}