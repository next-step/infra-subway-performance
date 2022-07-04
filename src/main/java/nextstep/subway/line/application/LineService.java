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
@Transactional
public class LineService {
    private LineRepository lineRepository;
    private StationService stationService;

    public LineService(LineRepository lineRepository, StationService stationService) {
        this.lineRepository = lineRepository;
        this.stationService = stationService;
    }

    @Caching(
            cacheable = { @Cacheable(value = "lineResponse", key = "#result.id") },
            evict = { @CacheEvict(value = "allLineResponse"), @CacheEvict(value = "allLine"), @CacheEvict(value = "path") }
    )
    public LineResponse saveLine(LineRequest request) {
        Station upStation = stationService.findById(request.getUpStationId());
        Station downStation = stationService.findById(request.getDownStationId());
        Line persistLine = lineRepository.save(new Line(request.getName(), request.getColor(), upStation, downStation, request.getDistance()));
        return LineResponse.of(persistLine);
    }

    @Cacheable(value = "allLineResponse", unless = "#result.isEmpty()")
    public List<LineResponse> findLineResponses() {
        List<Line> persistLines = lineRepository.findAll();
        return persistLines.stream()
                .map(LineResponse::of)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "allLine", unless = "#result.isEmpty()")
    public List<Line> findLines() {
        return lineRepository.findAll();
    }

    @Cacheable(value = "line", key = "#id")
    public Line findLineById(Long id) {
        return lineRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Cacheable(value = "lineResponse", key = "#id")
    public LineResponse findLineResponseById(Long id) {
        Line persistLine = findLineById(id);
        return LineResponse.of(persistLine);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "allLine"), @CacheEvict(value = "allLineResponse"), @CacheEvict(value = "path"),
                    @CacheEvict(value = "line", key = "#id"), @CacheEvict(value = "lineResponse", key = "#id")
            }
    )
    public void updateLine(Long id, LineRequest lineUpdateRequest) {
        Line persistLine = lineRepository.findById(id).orElseThrow(RuntimeException::new);
        persistLine.update(new Line(lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
    }

    @Caching(
            evict = { @CacheEvict(value = "line", key = "#id"), @CacheEvict(value = "lineResponse", key = "#id"), @CacheEvict(value = "path") }
    )
    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "allLine"), @CacheEvict(value = "allLineResponse"), @CacheEvict(value = "path"),
                    @CacheEvict(value = "line", key = "#lineId"), @CacheEvict(value = "lineResponse", key = "#lineId")
            }
    )
    public void addLineStation(Long lineId, SectionRequest request) {
        Line line = findLineById(lineId);
        Station upStation = stationService.findById(request.getUpStationId());
        Station downStation = stationService.findById(request.getDownStationId());
        line.addLineSection(upStation, downStation, request.getDistance());
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "allLine"), @CacheEvict(value = "allLineResponse"), @CacheEvict(value = "path"),
                    @CacheEvict(value = "line", key = "#lineId"), @CacheEvict(value = "lineResponse", key = "#lineId")
            }
    )
    public void removeLineStation(Long lineId, Long stationId) {
        Line line = findLineById(lineId);
        line.removeStation(stationId);
    }

}
