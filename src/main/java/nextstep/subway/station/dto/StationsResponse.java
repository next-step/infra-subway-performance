package nextstep.subway.station.dto;

import nextstep.subway.station.domain.Station;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StationsResponse {

    private Long totalPage;
    private List<StationResponse> stations = Collections.emptyList();

    public StationsResponse() {

    }

    public StationsResponse(Integer size, Long totalCount, List<Station> stations) {
        this.totalPage = totalCount / size;
        if(totalCount % size != 0) {
            this.totalPage += 1;
        }
        this.stations = stations.stream()
                .map(StationResponse::of)
                .collect(toList());

    }

    public Long getTotalPage() {
        return totalPage;
    }

    public List<StationResponse> getStations() {
        return stations;
    }
}
