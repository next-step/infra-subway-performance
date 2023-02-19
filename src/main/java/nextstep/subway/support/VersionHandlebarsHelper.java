package nextstep.subway.support;

import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class VersionHandlebarsHelper {

    private final ResourceVersion version;

    public VersionHandlebarsHelper(ResourceVersion version) {
        this.version = version;
    }

    public String staticUrls(String path) {
        return String.format("/resources/%s%s", version.getVersion(), path);
    }
}
