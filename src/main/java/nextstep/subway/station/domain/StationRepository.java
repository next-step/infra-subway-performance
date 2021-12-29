package nextstep.subway.station.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Query("SELECT station FROM Station station WHERE station.id >= :lastStationId")
    Page<Station> findAll(Pageable pageable, @Param("lastStationId") Long lastStationId);
}
