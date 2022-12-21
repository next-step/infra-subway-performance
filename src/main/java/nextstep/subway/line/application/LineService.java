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

    @Caching(evict = {
            @CacheEvict(cacheNames = "lines", allEntries = true),
            @CacheEvict(cacheNames = "path", allEntries = true)
    })
    public LineResponse saveLine(LineRequest request) {
        Station upStation = stationService.findById(request.getUpStationId());
        Station downStation = stationService.findById(request.getDownStationId());
        Line persistLine = lineRepository.save(new Line(request.getName(), request.getColor(), upStation, downStation, request.getDistance()));
        return LineResponse.of(persistLine);
    }

    @Cacheable(value = "lines", unless = "#result.isEmpty()")
    @Transactional(readOnly = true)
    public List<LineResponse> findLineResponses() {
        List<Line> persistLines = lineRepository.findAll();
        return persistLines.stream()
                .map(LineResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Line> findLines() {
        return lineRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Line findLineById(Long id) {
        return lineRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    public LineResponse findLineResponseById(Long id) {
        Line persistLine = findLineById(id);
        return LineResponse.of(persistLine);
    }

    @CachePut(value = "line", key = "#id")
    public void updateLine(Long id, LineRequest lineUpdateRequest) {
        Line persistLine = lineRepository.findById(id).orElseThrow(RuntimeException::new);
        persistLine.update(new Line(lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "lines", allEntries = true),
            @CacheEvict(cacheNames = "path", allEntries = true)
    })
    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "lines", allEntries = true),
            @CacheEvict(cacheNames = "path", allEntries = true)
    })
    public void addLineStation(Long lineId, SectionRequest request) {
        Line line = findLineById(lineId);
        Station upStation = stationService.findStationById(request.getUpStationId());
        Station downStation = stationService.findStationById(request.getDownStationId());
        line.addLineSection(upStation, downStation, request.getDistance());
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "lines", allEntries = true),
            @CacheEvict(cacheNames = "path", allEntries = true)
    })
    public void removeLineStation(Long lineId, Long stationId) {
        Line line = findLineById(lineId);
        line.removeStation(stationId);
    }

}
