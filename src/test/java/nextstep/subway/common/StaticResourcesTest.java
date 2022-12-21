package nextstep.subway.common;

import static nextstep.subway.common.WebMvcConfig.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import nextstep.subway.support.version.SubwayVersion;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourcesTest {
    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @DisplayName("모든 정적 자원에 대해 no-store, private 설정을 하고 테스트 코드를 통해 검증합니다.")
    @Test
    void static_no_cache_private() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/test.js";
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

        String etag = response.getResponseHeaders()
            .getETag();

        client.get()
            .uri(uri)
            .header("If-None-Match", etag)
            .exchange()
            .expectStatus()
            .isNotModified();
    }

    @DisplayName("확장자는 css인 경우는 max-age를 1년 설정을 합니다.")
    @Test
    void css_no_cache_private() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/test.css";
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

        String etag = response.getResponseHeaders()
            .getETag();

        client.get()
            .uri(uri)
            .header("If-None-Match", etag)
            .exchange()
            .expectStatus()
            .isNotModified();
    }

    @DisplayName("js 파일은 no-cache, private 설정을 합니다.")
    @Test
    void js_no_cache_private() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/test.js";
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
