package nextstep.subway.station.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

    @Query(value = "select s from Station s where s.id >= ?1 ")
    List<Station> findAll(Long id, Pageable pageable);
}