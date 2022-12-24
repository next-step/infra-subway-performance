package nextstep.subway.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SubwayVersion {

    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    private String version;

    private static String nowVersion() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    @PostConstruct
    public void init() {
        version = nowVersion();
    }

    public String getVersion() {
        return version;
    }
}
