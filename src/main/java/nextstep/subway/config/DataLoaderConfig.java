package nextstep.subway.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;

@Component
@Profile("!test")
public class DataLoaderConfig implements CommandLineRunner {
	private final StationRepository stationRepository;

	public DataLoaderConfig(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 100; i++) {
			stationRepository.save(new Station("station" + i));
		}
	}
}
