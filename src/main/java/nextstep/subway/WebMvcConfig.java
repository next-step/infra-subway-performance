package nextstep.subway;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String[] NOCACHE_PATHS = {"js", "images"};
    private static final String[] CACHE_PATHS = {"css"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        for (int i = 0; i < NOCACHE_PATHS.length; i++) {
            registry.addResourceHandler("/" + NOCACHE_PATHS[i] + "/**")
                    .addResourceLocations("classpath:/static/" + NOCACHE_PATHS[i] + "/")
                    .setCacheControl(CacheControl.noCache().cachePrivate());
        }
        for (int i = 0; i < CACHE_PATHS.length; i++) {
            registry.addResourceHandler("/" + CACHE_PATHS[i] + "/**")
                    .addResourceLocations("classpath:/static/" + CACHE_PATHS[i] + "/")
                    .setCacheControl(CacheControl.maxAge(365L, TimeUnit.DAYS));
        }
    }
}
