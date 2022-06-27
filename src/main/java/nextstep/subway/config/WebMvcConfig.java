package nextstep.subway.config;

import nextstep.support.version.SubwayVersion;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public static final String PREFIX_STATIC_RESOURCES = "/resources";

    private final SubwayVersion version;

    public WebMvcConfig(SubwayVersion version) {
        this.version = version;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(60 * 60 * 24 * 365);
    }

    @Bean
    public FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean(){
        FilterRegistrationBean<ShallowEtagHeaderFilter> registration = new FilterRegistrationBean<>();
        ShallowEtagHeaderFilter etagHeaderFilter = new ShallowEtagHeaderFilter();
        registration.setFilter(etagHeaderFilter);
        registration.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return registration;
    }
}
