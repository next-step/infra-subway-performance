package nextstep.subway.station.domain;

import nextstep.subway.station.dto.StationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * packageName : nextstep.subway.station
 * fileName : StationRepositoryTest
 * author : haedoang
 * date : 2021/12/25
 * description :
 */
@DataJpaTest
public class StationRepositoryTest {
    private static final int TOTAL_ELEMENTS = 100;

    @Autowired
    private StationRepository stationRepository;

    @BeforeEach
    void setUp() {
        List<Station> stations = new ArrayList<>();
        IntStream.range(0, TOTAL_ELEMENTS).forEach(
                i -> stations.add(new Station("station" + i))
        );

        stationRepository.saveAll(stations);
    }

    @Test
    @DisplayName("paging 처리하기")
    public void paging() {
        // given
        PageRequest pageRequest = PageRequest.of(1, 10);

        // when
        Page<Station> stations = stationRepository.findAll(pageRequest);

        // then
        assertThat(stations).hasSize(10);
        assertThat(stations.getTotalElements()).isEqualTo(TOTAL_ELEMENTS);
        assertThat(stations.getTotalPages()).isEqualTo(TOTAL_ELEMENTS / pageRequest.getPageSize());
    }

    @Test
    @DisplayName("page 요청 시 id를 기준으로 조회한다.")
    public void pageAdvance() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10);
        Long id = 20L;

        // when
        Page<Station> pageStation = stationRepository.findStationsPage(id, pageRequest);

        // then
        assertAll(
                () -> assertThat(pageStation.getTotalElements()).isEqualTo(TOTAL_ELEMENTS),
                () -> assertThat(pageStation.getContent()).hasSize(pageRequest.getPageSize())
        );
    }
}
