package nextstep.subway.station.dto;

import nextstep.subway.station.domain.Station;

import java.time.LocalDateTime;

public class StationResponse {
    private Long id;
    private String name;
    private String createdDate;
    private String modifiedDate;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.getName(), station.getCreatedDate(), station.getModifiedDate());
    }

    public StationResponse() {
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate.toString();
        this.modifiedDate = modifiedDate.toString();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }
}
