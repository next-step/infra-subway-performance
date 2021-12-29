package nextstep.subway.station.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT station FROM Station station WHERE station.id > :offset")
    List<Station> findAll(@Param("offset") Long offset, Pageable pageable);
}
