package nextstep.subway.common;

import org.apache.coyote.ProtocolHandler;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Profile("prod")
@Configuration
public class TomcatKeepAliveConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> {
            ProtocolHandler protocolHandler = connector.getProtocolHandler();
            if (protocolHandler instanceof AbstractHttp11Protocol) {
                applyProperties((AbstractHttp11Protocol) protocolHandler);
            }
        });
    }

    private void applyProperties(AbstractHttp11Protocol protocolHandler) {
        protocolHandler.setMaxKeepAliveRequests(300);
        protocolHandler.setKeepAliveTimeout(30000);
    }
}
