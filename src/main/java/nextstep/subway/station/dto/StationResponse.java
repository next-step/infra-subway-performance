package nextstep.subway.station.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nextstep.subway.LocalDateTimePersistenceConverter;
import nextstep.subway.station.domain.Station;

import javax.persistence.Convert;
import java.io.Serializable;
import java.time.LocalDateTime;

public class StationResponse implements Serializable {
    private Long id;
    private String name;
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    private LocalDateTime createdDate;
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    private LocalDateTime modifiedDate;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.getName(), null, null);
    }

    public StationResponse() {
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
//        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public LocalDateTime getModifiedDate() {
//        return modifiedDate;
//    }
}
