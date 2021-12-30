package nextstep.subway.station.application;

import java.util.Arrays;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.subway.station.domain.Station;
import nextstep.subway.station.domain.StationRepository;
import nextstep.subway.station.dto.StationRequest;
import nextstep.subway.station.dto.StationResponse;

@Service
@Transactional
public class StationService {
    private static final String ID = "id";

    private StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public StationResponse saveStation(StationRequest stationRequest) {
        Station persistStation = stationRepository.save(stationRequest.toStation());
        return StationResponse.of(persistStation);
    }

    @Transactional(readOnly = true)
    public Page<StationResponse> findAllStations(final Pageable pageable) {
        if (sortedById(pageable.getSort())) {
            final Specification<Station> specification = computeSpecification(pageable);
            return stationRepository.findAll(specification, pageable)
                .map(StationResponse::of);
        }

        return stationRepository.findAll(pageable)
            .map(StationResponse::of);
    }

    private boolean sortedById(final Sort sort) {
        return Arrays.asList(Sort.by(ID).ascending(), Sort.by(ID).descending(), Sort.unsorted()).contains(sort);
    }

    private Specification<Station> computeSpecification(final Pageable pageable) {
        final Sort.Order order = pageable.getSort().getOrderFor(ID);

        if (order != null && order.getDirection() == Sort.Direction.DESC) {
            final Long maxId = stationRepository.getMaxId();
            final long boundaryId = maxId - (long)pageable.getPageNumber() * pageable.getPageSize();
            return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(ID), boundaryId);
        }

        final long boundaryId = (long)pageable.getPageNumber() * pageable.getPageSize();
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(ID), boundaryId);
    }

    @CacheEvict(value = "station", key = "#id")
    public void deleteStationById(Long id) {
        stationRepository.deleteById(id);
    }

    @Cacheable(value = "station", key = "#id")
    public Station findStationById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Cacheable(value = "station", key = "#id")
    public Station findById(Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
