package nextstep.subway.line.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nextstep.subway.line.domain.Line;
import nextstep.subway.station.dto.StationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LineResponse {
    private Long id;
    private String name;
    private String color;
    private List<StationResponse> stations;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime modifiedDate;

    public LineResponse() {
    }

    public LineResponse(Long id, String name, String color, List<StationResponse> stations, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
        this.createDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static LineResponse of(Line line) {
        if (isEmpty(line)) {
            return new LineResponse(line.getId(), line.getName(), line.getColor(), new ArrayList(), line.getCreatedDate(), line.getModifiedDate());
        }
        return new LineResponse(line.getId(), line.getName(), line.getColor(), assembleStations(line), line.getCreatedDate(), line.getModifiedDate());
    }

    private static boolean isEmpty(Line line) {
        return line.getStations().isEmpty();
    }

    private static List<StationResponse> assembleStations(Line line) {
        return line.getStations().stream()
                .map(StationResponse::of)
                .collect(toList());
    }

    public static PageImpl<LineResponse> ofList(Page<Line> lines) {
        return new PageImpl<>(
                lines.stream()
                        .map(LineResponse::of)
                        .collect(toList()),
                lines.getPageable(),
                lines.getTotalElements());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<StationResponse> getStations() {
        return stations;
    }
}
