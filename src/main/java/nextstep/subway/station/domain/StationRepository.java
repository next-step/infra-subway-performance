package nextstep.subway.station.domain;

import java.util.List;
import nextstep.subway.common.PageRequestPerform;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {

    @Query("SELECT s FROM Station s WHERE s.id >= :position")
    List<Station> findStationAll(@Param("pageable") Pageable pageable, @Param("position") Long position);
}