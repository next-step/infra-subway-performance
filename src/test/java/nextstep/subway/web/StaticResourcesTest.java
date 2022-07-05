package nextstep.subway.web;

import nextstep.support.subwayVersion.SubwayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Cache.Cachecontrol;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.config.WebMvcConfig.PREFIX_STATIC_RESOURCES;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {
    @Autowired
    private WebTestClient client;
    @Autowired
    private SubwayVersion version;

    @Test
    @DisplayName("정적 리소스 중 확장자가 js 인 경우 no-cache, private 이 설정된다")
    void getStaticResourcesJs() throws Exception {
        // given
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        Cachecontrol cachecontrol = new Cachecontrol();
        cachecontrol.setNoCache(true);
        cachecontrol.setCachePrivate(true);

        EntityExchangeResult<String> response = client
                .get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(cachecontrol.toHttpCacheControl())
                .expectBody(String.class)
                .returnResult();

        String etag = response.getResponseHeaders()
                .getETag();

        client
                .get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
        // when

        // then
    }

    @Test
    @DisplayName("정적 리소스 중 확장자가 css 인 경우 max-age 가 1년이 설정된다")
    void get_static_resources_css() {
        // given
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/mypage.css";

        // when & then
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

        client
                .get()
                .uri(uri)
                .header("If-None-Match", etag)
                .exchange()
                .expectStatus()
                .isNotModified();
    }

    @Test
    @DisplayName("정적 리소스 중 확장자가 css, js 가 아닌 경우 no-cache, no-store 가 설정된다")
    void get_static_resources_default() {
        // given
        String uri = PREFIX_STATIC_RESOURCES + "/images/main_logo.png";
        Cachecontrol cachecontrol = new Cachecontrol();
        cachecontrol.setNoCache(true);
        cachecontrol.setNoStore(true);

        // when & then
        EntityExchangeResult<String> response = client
                .get()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .cacheControl(cachecontrol.toHttpCacheControl())
                .expectBody(String.class)
                .returnResult();

        assertThat(response.getResponseHeaders().getETag()).isNullOrEmpty();
    }
}
