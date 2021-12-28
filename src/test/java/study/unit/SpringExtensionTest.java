package study.unit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.google.common.collect.Lists;

import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.station.application.StationService;

@DisplayName("단위 테스트 - SpringExtension을 활용한 가짜 협력 객체 사용")
@ExtendWith(org.springframework.test.context.junit.jupiter.SpringExtension.class)
public class SpringExtensionTest {
    @MockBean
    private LineRepository lineRepository;
    @MockBean
    private StationService stationService;

    @Test
    void findAllLines() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10);
        when(lineRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(Lists.newArrayList(new Line())));
        LineService lineService = new LineService(lineRepository, stationService);

        // when
        Page<LineResponse> responses = lineService.findLineResponses(pageRequest);

        // then
        assertThat(responses).hasSize(1);
    }
}
