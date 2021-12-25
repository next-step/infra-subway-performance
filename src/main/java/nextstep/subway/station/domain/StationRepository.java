package nextstep.subway.station.domain;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    Page<Station> findAll(Pageable pageable);
}
