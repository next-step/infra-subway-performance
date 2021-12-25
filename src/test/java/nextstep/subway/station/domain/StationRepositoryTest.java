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
    public void paging() throws Exception {
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
    @DisplayName("pageImpl 사용하기 ")
    public void pagingToPageImpl() throws Exception {
        // given
        PageRequest pageRequest = PageRequest.of(1, 10);

        // when
        Page<Station> stations = stationRepository.findAll(pageRequest);

        // then
        PageImpl<Station> stationPageImpl = new PageImpl<>(stations.getContent(), stations.getPageable(), stations.getTotalElements());
        assertThat(stationPageImpl.getPageable().getPageNumber()).isEqualTo(pageRequest.getPageNumber());
        assertThat(stationPageImpl.getPageable().getPageSize()).isEqualTo(pageRequest.getPageSize());
        assertThat(stationPageImpl.getContent()).hasSize(10);
    }

    @Test
    @DisplayName("response 타입으로 응답하기")
    public void pagingResult() throws Exception {
        // given
        PageRequest pageRequest = PageRequest.of(1, 10);

        // when
        Page<Station> stations = stationRepository.findAll(pageRequest);

        PageImpl<StationResponse> stationResponses = new PageImpl<>(
                stations.getContent()
                        .stream()
                        .map(StationResponse::of)
                        .collect(Collectors.toList()),
                stations.getPageable(),
                stations.getTotalElements()
        );

        // then
        assertAll(
                () -> assertThat(stationResponses).hasSize(10),
                () -> assertThat(stationResponses.getPageable().getPageNumber()).isEqualTo(pageRequest.getPageNumber()),
                () -> assertThat(stationResponses.getPageable().getPageSize()).isEqualTo(pageRequest.getPageSize()),
                () -> assertThat(stationResponses.getTotalElements()).isEqualTo(TOTAL_ELEMENTS)
        );
    }


    @Test
    @DisplayName("page를 pk를 받아서 성능을 개선한다.")
    public void pageAdvance() throws Exception {
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
