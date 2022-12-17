package nextstep.subway.web;

import nextstep.subway.support.version.SubwayVersion;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.config.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class StaticResourceTest {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourceTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @Test
    void getScript() {
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

    @Test
    void 이미지_리소스_확인() {
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

    @Test
    void 스타일_리소스_확인() {
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
