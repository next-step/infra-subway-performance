package nextstep.subway.station.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

    @Query(value = "select s from Station s where s.id >= ?1",
            countQuery = "select count(s) from Station s")
    Page<Station> findStationsPage(Long id, Pageable pageable);
}