package nextstep.subway.config;

import nextstep.subway.support.SubwayVersion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebMvcConfigTest {
    private static final String PREFIX_STATIC_RESOURCES = "/resources";

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @Test
    void getStaticCssResourcesTest() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/css/main.css";
        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus().isNotModified();
    }

    @Test
    void getStaticJsResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/js/main.js";
        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus().isNotModified();
    }

    @Test
    void getStaticResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/images/main_logo.png";

        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus().isNotModified();
    }
}