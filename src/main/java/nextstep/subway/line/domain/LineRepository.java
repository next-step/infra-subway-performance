package nextstep.subway.line.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LineRepository extends JpaRepository<Line, Long> {

    @Query("SELECT line FROM Line line WHERE line.id > :offset")
    Slice<Line> findAll(@Param("offset") Long offset, Pageable pageable);
}
