package nextstep.subway.common;

import static nextstep.subway.common.WebMvcConfig.PREFIX_STATIC_RESOURCES;

import java.util.concurrent.TimeUnit;
import nextstep.subway.support.version.SubwayVersion;
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

@DisplayName("정적 리소스 캐싱 테스트")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SubwayVersion subwayVersion;

    @DisplayName("/resources/js/*.js 자원의 cache-control은 no-cache, private이다.")
    @Test
    void getStaticJsResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders()
                .getETag();

        webTestClient.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("/resources/css/*.css 자원의 캐싱 기간은 1년이다.")
    @Test
    void getStaticCssResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/css/main.css";
        EntityExchangeResult<String> response = webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class)
                .returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders()
                .getETag();

        webTestClient.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("/resources 내 /resources/js/*.js, /resources/css/*.css에 속하지 않는 자원들은 cache-control은 no-cache, private이다.")
    @Test
    void getStatisImageResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/images/logo_small.png";
        EntityExchangeResult<String> response = webTestClient.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders()
                .getETag();

        webTestClient.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }
}
