package nextstep.subway.station.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query(value = "SELECT s FROM Station s WHERE s.id > :offset",
        countQuery = "SELECT count(s) FROM Station s")
    Page<Station> findAll(@Param("offset") Long offset, Pageable pageable);
}
