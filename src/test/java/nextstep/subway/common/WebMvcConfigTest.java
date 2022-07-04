package nextstep.subway.common;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WebMvcConfigTest {
    @Autowired
    private WebTestClient client;

    @Test
    void css가_아닌_정적_자원에_대해_no_cache와_private_설정이_되어_있어야_한다() {
        final String uri = "/js/main.js";
        client
                .get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class);
    }

    @Test
    void 확장자가_css이면_캐시_max_age가_1년이어야_한다() {
        final String uri = "/css/app.css";
        client
                .get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class);
    }
}
