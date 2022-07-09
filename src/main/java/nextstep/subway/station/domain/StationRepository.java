package nextstep.subway.station.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

    @Query("select s from Station s where s.id >= :offset")
    List<Station> findAll(long offset, Pageable pg);
}
