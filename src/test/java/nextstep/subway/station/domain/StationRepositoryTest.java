package nextstep.subway.station.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql(scripts = "classpath:scripts/data.sql")
class StationRepositoryTest {

    @Autowired
    StationRepository stationRepository;

    @Test
    @DisplayName("page 테스트")
    void findAllByIdAfterOrderById() {
        Slice<Station> stations = stationRepository.findAllByOrderById(PageRequest.of(1, 5));

        assertAll(
            () -> assertThat(stations.getNumberOfElements()).isEqualTo(5),
            () -> assertThat(stations).extracting(Station::getId).containsExactly(6L,7L,8L,9L,10L)
        );
    }
}