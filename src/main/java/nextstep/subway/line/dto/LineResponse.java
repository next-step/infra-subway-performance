package nextstep.subway.line.dto;

import nextstep.subway.common.BaseResponse;
import nextstep.subway.line.domain.Line;
import nextstep.subway.station.dto.StationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LineResponse extends BaseResponse {
    private Long id;
    private String name;
    private String color;
    private List<StationResponse> stations;

    public LineResponse() {
        super();
    }

    public LineResponse(Long id, String name, String color, List<StationResponse> stations, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.name = name;
        this.color = color;
        this.stations = stations;
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

    @Override
    public String toString() {
        return "LineResponse{" +
                "createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", stations=" + stations +
                '}';
    }
}
