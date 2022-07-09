package nextstep.subway.station.domain;

import nextstep.subway.station.dto.StationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationRepository extends JpaRepository<Station, Long> {
    @Override
    List<Station> findAll();

    @Query("select s from Station s where s.id > :offset")
    Slice<StationResponse> findAll(@Param("offset") Long offset, Pageable pageable );
}
