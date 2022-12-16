package nextstep.subway.web;

import static nextstep.subway.common.WebMvcConfig.PREFIX_STATIC_RESOURCES;

import java.util.concurrent.TimeUnit;
import nextstep.subway.common.support.version.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);
    @Autowired
    private WebTestClient client;
    @Autowired
    private SubwayVersion version;

    @DisplayName("css에 대한 ETag 테스트 (max-age : 1년)")
    @Test
    void cssETag() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/css/app.css";
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

        String etag = response.getResponseHeaders().getETag();

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("js에 대한 ETag 테스트 (no cache, private)")
    @Test
    void jsETag() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/js/main.js";
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

        String etag = response.getResponseHeaders().getETag();

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("css, js 제외한 정적 리소스에 대한 ETag 테스트 (no cache, private)")
    @Test
    void etcETag() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/images/main_logo.png";
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

        String etag = response.getResponseHeaders().getETag();

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }
}
