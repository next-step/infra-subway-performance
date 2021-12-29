package nextstep.subway.station.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

	@Query("SELECT s FROM Station s WHERE s.id > :id ORDER BY s.id ASC")
	Page<Station> findAll(Pageable pageable, @Param(value = "id") Long id);
}
