package nextstep.subway.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public static final String PREFIX_STATIC_RESOURCES = "/resources";
    public static final int CSS_MAX_AGE = 60 * 60 * 24 * 365;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/css/**")
            .addResourceLocations("classpath:/static/css/")
            .setCachePeriod(CSS_MAX_AGE);

        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/js/**")
            .addResourceLocations("classpath:/static/js/")
            .setCacheControl(CacheControl.noCache().cachePrivate());

        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/**")
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
}
