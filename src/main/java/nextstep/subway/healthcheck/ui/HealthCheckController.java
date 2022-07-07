package nextstep.subway.healthcheck.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    public HealthCheckController() {
    }

    @GetMapping("/health")
    public ResponseEntity getHealth() {
        return ResponseEntity.ok().build();
    }
}
