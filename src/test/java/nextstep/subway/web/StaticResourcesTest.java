package nextstep.subway.web;

import nextstep.subway.support.version.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@DisplayName("정적 자원 HTTP 캐싱 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {

    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @Test
    void js_파일은_no_cache_private를_적용한다() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        EntityExchangeResult<String> response = client.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class).returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders().getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }

    @Test
    void css_파일은_max_age_1년을_적용한다() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/test.css";
        EntityExchangeResult<String> response = client.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
                .expectBody(String.class).returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders().getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }

    @Test
    void css나_js가_아닌_정적_리소스는_no_cache_private를_적용한다() {
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/images/main_logo.png";
        EntityExchangeResult<String> response = client.get().uri(uri)
                .exchange().expectStatus().isOk()
                .expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
                .expectBody(String.class).returnResult();

        logger.debug("body : {}", response.getResponseBody());

        String etag = response.getResponseHeaders().getETag();

        client.get().uri(uri)
                .header("If-None-Match", etag)
                .exchange().expectStatus().isNotModified();
    }
}
