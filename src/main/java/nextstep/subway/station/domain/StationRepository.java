package nextstep.subway.station.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    @Query("select s from Station s where s.id >= ?1")
    List<Station> findAll();
}
