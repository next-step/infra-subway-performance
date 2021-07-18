package nextstep.subway.favorite.dto;

import nextstep.subway.favorite.domain.Favorite;
import nextstep.subway.station.domain.Station;
import nextstep.subway.station.dto.StationResponse;
import org.springframework.data.domain.Page;

import java.util.Map;

public class FavoriteResponse {
	private Long id;
	private StationResponse source;
	private StationResponse target;

	public FavoriteResponse() {
	}

	public FavoriteResponse(Long id, StationResponse source, StationResponse target) {
		this.id = id;
		this.source = source;
		this.target = target;
	}

	public static FavoriteResponse of(Favorite favorite, StationResponse source, StationResponse target) {
		return new FavoriteResponse(favorite.getId(), source, target);
	}

	public static Page<FavoriteResponse> of(Page<Favorite> favorites, Map<Long, Station> stations) {
		return favorites.map(favorite -> FavoriteResponse.of(favorite,
				StationResponse.of(stations.get(favorite.getSourceStationId())),
				StationResponse.of(stations.get(favorite.getTargetStationId()))));
	}

	public Long getId() {
		return id;
	}

	public StationResponse getSource() {
		return source;
	}

	public StationResponse getTarget() {
		return target;
	}
}
