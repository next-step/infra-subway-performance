package nextstep.subway.line.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
    @Override
    Page<Line> findAll(Pageable pageable);
}
