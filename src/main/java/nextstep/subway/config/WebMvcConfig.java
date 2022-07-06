package nextstep.subway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public static final String PREFIX_STATIC_RESOURCES = "/resources";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache().cachePrivate());

        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCacheControl(CacheControl.noCache().cachePrivate());

    }
}
