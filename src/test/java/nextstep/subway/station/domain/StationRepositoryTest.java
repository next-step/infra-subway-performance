package nextstep.subway.station.domain;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;
import nextstep.subway.common.PageRequestPerform;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class StationRepositoryTest {

    @Autowired
    StationRepository stationRepository;

    @BeforeEach
    void setUp() {
        final List<Station> saveStations = Stream.iterate(1, s -> s + 1)
            .limit(30)
            .map(station -> new Station("지하철_" + station))
            .collect(toList());
        stationRepository.saveAll(saveStations);
    }

    @Test
    void 속도저하_개선을_위해_idx_검증() {

        // given
        final int page = 1;
        final int size = 10;

        PageRequestPerform pageRequest = PageRequestPerform.of(size);

        // when
        List<Station> stations = stationRepository.findStationAll(pageRequest, pageRequest.getLastIdx(page, size));

        List<Long> collect = stations.stream()
            .map(Station::getId)
            .collect(toList());

        // then
        Assertions.assertThat(collect).contains(11L, 20L);
        Assertions.assertThat(collect).doesNotContain(10L, 21L);
    }
}