package nextstep.subway.web;

import nextstep.subway.support.version.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static nextstep.subway.config.WebMvcConfig.PREFIX_STATIC_RESOURCES;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @DisplayName("css 캐시는 1년간 유지됨")
    @Test
    void testCssCacheControl() {

        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/app.css";

        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.maxAge(Duration.ofDays(365).getSeconds(), TimeUnit.SECONDS))
                .expectBody(String.class)
                .returnResult();

        client.get()
                .uri(uri)
                .header(HttpHeaders.IF_NONE_MATCH, response.getResponseHeaders()
                        .getETag())
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @DisplayName("js 파일은 no-cache 와 private 이 적용됨")
    @Test
    void testJavascriptCacheControl() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = client.get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.noCache()
                        .cachePrivate())
                .expectBody(String.class)
                .returnResult();

        client.get().uri(uri)
                .header(HttpHeaders.IF_NONE_MATCH, response.getResponseHeaders().getETag())
                .exchange().expectStatus().isNotModified();
    }

    @DisplayName("css, js 파일 이외의 정적 리소스는 no-cache 와 private 이 적용됨")
    @Test
    void testExtraResourcesCacheControl() {

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

        client.get().uri(uri)
                .header(HttpHeaders.IF_NONE_MATCH, response.getResponseHeaders().getETag())
                .exchange().expectStatus().isNotModified();
    }

    @DisplayName("정적 파일이 아닌 경우 캐시 적용되지 않음")
    @Test
    void nonStaticRequestCacheControl() {
        EntityExchangeResult<String> response = client.get()
                .uri("/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(CacheControl.empty())
                .expectBody(String.class)
                .returnResult();

        assertThat(response.getResponseHeaders().getETag()).isNull();
    }
}
