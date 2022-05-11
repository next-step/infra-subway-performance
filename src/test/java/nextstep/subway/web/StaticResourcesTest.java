package nextstep.subway.web;

import nextstep.support.version.SubwayVersion;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

import static nextstep.subway.WebMvcConfig.PREFIX_STATIC_RESOURCES;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

    @Autowired
    private WebTestClient client;

    @Autowired
    private SubwayVersion version;

    @Test
    void get_static_images() {

        String jsUri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/main_logo.png";

        client
            .get()
            .uri(jsUri)
            .exchange()
                .expectStatus()
                    .isOk()
                .expectHeader()
                    .cacheControl(CacheControl.noCache().cachePrivate());
    }

}
