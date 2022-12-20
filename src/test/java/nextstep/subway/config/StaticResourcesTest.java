package nextstep.subway.config;

import nextstep.subway.support.version.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.config.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourcesTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @Test
    @DisplayName("JS 파일에 대해 no-cache, private 캐싱 설정")
    void jsFilesNoCachePrivateTest() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/js/main.js";
        EntityExchangeResult<String> response = client.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders().getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }

    @Test
    @DisplayName("CSS 파일에 대해 max-age 1년동안 캐싱 설정")
    void cssFilesNoCachePrivateTest() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/css/index.css";
        EntityExchangeResult<String> response = client.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders().getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }
}
