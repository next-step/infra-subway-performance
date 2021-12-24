package nextstep.subway.station.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nextstep.subway.common.BaseResponse;
import nextstep.subway.station.domain.Station;

import java.time.LocalDateTime;

public class StationResponse extends BaseResponse {
    private Long id;
    private String name;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.getName(), station.getCreatedDate(), station.getModifiedDate());
    }

    public StationResponse() {
        super();
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.name = name;
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
