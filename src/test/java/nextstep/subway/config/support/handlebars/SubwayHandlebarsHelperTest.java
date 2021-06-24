package nextstep.subway.config.support.handlebars;

import nextstep.subway.config.support.version.SubWayVersion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubwayHandlebarsHelperTest {

    @Autowired
    SubwayHandlebarsHelper subwayHandlebarsHelper;

    @Autowired
    SubWayVersion subWayVersion;

    @DisplayName("SubwayHandlebarsHelper 빈이 생성되었는지 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(subwayHandlebarsHelper).isNotNull(),
                () -> assertThat(subwayHandlebarsHelper).isInstanceOf(SubwayHandlebarsHelper.class)
        );
    }

    @DisplayName("SubwayHandlebarsHelper 빈이 알맞은 경로를 생성하는지 테스트")
    @Test
    void staticUrls() {
        // given
        String path = "main.js";
        String expectedUrls = String.format(SubwayHandlebarsHelper.STATIC_URL_FORMAT,
                subWayVersion.version(), path);

        // when
        String actualUrls = subwayHandlebarsHelper.staticUrls(path, null);

        // then
        assertThat(actualUrls).isEqualTo(expectedUrls);
    }

}