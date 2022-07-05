package nextstep.subway;


import nextstep.subway.util.support.version.BlogVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static nextstep.subway.util.WebMvcConfig.PREFIX_STATIC_RESOURCES;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private BlogVersion version;

    @Test
    @DisplayName("main.js 캐시 태스트")
    void cacheMainJs() {
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

      assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("main_log 캐시 테스트")
    void get_static_resources_images() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/main_logo.png";
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

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("root 캐시 태스트")
    void cacheRoot() {
        EntityExchangeResult<String> response = client
                .get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.empty())
                .expectBody(String.class)
                .returnResult();

        String etag = response
                .getResponseHeaders()
                .getETag();

        assertThat(etag).isNull();
    }
}
