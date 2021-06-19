package nextstep.subway.config.support.version;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SubWayVersion {
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    private String version;

    @PostConstruct
    public void init() {
        version = nowVersion();
    }

    public static String nowVersion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.now().format(formatter);
    }

    public String version() {
        return version;
    }

}
