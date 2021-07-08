package nextstep.subway.station.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nextstep.subway.common.CustomLocalDateTimeDeserializer;
import nextstep.subway.common.CustomLocalDateTimeSerializer;
import nextstep.subway.station.domain.Station;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class StationResponse {
    private Long id;
    private String name;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifiedDate;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.getName(), station.getCreatedDate(), station.getModifiedDate());
    }

    public StationResponse() {
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }
}
