package nextstep.subway.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CacheVersion {

    private final String version;

    public CacheVersion() {
        this.version = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public String getVersion() {
        return version;
    }
}
