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

import static nextstep.subway.configuration.WebMvcConfig.PREFIX_STATIC_RESOURCES;

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
    void get_static_resources() {
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
