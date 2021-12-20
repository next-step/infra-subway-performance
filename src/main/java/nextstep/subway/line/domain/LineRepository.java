package nextstep.subway.line.domain;

import java.util.List;
import nextstep.subway.common.PageRequestPerform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LineRepository extends JpaRepository<Line, Long> {

    @Query("SELECT l FROM Line l WHERE l.id >= :position")
    <T extends PageRequestPerform> List<Line> findLineAll(T pageable, Long position);

}
