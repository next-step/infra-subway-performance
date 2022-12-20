package nextstep.subway.station.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s WHERE s.id >= :id")
    List<Station> findAll(@Param("id") Long id, Pageable pageable);
}
