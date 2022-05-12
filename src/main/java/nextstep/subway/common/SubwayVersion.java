package nextstep.subway.common;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SubwayVersion {
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    private String version;

    @PostConstruct
    public void init() {
        this.version = nowVersion();
    }

    public String getVersion() {
        return version;
    }

    private String nowVersion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
        return LocalDateTime.now().format(formatter);
    }



}
