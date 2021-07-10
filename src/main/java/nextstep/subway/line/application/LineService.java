package nextstep.subway.line.application;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.dto.LineRequest;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.line.dto.SectionRequest;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LineService {

    private static final String FIND_LINES = "'findLines'";

    private LineRepository lineRepository;
    private StationService stationService;

    public LineService(LineRepository lineRepository, StationService stationService) {
        this.lineRepository = lineRepository;
        this.stationService = stationService;
    }

    @CacheEvict(value = "lines", key = FIND_LINES)
    public LineResponse saveLine(LineRequest request) {
        Station upStation = stationService.findById(request.getUpStationId());
        Station downStation = stationService.findById(request.getDownStationId());
        Line persistLine = lineRepository.save(new Line(request.getName(), request.getColor(), upStation, downStation, request.getDistance()));
        return LineResponse.of(persistLine);
    }

    @Cacheable(value = "lines", key = FIND_LINES)
    public List<LineResponse> findLineResponses() {
        return findLines().stream()
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

    @Caching(evict = {
        @CacheEvict(value = "line", key = "#id"),
        @CacheEvict(value = "lines", key = FIND_LINES)
    })
    public void updateLine(Long id, LineRequest lineUpdateRequest) {
        Line persistLine = findLineById(id);
        persistLine.update(new Line(lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
    }

    @Caching(evict = {
        @CacheEvict(value = "line", key = "#id"),
        @CacheEvict(value = "lines", key = FIND_LINES)
    })
    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }

    @Caching(evict = {
        @CacheEvict(value = "line", key = "#lineId"),
        @CacheEvict(value = "lines", key = FIND_LINES)
    })
    public void addLineStation(Long lineId, SectionRequest request) {
        Line line = findLineById(lineId);
        Station upStation = stationService.findStationById(request.getUpStationId());
        Station downStation = stationService.findStationById(request.getDownStationId());
        line.addLineSection(upStation, downStation, request.getDistance());
    }

    @Caching(evict = {
        @CacheEvict(value = "line", key = "#lineId"),
        @CacheEvict(value = "lines", key = FIND_LINES)
    })
    public void removeLineStation(Long lineId, Long stationId) {
        Line line = findLineById(lineId);
        line.removeStation(stationId);
    }

}
