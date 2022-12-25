package nextstep.subway.station.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

    @Query(value = "SELECT s FROM Station s WHERE s.id >= ?1")
    List<Station> findAll(Long id, Pageable pageable);

}
