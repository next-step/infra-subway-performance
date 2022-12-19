package nextstep.subway.statics;

import java.util.concurrent.TimeUnit;
import nextstep.subway.common.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {
    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @DisplayName("css는 1년의 캐시 유지 기간을 갖는다.")
    @Test
    void css() {
        String uri = createUri("/css/subway.css");

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

        String eTag = response.getResponseHeaders().getETag();

        client.get()
                .uri(uri)
                .header(HttpHeaders.IF_NONE_MATCH, eTag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("js는 no-cache, private Cache-Control을 갖는다.")
    @Test
    void js() {
        client.get()
                .uri(createUri("/js/main.js"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate());
    }

    @DisplayName("그 외 리소스는 no-cache, private Cache-Control을 갖는다.")
    @Test
    void other() {
        client.get()
                .uri(createUri("/images/logo_small.png"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate());
    }

    private String createUri(String path) {
        return String.format("/resources/%s%s", version.getVersion(), path);
    }
}
