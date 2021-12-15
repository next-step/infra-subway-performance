package nextstep.subway.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ResourceVersion {

    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    private String version;

    @PostConstruct
    public void init() {
        this.version = nowVersion();
    }

    public String getVersion() {
        return version;
    }

    private static String nowVersion() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT));
    }
}
