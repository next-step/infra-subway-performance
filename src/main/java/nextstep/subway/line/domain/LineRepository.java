package nextstep.subway.line.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LineRepository extends JpaRepository<Line, Long> {

	@Query("SELECT l FROM Line l WHERE l.id > :id ORDER BY l.id ASC")
	Page<Line> findAll(Pageable pageable, @Param(value = "id") Long id);
}
