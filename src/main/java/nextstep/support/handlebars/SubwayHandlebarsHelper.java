package nextstep.support.handlebars;

import com.github.jknack.handlebars.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;
import nextstep.support.version.SubwayVersion;

@HandlebarsHelper
public class SubwayHandlebarsHelper {
    private static final Logger logger = LoggerFactory.getLogger(SubwayHandlebarsHelper.class);

    @Autowired
    private SubwayVersion blogVersion;

    public String staticUrls(String path, Options options) {
        logger.debug("static url : {}", path);
        return String.format("/resources/%s%s", blogVersion.getVersion(), path);
    }
}