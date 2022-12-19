package nextstep.subway.map.application;

import nextstep.subway.line.application.LineService;
import nextstep.subway.line.domain.Line;
import nextstep.subway.map.domain.SubwayPath;
import nextstep.subway.map.dto.PathResponse;
import nextstep.subway.map.dto.PathResponseAssembler;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MapService {
    private LineService lineService;
    private StationRepository stationRepository;
    private PathService pathService;

    public MapService(LineService lineService, StationRepository stationRepository, PathService pathService) {
        this.lineService = lineService;
        this.stationRepository = stationRepository;
        this.pathService = pathService;
    }

    @Cacheable(value = "path", key = "{#source, #destination}")
    public PathResponse findPath(Long source, Long destination) {
        List<Line> lines = lineService.findLines();
        Station sourceStation = stationRepository.findById(source).orElseThrow(NoClassDefFoundError::new);
        Station targetStation = stationRepository.findById(destination).orElseThrow(NoClassDefFoundError::new);
        SubwayPath subwayPath = pathService.findPath(lines, sourceStation, targetStation);

        return PathResponseAssembler.assemble(subwayPath);
    }
}
