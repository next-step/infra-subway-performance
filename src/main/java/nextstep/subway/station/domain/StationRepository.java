package nextstep.subway.station.domain;

import nextstep.subway.station.dto.StationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Query("select s from Station s where s.id > :id order by s.id asc")
    Slice<StationResponse> findAll(Pageable pageable, @Param("id") Long id);
}
