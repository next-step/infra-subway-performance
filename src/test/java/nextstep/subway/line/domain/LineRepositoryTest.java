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

        // then
        assertAll(
                () -> assertThat(lines).hasSize(10),
                () -> assertThat(lines.getPageable().getPageNumber()).isEqualTo(pageRequest.getPageNumber()),
                () -> assertThat(lines.getPageable().getPageSize()).isEqualTo(pageRequest.getPageSize()),
                () -> assertThat(lines.getTotalElements()).isEqualTo(TOTAL_ELEMENTS)
        );
    }

    @Test
    @DisplayName("page 요청 시 id를 기준으로 조회한다.")
    public void pageAdvance() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 10);
        Long id = 20L;

        // when
        Page<Line> pageLine = lineRepository.findLinesPage(id, pageRequest);

        // then
        assertAll(
                () -> assertThat(pageLine.getTotalElements()).isEqualTo(TOTAL_ELEMENTS),
                () -> assertThat(pageLine.getContent()).hasSize(pageRequest.getPageSize())
        );
    }
}

