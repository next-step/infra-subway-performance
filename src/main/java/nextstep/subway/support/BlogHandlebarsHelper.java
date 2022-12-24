package nextstep.subway.support;

import com.github.jknack.handlebars.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class BlogHandlebarsHelper {

    private static final Logger logger = LoggerFactory.getLogger(BlogHandlebarsHelper.class);

    @Autowired
    private SubwayVersion subwayVersion;

    public String staticUrls(String path, Options options) {
        logger.debug("static url : {}", path);
        return String.format("/resources/%s%s", subwayVersion.getVersion(), path);
    }
}
