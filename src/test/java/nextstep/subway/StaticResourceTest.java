package nextstep.subway;

import static nextstep.subway.config.WebMvcConfig.PREFIX_STATIC_RESOURCES;

import java.util.concurrent.TimeUnit;
import nextstep.subway.support.version.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class StaticResourceTest {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourceTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @DisplayName("스크립트 리소스를 확인한다.")
    @Test
    void staticScriptResources() {
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

        client
            .get()
            .uri(uri)
            .header("If-None-Match", response.getResponseHeaders().getETag())
            .exchange()
            .expectStatus()
            .isNotModified();
    }

    @DisplayName("이미지 리소스를 확인한다.")
    @Test
    void staticImageResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/images/main_logo.png";
        EntityExchangeResult<String> response = client
            .get()
            .uri(uri)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .cacheControl(CacheControl.noStore().mustRevalidate())
            .expectBody(String.class)
            .returnResult();

        client
            .get()
            .uri(uri)
            .header("If-None-Match", response.getResponseHeaders().getETag())
            .exchange()
            .expectStatus()
            .isOk();
    }

    @DisplayName("스타일 리소스를 확인한다.")
    @Test
    void staticCssResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/css/main.css";
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

        client
            .get()
            .uri(uri)
            .header("If-None-Match", response.getResponseHeaders().getETag())
            .exchange()
            .expectStatus()
            .isNotModified();
    }
}
