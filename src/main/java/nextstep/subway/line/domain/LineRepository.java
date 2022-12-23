package nextstep.subway.line.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    @Query("SELECT l FROM Line l WHERE l.id >= ?1")
    List<Line> findAll(Long id, Pageable pageable);
}
