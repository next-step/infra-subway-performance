package nextstep.subway.map.application;

import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.map.domain.SubwayPath;
import nextstep.subway.map.dto.PathResponse;
import nextstep.subway.map.dto.PathResponseAssembler;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
public class MapService {
    private static final Logger JSON_LOGGER = LoggerFactory.getLogger("json");

    private final LineService lineService;
    private final StationService stationService;
    private final PathService pathService;

    public MapService(LineService lineService, StationService stationService, PathService pathService) {
        this.lineService = lineService;
        this.stationService = stationService;
        this.pathService = pathService;
    }

    @Cacheable(value = "path", key = "{#source, #target}")
    @Transactional(readOnly = true)
    public PathResponse findPath(Long source, Long target) {
        List<Line> lines = lineService.findLines();
        Station sourceStation = stationService.findStationById(source);
        Station targetStation = stationService.findStationById(target);
        SubwayPath subwayPath = pathService.findPath(lines, sourceStation, targetStation);

        JSON_LOGGER.info("{}, {}, {}, {}",
                kv("출발지", sourceStation.getName()),
                kv("도착지", targetStation.getName()),
                kv("경로", subwayPath.getStations().toString()),
                kv("거리", subwayPath.calculateDistance())
        );

        return PathResponseAssembler.assemble(subwayPath);
    }
}
