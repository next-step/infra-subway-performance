package nextstep.subway.station.domain;

import java.awt.print.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

    @Query("SELECT s FROM Station s WHERE s.id >= ?1")
    List<Station> findAll(Long id, Pageable pageable);
}
