package nextstep.subway.line.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LineRepository extends JpaRepository<Line, Long> {

    @Query("SELECT line FROM Line line WHERE line.id > ?1")
    Slice<Line> findAll(Long offset, Pageable pageable);
}
