package nextstep.subway;

import static nextstep.subway.common.WebMvcConfig.PREFIX_STATIC_RESOURCES;

import java.util.concurrent.TimeUnit;
import nextstep.subway.common.SubwayVersion;
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

@DisplayName("정적 리소스 테스트")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @DisplayName("js 리소스 테스트")
    @Test
    void get_js_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = client.get()
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

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("css 리소스 테스트")
    @Test
    void get_css_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/main.css";
        EntityExchangeResult<String> response = client.get()
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

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("image 리소스 테스트")
    @Test
    void get_image_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/main_logo.png";
        EntityExchangeResult<String> response = client.get()
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

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }
}
