package study.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.Lists;
import java.util.List;
import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.station.application.StationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@DisplayName("단위 테스트 - mockito를 활용한 가짜 협력 객체 사용")
class MockitoTest {

    @Test
    void findAllLines() {
        // given
        LineRepository lineRepository = mock(LineRepository.class);
        StationService stationService = mock(StationService.class);

        when(lineRepository.findAll(any(Pageable.class)))
            .thenReturn(new PageImpl<>(Lists.newArrayList(new Line())));
        LineService lineService = new LineService(lineRepository, stationService);

        // when
        List<LineResponse> responses = lineService.findLineResponses(PageRequest.of(0, 1));

        // then
        assertThat(responses).hasSize(1);
    }
}
