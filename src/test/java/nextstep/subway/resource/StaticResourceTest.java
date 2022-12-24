package nextstep.subway.resource;

import static nextstep.subway.common.WebMvcConfig.PREFIX_STATIC_RESOURCES;

import java.util.concurrent.TimeUnit;
import nextstep.subway.support.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourceTest {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourceTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @Test
    @DisplayName("정적 자원에 대해 no-cache, private 설정검증")
    void get_static_resources() {
        String uri =
            PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/logo_small.png";
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

        logger.debug("body : {}", response.getResponseBody());

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

    @Test
    @DisplayName("JS 자원에 대해 no-cache, private 설정검증")
    void jsNoCachePrivate() {
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

        logger.debug("body : {}", response.getResponseBody());

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

    @Test
    @DisplayName("css 자원에 대해 1년 캐시 설정검증")
    void cssCachedAYear() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/test.css";
        EntityExchangeResult<String> response = client
            .get()
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

        client
            .get()
            .uri(uri)
            .header("If-None-Match", etag)
            .exchange()
            .expectStatus()
            .isNotModified();
    }
}
