package nextstep.subway.station.application;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.dto.StationRequest;
import nextstep.subway.station.dto.StationResponse;
import nextstep.subway.station.dto.StationsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationService {
    private StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public StationResponse saveStation(StationRequest stationRequest) {
        Station persistStation = stationRepository.save(stationRequest.toStation());
        return StationResponse.of(persistStation);
    }

    @Transactional(readOnly = true)
    public StationsResponse findAllStations(Integer offset, Integer size) {

        if(offset != null) {
            return findStations(offset, size);
        }

        List<Station> stations = stationRepository.findAll();
        return new StationsResponse(size, (long)stations.size(), stations);
    }

    public void deleteStationById(Long id) {
        stationRepository.deleteById(id);
    }

    public Station findStationById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Station findById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional(readOnly = true)
    public StationsResponse findStations(Integer offset, Integer size) {
        final PageRequest pageRequest = PageRequest.of(0, size);
        final long id = offset * size + 1;
        List<Station> stations = stationRepository.findByIdGreaterThanEqualOrderById(id, pageRequest);
        long count = stationRepository.count();

        return new StationsResponse(size, count, stations);
    }
}
