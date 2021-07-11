package nextstep.subway.common.handlesbars;

import com.github.jknack.handlebars.Options;
import nextstep.subway.common.SubwayVersion;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class SubwayHandlebarsHelper {
    private final SubwayVersion subwayVersion;

    public SubwayHandlebarsHelper(SubwayVersion subwayVersion) {
        this.subwayVersion = subwayVersion;
    }

    public String staticUrls(String path, Options options) {
        return String.format("/resources/%s%s", subwayVersion.getVersion(), path);
    }
}