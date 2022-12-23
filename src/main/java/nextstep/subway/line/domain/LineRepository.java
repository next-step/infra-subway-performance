package nextstep.subway.line.domain;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LineRepository extends JpaRepository<Line, Long> {

    @Query("SELECT l FROM Line l WHERE l.id >= :id")
    List<Line> findAll(@Param("id") Long id, Pageable pageable);
}
