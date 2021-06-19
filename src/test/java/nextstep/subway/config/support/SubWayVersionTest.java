package nextstep.subway.config.support;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestComponent
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SubWayVersionTest {

    @Autowired
    SubWayVersion subWayVersion;

    @DisplayName("SubWayVersion 빈이 생성되었는지 테스트")
    @Test
    void initializedTest() {
        assertAll(
                () -> assertThat(subWayVersion).isNotNull(),
                () -> assertThat(subWayVersion).isInstanceOf(SubWayVersion.class)
        );
    }
}