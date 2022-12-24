package nextstep.subway.station.application;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.dto.StationRequest;
import nextstep.subway.station.dto.StationResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StationService {
    private StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }


    @CacheEvict(cacheNames = "stations", allEntries = true)
    @Transactional
    public StationResponse saveStation(StationRequest stationRequest) {
        Station persistStation = stationRepository.save(stationRequest.toStation());
        return StationResponse.of(persistStation);
    }

    @Cacheable(cacheNames = "stations", unless = "#result.isEmpty()")
    @Transactional(readOnly = true)
    public List<StationResponse> findAllStations(Long id, Pageable pageable) {
        List<Station> stations = stationRepository.findAll(id, pageable);

        return stations.stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "paths", allEntries = true),
            @CacheEvict(cacheNames = "lines", allEntries = true),
            @CacheEvict(cacheNames = "stations", allEntries = true),
    })
    @Transactional
    public void deleteStationById(Long id) {
        stationRepository.deleteById(id);
    }

    public Station findStationById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Station findById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
