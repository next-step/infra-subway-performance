package nextstep.subway;

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

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SubwayVersion subwayVersion;

    @DisplayName("js 자원 cache-control : no-cache, private")
    @Test
    void getStaticJsResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = webTestClient.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class).returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders().getETag();

        webTestClient.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }

    @DisplayName("css 자원 캐싱 기간 : 1년")
    @Test
    void getStaticCssResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/css/test.css";
        EntityExchangeResult<String> response = webTestClient.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class).returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders().getETag();

        webTestClient.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }

    @DisplayName("js, css 외 자원들 cache-control : no-cache, private")
    @Test
    void getStatisImageResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/static/images/main_logo.png";
        EntityExchangeResult<String> response = webTestClient.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class).returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders().getETag();

        webTestClient.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }
}