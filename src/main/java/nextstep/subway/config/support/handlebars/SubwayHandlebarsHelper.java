package nextstep.subway.config.support.handlebars;

import com.github.jknack.handlebars.Options;
import nextstep.subway.config.support.version.SubWayVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class SubwayHandlebarsHelper {
    public static final String STATIC_URL_FORMAT = "/resources/%s%s";

    private static final Logger logger = LoggerFactory.getLogger(SubwayHandlebarsHelper.class);

    @Autowired
    private SubWayVersion subWayVersion;

    public String staticUrls(String path, Options options) {
        logger.debug("static url : {}", path);
        return String.format(STATIC_URL_FORMAT, subWayVersion.version(), path);
    }
}
