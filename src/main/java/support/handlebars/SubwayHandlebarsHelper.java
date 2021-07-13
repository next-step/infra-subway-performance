package support.handlebars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jknack.handlebars.Options;

import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;
import support.version.SubwayVersion;

@HandlebarsHelper
public class SubwayHandlebarsHelper {
    private static final Logger logger = LoggerFactory.getLogger(SubwayHandlebarsHelper.class);

    @Autowired
    private SubwayVersion subwayVersion;

    public String staticUrls(String path, Options options) {
        logger.debug("static url : {}", path);
        return String.format("/resources/%s%s", subwayVersion.getVersion(), path);
    }
}
