package nextstep.subway.station.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.dto.StationRequest;
import nextstep.subway.station.dto.StationResponse;

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
	public Page<StationResponse> findAllStations(Pageable pageable) {
		Page<Station> stations = stationRepository.findAll(pageable);

		return stations.map(StationResponse::of);
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
}
