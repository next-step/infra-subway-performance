package nextstep.subway.common.support.handlbars;

import nextstep.subway.common.support.version.ResourceVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;
import com.github.jknack.handlebars.Options;

@HandlebarsHelper
public class BlogHandlebarsHelper {
    private static final Logger logger = LoggerFactory.getLogger(BlogHandlebarsHelper.class);

    @Autowired
    private ResourceVersion resourceVersion;

    public String staticUrls(String path, Options options) {
        logger.debug("static url : {}", path);
        return String.format("/resources/%s%s", resourceVersion.getVersion(), path);
    }
}
