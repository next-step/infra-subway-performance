package nextstep.subway.station.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Sql("classpath:test-data.sql")
@ActiveProfiles("test")
public class StationRepositoryTest {
    @Autowired
    StationRepository stationRepository;

    @Test
    void testFindAll() {
        // given
        int totalSize = 20;
        long lastStationId = 0L;
        PageRequest pageable = PageRequest.of(0, 10);

        // when
        Page<Station> stations = stationRepository.findAll(pageable, lastStationId);

        // then
        assertAll(() -> assertThat(stations.getTotalElements()).isEqualTo(totalSize),
                () -> assertThat(stations.getTotalPages()).isEqualTo(totalSize / pageable.getPageSize()),
                () -> assertThat(stations.getNumberOfElements()).isEqualTo(pageable.getPageSize()),
                () -> assertThat(stations).extracting(Station::getId).containsExactly(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L)
        );
    }
}
