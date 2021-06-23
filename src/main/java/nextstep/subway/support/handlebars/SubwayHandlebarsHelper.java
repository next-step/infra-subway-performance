package nextstep.subway.support.handlebars;

import com.github.jknack.handlebars.Options;
import nextstep.subway.support.version.SubwayVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class SubwayHandlebarsHelper {
    private static final Logger logger = LoggerFactory.getLogger(SubwayHandlebarsHelper.class);

    private final SubwayVersion blogVersion;

    public SubwayHandlebarsHelper(SubwayVersion blogVersion) {
        this.blogVersion = blogVersion;
    }

    public String staticUrls(String path, Options options) {
        logger.debug("static url : {}", path);
        return String.format("/resources/%s%s", blogVersion.getVersion(), path);
    }
}
