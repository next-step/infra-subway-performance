package nextstep.subway.station.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Query("SELECT station FROM Station station WHERE station.id > :offset")
    List<Station> findAll(@Param("offset") Long offset, Pageable pageable);
}