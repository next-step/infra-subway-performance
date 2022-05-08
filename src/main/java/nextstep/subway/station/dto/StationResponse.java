package nextstep.subway.station.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nextstep.subway.station.domain.Station;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StationResponse {
    private Long id;
    private String name;

//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private String createdDate;

//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private String modifiedDate;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.getName(), station.getCreatedDate(), station.getModifiedDate());
    }

    public StationResponse() {
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate.format(DateTimeFormatter.ISO_DATE_TIME);
        this.modifiedDate = modifiedDate.format(DateTimeFormatter.ISO_DATE_TIME);
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
