package nextstep.subway.map.ui;

import static net.logstash.logback.argument.StructuredArguments.kv;

import nextstep.subway.map.application.MapService;
import nextstep.subway.map.dto.PathResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {
    private static final Logger json = LoggerFactory.getLogger("json");
    private MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/paths")
    public ResponseEntity<PathResponse> findPath(@RequestParam Long source, @RequestParam Long target) {
        json.info("[경로검색] {}, {}", kv("출발지", source), kv("목적지", target));
        return ResponseEntity.ok(mapService.findPath(source, target));
    }
}
