package nextstep.subway.common.support;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SubwayVersionSupport {


    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    private String version;

    @PostConstruct
    public void init() {
        version = nowVersion();
    }

    public String getVersion() {
        return version;
    }

    private static String nowVersion() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.now().format(dateTimeFormatter);
    }
}
