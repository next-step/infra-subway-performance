package nextstep.subway.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.config.WebMvcConfig.CACHE_MAX_AGE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebMvcConfigTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private ResourceVersion version;

    @Test
    void 정적_리소스를_요청한다() {
        final String uri = "/resources/" + version.getVersion() + "/js/main.js";
        final EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.maxAge(CACHE_MAX_AGE, TimeUnit.SECONDS))
                .expectBody(String.class)
                .returnResult();

        final String eTag = response.getResponseHeaders().getETag();
        assertThat(eTag).isNotNull();

        client.get().uri(uri)
                .header(HttpHeaders.IF_NONE_MATCH, eTag)
                .exchange()
                .expectStatus().isNotModified()
                .expectHeader().exists("eTag");
    }
}
