package nextstep.subway.line.domain;

import nextstep.subway.line.dto.LineResponse;
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
 * packageName : nextstep.subway.line.domain
 * fileName : LineRepository
 * author : haedoang
 * date : 2021/12/25
 * description :
 */

@DataJpaTest
public class LineRepositoryTest {
    private static final int TOTAL_ELEMENTS = 100;

    @Autowired
    private LineRepository lineRepository;

    @BeforeEach
    void setUp() {
        List<Line> lines = new ArrayList<>();

        IntStream.range(0, TOTAL_ELEMENTS).forEach(
                i -> lines.add(new Line("line" + i, "주황색"))
        );

        lineRepository.saveAll(lines);
    }

    @Test
    @DisplayName("노선 페이지 쿼리를 작성한다.")
    public void paging() throws Exception {
        // given
        PageRequest pageRequest = PageRequest.of(1, 10);

        // when
        Page<Line> lines = lineRepository.findAll(pageRequest);

        PageImpl<LineResponse> lineResponse = new PageImpl<>(
                lines.getContent()
                        .stream()
                        .map(LineResponse::of)
                        .collect(Collectors.toList()),
                lines.getPageable(),
                lines.getTotalElements()
        );

        // then
        assertAll(
                () -> assertThat(lineResponse).hasSize(10),
                () -> assertThat(lineResponse.getPageable().getPageNumber()).isEqualTo(pageRequest.getPageNumber()),
                () -> assertThat(lineResponse.getPageable().getPageSize()).isEqualTo(pageRequest.getPageSize()),
                () -> assertThat(lineResponse.getTotalElements()).isEqualTo(TOTAL_ELEMENTS)
        );
    }
}

