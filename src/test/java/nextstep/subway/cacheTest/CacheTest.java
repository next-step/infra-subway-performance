package nextstep.subway.cacheTest;

import static nextstep.subway.common.WebMvcConfig.PREFIX_STATIC_RESOURCES;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import nextstep.subway.version.SubwayVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Cache.Cachecontrol;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheTest {

    @LocalServerPort
    int port;

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @BeforeEach
    public void init() {
        System.out.println(port);
    }

    @Test
    @DisplayName("정적자원중 js에 대한 요청은 no-cache와 private cache-control이 추가된다")
    void get_static_resources_js() {
        //given
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";

        Cachecontrol expectedCacheControl = new Cachecontrol();
        expectedCacheControl.setNoCache(true);
        expectedCacheControl.setCachePrivate(true);

        //when & then
        EntityExchangeResult<String> response = client
            .get()
            .uri(uri)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .cacheControl(expectedCacheControl.toHttpCacheControl())
            .expectBody(String.class)
            .returnResult();

        //given ( 응답값으로 etag가 추가된다)
        String etag = response.getResponseHeaders()
            .getETag();

        //when then
        client
            .get()
            .uri(uri)
            .header("If-None-Match", etag)
            .exchange()
            .expectStatus()
            .isNotModified();
    }

    @Test
    @DisplayName("정적자원중 css에 대한 요청은 max-age 1년이 추가된다")
    void get_static_resources_css() {
        //given
        String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/article.css";

        //when & then
        EntityExchangeResult<String> response = client
            .get()
            .uri(uri)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .cacheControl(CacheControl.maxAge(Duration.ofSeconds(60 * 60 * 24 * 365)))
            .expectBody(String.class)
            .returnResult();

        //given ( 응답값으로 etag가 추가된다)
        String etag = response.getResponseHeaders()
            .getETag();

        //when then
        client
            .get()
            .uri(uri)
            .header("If-None-Match", etag)
            .exchange()
            .expectStatus()
            .isNotModified();
    }

    @Test
    @DisplayName("정적자원중 css,js가 아닌 다른 자원에 대한 요청은 no-cache, no-store, private 설정이 추가된다")
    void get_static_resources_etc() {
        //given
        String uri = "/images/main_logo.png";
        Cachecontrol expectedCacheControl = new Cachecontrol();
        expectedCacheControl.setNoCache(true);
        expectedCacheControl.setNoStore(true);
        expectedCacheControl.setCachePrivate(true);

        //when & then
        EntityExchangeResult<String> response = client
            .get()
            .uri(uri)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .cacheControl(expectedCacheControl.toHttpCacheControl())
            .expectBody(String.class)
            .returnResult();

        //when ( 응답값으로 etag가 추가되지 않는다)
        String etag = response.getResponseHeaders()
            .getETag();
        //then
        assertThat(etag).isNull();
    }

}
