package nextstep.subway.util.support.handlebars;

import com.github.jknack.handlebars.Options;
import nextstep.subway.util.support.version.SubwayVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class SubwayHandlebarsHelper {
    private static final Logger logger = LoggerFactory.getLogger(SubwayHandlebarsHelper.class);

    private final SubwayVersion subwayVersion;

    public SubwayHandlebarsHelper(final SubwayVersion subwayVersion) {
        this.subwayVersion = subwayVersion;
    }

    public String staticUrls(String path, Options options) {
        logger.debug("static url : {}", path);
        return String.format("/resources/%s%s", subwayVersion.getVersion(), path);
    }
}
