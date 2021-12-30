package nextstep.subway.station.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DataJpaTest
class StationRepositoryTest {
    @Autowired
    private StationRepository stationRepository;

    @DisplayName("페이징 쿼리 테스트")
    @Test
    void findAll() {
        List<Station> stations = Arrays.asList(new Station("name1"),
            new Station("name2"),
            new Station("name3"),
            new Station("name4"));

        stationRepository.saveAll(stations);
        Pageable pageable = PageRequest.of(0, 3);

        Page<Station> result = stationRepository.findAllByIdGreaterThan(0L, pageable);

        assertAll(
            () -> assertThat(result.getTotalPages()).isEqualTo(2),
            () -> assertThat(result.getTotalElements()).isEqualTo(4),
            () -> assertThat(result.getNumberOfElements()).isEqualTo(3)
        );
    }
}