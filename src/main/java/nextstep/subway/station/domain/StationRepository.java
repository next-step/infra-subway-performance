package nextstep.subway.station.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();
    
    @Query("SELECT station FROM Station station WHERE station.id > ?1")
    Slice<Station> findAll(Long offset, Pageable pageable);
}