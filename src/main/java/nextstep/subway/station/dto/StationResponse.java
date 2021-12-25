package nextstep.subway.station.dto;

import nextstep.subway.common.BaseResponse;
import nextstep.subway.station.domain.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StationResponse extends BaseResponse {
    private Long id;
    private String name;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.getName(), station.getCreatedDate(), station.getModifiedDate());
    }

    public static List<StationResponse> ofList(List<Station> stations) {
        return stations.stream().map(StationResponse::of).collect(toList());
    }

    public StationResponse() {
        super();
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.name = name;
    }

    public static PageImpl<StationResponse> ofList(Page<Station> stations) {
        return new PageImpl<>(
                stations.stream()
                        .map(StationResponse::of)
                        .collect(toList()),
                stations.getPageable(),
                stations.getTotalElements()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StationResponse{" +
                "createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
