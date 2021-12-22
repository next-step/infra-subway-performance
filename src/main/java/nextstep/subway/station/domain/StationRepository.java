package nextstep.subway.station.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    Page<Station> findAll(Pageable pageable);
}
