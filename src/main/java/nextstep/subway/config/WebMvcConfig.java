package nextstep.subway.config;

import nextstep.subway.support.SubwayVersion;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String PREFIX_STATIC_RESOURCES = "/resources";

    private final SubwayVersion subwayVersion;

    public WebMvcConfig(SubwayVersion subwayVersion) {
        this.subwayVersion = subwayVersion;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(createResourcePathPattern("/static/css/**"))
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(60 * 60 * 24 * 365);

        registry.addResourceHandler(createResourcePathPattern("/static/js/**"))
                .addResourceLocations("classpath:/static/js/")
                .setCacheControl(CacheControl.noCache().cachePrivate());

        registry.addResourceHandler(createResourcePathPattern("/static/**"))
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache().cachePrivate());
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        registration.setFilter(etagHeaderFilter);
        registration.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return registration;
    }

    private String createResourcePathPattern(String path) {
        return PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + path;
    }
}