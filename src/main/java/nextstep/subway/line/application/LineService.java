package nextstep.subway.line.application;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.dto.LineRequest;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.line.dto.SectionRequest;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LineService {

    public static final String ALL_LINES_CACHE_KEY = "allLinesKey";

    private LineRepository lineRepository;
    private StationService stationService;

    public LineService(LineRepository lineRepository, StationService stationService) {
        this.lineRepository = lineRepository;
        this.stationService = stationService;
    }

    @Transactional
    @Caching(evict = {@CacheEvict(value = "line", key = "#root.target.ALL_LINES_CACHE_KEY"),
        @CacheEvict(value = "map", allEntries = true)})
    public LineResponse saveLine(LineRequest request) {
        Station upStation = stationService.findById(request.getUpStationId());
        Station downStation = stationService.findById(request.getDownStationId());
        Line persistLine = lineRepository.save(
            new Line(request.getName(), request.getColor(), upStation, downStation,
                request.getDistance()));
        return LineResponse.of(persistLine);
    }

    @Cacheable(value = "line", key = "#root.target.ALL_LINES_CACHE_KEY")
    public List<LineResponse> findLineResponses() {
        List<Line> persistLines = lineRepository.findAll();
        return persistLines.stream()
            .map(LineResponse::of)
            .collect(Collectors.toList());
    }

    public List<Line> findLines() {
        return lineRepository.findAll();
    }

    public Line findLineById(Long id) {
        return lineRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Cacheable(value = "line", key = "#id")
    public LineResponse findLineResponseById(Long id) {
        Line persistLine = findLineById(id);
        return LineResponse.of(persistLine);
    }

    @Transactional
    @Caching(put = {@CachePut(value = "line", key = "#id")},
        evict = {@CacheEvict(value = "line", key = "#root.target.ALL_LINES_CACHE_KEY")})
    public LineResponse updateLine(Long id, LineRequest lineUpdateRequest) {
        Line persistLine = lineRepository.findById(id).orElseThrow(RuntimeException::new);
        persistLine.update(new Line(lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
        return LineResponse.of(persistLine);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(value = "line", key = "#id"),
        @CacheEvict(value = "map", allEntries = true),
        @CacheEvict(value = "line", key = "#root.target.ALL_LINES_CACHE_KEY")})
    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }

    @Transactional
    @Caching(put = {@CachePut(value = "line", key = "#id")},
        evict = {@CacheEvict(value = "map", allEntries = true),
            @CacheEvict(value = "line", key = "#root.target.ALL_LINES_CACHE_KEY")})
    public LineResponse addLineStation(Long id, SectionRequest request) {
        Line line = findLineById(id);
        Station upStation = stationService.findStationById(request.getUpStationId());
        Station downStation = stationService.findStationById(request.getDownStationId());
        line.addLineSection(upStation, downStation, request.getDistance());
        return  LineResponse.of(line);
    }

    @Transactional
    @Caching(put = {@CachePut(value = "line", key = "#id")},
        evict = {@CacheEvict(value = "map", allEntries = true),
            @CacheEvict(value = "line", key = "#root.target.ALL_LINES_CACHE_KEY")})
    public LineResponse removeLineStation(Long id, Long stationId) {
        Line line = findLineById(id);
        line.removeStation(stationId);
        return LineResponse.of(line);
    }
}
