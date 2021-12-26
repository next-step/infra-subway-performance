package nextstep.subway.support;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SubwayVersion {

    @Value("${subway-version}")
    private String version;

    public String getVersion() {
        return version;
    }
}
