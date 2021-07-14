package nextstep.subway.station.application;

import static nextstep.subway.map.application.MapService.*;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.dto.StationRequest;
import nextstep.subway.station.dto.StationResponse;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationService {
    public static final String ALL_STATIONS_CACHE_KEY = "STATIONS";
    public static final String STATIONS_CACHE_VALUE = "stations";

    private StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }


    @Caching(
        evict = {
            @CacheEvict(value = STATIONS_CACHE_VALUE, key = "#root.target.ALL_STATIONS_CACHE_KEY"),
            @CacheEvict(value = PATH_CACHE_VALUE, allEntries = true),
        }
    )
    public StationResponse saveStation(StationRequest stationRequest) {
        Station persistStation = stationRepository.save(stationRequest.toStation());
        return StationResponse.of(persistStation);
    }

    @Cacheable(value = STATIONS_CACHE_VALUE, key = "#root.target.ALL_STATIONS_CACHE_KEY")
    @Transactional(readOnly = true)
    public List<StationResponse> findAllStations() {
        List<Station> stations = stationRepository.findAll();

        return stations.stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());
    }

    @Caching(
        evict = {
            @CacheEvict(value = STATIONS_CACHE_VALUE, key = "#root.target.ALL_STATIONS_CACHE_KEY"),
            @CacheEvict(value = PATH_CACHE_VALUE, allEntries = true),
        }
    )
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
