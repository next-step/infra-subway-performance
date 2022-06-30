package nextstep.subway;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourcesTest {
    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("정적 자원 no Cache, private 검증")
    void verifyStaticNoCachingAndPrivate() {
        String uri = "/js/main.js";
        client.get().uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class);
    }

    @Test
    @DisplayName("CSS 요소에 대한 cache max-age 1년 검증")
    void verifyCssCachingForOneYear() {
        String uri = "/css/color.css";
        client.get().uri(uri)
                .exchange()
                .expectStatus()
                    .isOk()
                .expectHeader()
                    .cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class);
    }
}
