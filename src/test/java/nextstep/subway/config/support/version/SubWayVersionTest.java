package nextstep.subway.config.support.version;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubWayVersionTest {

    @Autowired
    SubWayVersion subWayVersion;

    @DisplayName("SubWayVersion 빈이 생성되었는지 테스트")
    @Test
    void initialize() {
        assertAll(
                () -> assertThat(subWayVersion).isNotNull(),
                () -> assertThat(subWayVersion).isInstanceOf(SubWayVersion.class)
        );
    }

    @DisplayName("SubWayVersion 빈이 알맞은 버전을 반환하는지 테스트")
    @Test
    void version() {
        // given
        String actualVersion = subWayVersion.version();
        LocalDateTime actual = LocalDateTime.parse(actualVersion,
                DateTimeFormatter.ofPattern(SubWayVersion.DEFAULT_DATE_TIME_FORMAT));

        // when
        LocalDateTime startInclusive = actual.minus(1, ChronoUnit.HOURS);
        LocalDateTime endInclusive = actual.plus(1, ChronoUnit.HOURS);

        // then
        assertThat(actual).isBetween(startInclusive, endInclusive);
    }

}