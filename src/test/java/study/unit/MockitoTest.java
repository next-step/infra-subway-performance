package study.unit;

import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.station.application.StationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("단위 테스트 - mockito를 활용한 가짜 협력 객체 사용")
public class MockitoTest {
    @Test
    void findAllLines() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10);
        LineRepository lineRepository = mock(LineRepository.class);
        StationService stationService = mock(StationService.class);

        when(lineRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(Collections.singletonList(new Line())));
        LineService lineService = new LineService(lineRepository, stationService);

        // when
        Page<LineResponse> responses = lineService.findLineResponses(pageRequest);

        // then
        assertThat(responses).hasSize(1);
    }
}
