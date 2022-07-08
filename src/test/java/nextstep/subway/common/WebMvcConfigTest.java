package nextstep.subway.common;

import static nextstep.subway.common.WebMvcConfig.PREFIX_STATIC_RESOURCES;

import java.time.Duration;
import nextstep.subway.supprot.version.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebMvcConfigTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;


    private static final Duration YEAR = Duration.ofDays(365);

    @Test
    @DisplayName("CSS의  max-age를 1년으로 설정")
    void cssCacheControl() {
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

        client
                .get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @Test
    @DisplayName("js no-cache, private 설정")
    void jsNoCache() {
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

        String etag = response
                .getResponseHeaders()
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
