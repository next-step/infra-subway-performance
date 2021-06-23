package nextstep.subway.support.handlebars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jknack.handlebars.Options;

import nextstep.subway.support.version.SubwayVersion;
import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class SubwayHandlebarsHelper {
	private static final Logger logger = LoggerFactory.getLogger(SubwayHandlebarsHelper.class);
	private final SubwayVersion subwayVersion;

	public SubwayHandlebarsHelper(SubwayVersion subwayVersion) {
		this.subwayVersion = subwayVersion;
	}

	public String staticUrls(String path, Options options) {
		logger.debug("static url : {}", path);
		return String.format("/resources/%s%s", subwayVersion.getVersion(), path);
	}
}