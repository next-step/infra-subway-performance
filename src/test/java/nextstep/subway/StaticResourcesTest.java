package nextstep.subway;

import nextstep.subway.support.version.SubwayVersion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @Test
    void get_static_css_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/main.css";
        EntityExchangeResult<String> response = client
                .get().uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client
                .get().uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus().isNotModified();
    }

    @Test
    void get_static_js_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = client
                .get().uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client
                .get().uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus().isNotModified();
    }

    @Test
    void get_static_the_others_resources() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/main_logo.png";

        EntityExchangeResult<String> response = client
                .get().uri(uri)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client
                .get().uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus().isNotModified();
    }
}
