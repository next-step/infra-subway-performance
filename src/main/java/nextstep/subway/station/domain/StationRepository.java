package nextstep.subway.station.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

    Page<Station> findAll(Specification<Station> specification, Pageable pageable);

    @Query("select coalesce(max(s.id), 0) from Station s")
    Long getMaxId();
}
