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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class LineService {

    private LineRepository lineRepository;
    private StationService stationService;

    public LineService(LineRepository lineRepository, StationService stationService) {
        this.lineRepository = lineRepository;
        this.stationService = stationService;
    }

    @Transactional
    @Caching(evict = {@CacheEvict(value = "line", allEntries = true),
        @CacheEvict(value = "map", allEntries = true)})
    public LineResponse saveLine(LineRequest request) {
        Station upStation = stationService.findById(request.getUpStationId());
        Station downStation = stationService.findById(request.getDownStationId());
        Line persistLine = lineRepository.save(
            new Line(request.getName(), request.getColor(), upStation, downStation,
                request.getDistance()));
        return LineResponse.of(persistLine);
    }

    @Transactional // master, slave 테스트를 위해 읽기전용이 아니도록 설정함
    @Cacheable(value = "line", key = "#pageable")
    public List<LineResponse> findLineResponses(Pageable pageable) {
        Page<Line> persistLines = findLines(pageable);
        return persistLines.stream()
            .map(LineResponse::of)
            .collect(Collectors.toList());
    }

    @Cacheable(value = "line", key = "#pageable")
    public List<LineResponse> findLineResponses() {
        List<Line> persistLines = findLines();
        return persistLines.stream()
            .map(LineResponse::of)
            .collect(Collectors.toList());
    }

    public Page<Line> findLines(Pageable pageable) {
        Page<Line> findLines = lineRepository.findAll(pageable);
        return findLines;
    }

    public List<Line> findLines() {
        List<Line> findLines = lineRepository.findAll();
        return findLines;
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
    @CacheEvict(value = "line", allEntries = true)
    public LineResponse updateLine(Long id, LineRequest lineUpdateRequest) {
        Line persistLine = lineRepository.findById(id).orElseThrow(RuntimeException::new);
        persistLine.update(new Line(lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
        return LineResponse.of(persistLine);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(value = "map", allEntries = true),
        @CacheEvict(value = "line", allEntries = true)})
    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(value = "map", allEntries = true),
        @CacheEvict(value = "line", allEntries = true)})
    public LineResponse addLineStation(Long id, SectionRequest request) {
        Line line = findLineById(id);
        Station upStation = stationService.findStationById(request.getUpStationId());
        Station downStation = stationService.findStationById(request.getDownStationId());
        line.addLineSection(upStation, downStation, request.getDistance());
        return LineResponse.of(line);
    }

    @Transactional
    @Caching(evict = {@CacheEvict(value = "map", allEntries = true),
        @CacheEvict(value = "line", allEntries = true)})
    public LineResponse removeLineStation(Long id, Long stationId) {
        Line line = findLineById(id);
        line.removeStation(stationId);
        return LineResponse.of(line);
    }
}
