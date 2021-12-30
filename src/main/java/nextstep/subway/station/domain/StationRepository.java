package nextstep.subway.station.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Query(value = "select s from Station s where s.id >= :id", countQuery = "select count(s) from Station s")
    Page<Station> findAll(Pageable pageable, @Param("id") Long id);
}
