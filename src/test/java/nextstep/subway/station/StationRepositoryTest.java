package nextstep.subway.station;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StationRepositoryTest {

    @Autowired
    private StationRepository stationRepository;

    @DisplayName("지하철 역 특정 개수만 가져오기")
    @Test
    void findAllStationsByOffsetAndSize() {

        //given
        final int size = 3;
        final int offset = 0;
        final PageRequest pageRequest = PageRequest.of(offset, size);

        Station 강남역 = new Station("강남역");
        Station 교대역 = new Station("교대역");
        Station 신대방역 = new Station("신대방역");
        Station 신림역 = new Station("신림역");
        Station 구로디지털단지역 = new Station("구로디지털단지역");

        List<Station> saveStations = Arrays.asList(강남역, 교대역, 신대방역, 신림역, 구로디지털단지역);
        stationRepository.saveAll(saveStations);

        //when
        List<Station> stations = stationRepository.findByIdGreaterThanEqualOrderById(1L, pageRequest);

        //then
        assertThat(stations.size()).isEqualTo(size);
        assertThat(stations).containsExactly(강남역, 교대역, 신대방역);
    }
}
