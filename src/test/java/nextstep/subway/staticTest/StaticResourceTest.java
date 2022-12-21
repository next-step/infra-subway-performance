package nextstep.subway.staticTest;

import nextstep.subway.support.version.SubwayVersionSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.common.WebMvcConfig.PREFIX_STATIC_RESOURCES;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourceTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersionSupport version;

    @Test
    void getJsStaticResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/js/main.js";
        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @Test
    void getCssStaticResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/css/main.css";
        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client.get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @Test
    void getImageStaticResources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/images/main_logo.png";
        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

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
