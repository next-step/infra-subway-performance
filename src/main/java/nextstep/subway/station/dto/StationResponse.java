package nextstep.subway.station.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nextstep.subway.station.domain.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StationResponse  {
    private Long id;
    private String name;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime modifiedDate;

    public static StationResponse of(Station station) {
        return new StationResponse(station.getId(), station.getName(), station.getCreatedDate(), station.getModifiedDate());
    }

    public static List<StationResponse> ofList(List<Station> stations) {
        return stations.stream().map(StationResponse::of).collect(toList());
    }

    public StationResponse() {
    }

    public StationResponse(Long id, String name, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.createDate = createdDate;
        this.modifiedDate = modifiedDate;
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
}
