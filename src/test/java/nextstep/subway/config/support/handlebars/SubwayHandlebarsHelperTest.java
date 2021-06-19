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

    @DisplayName("SubwayHandlebarsHelper 빈이 생성되었는지 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(subwayHandlebarsHelper).isNotNull(),
                () -> assertThat(subwayHandlebarsHelper).isInstanceOf(SubwayHandlebarsHelper.class)
        );
    }

}