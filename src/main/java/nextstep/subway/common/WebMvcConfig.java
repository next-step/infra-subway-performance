package nextstep.subway.common;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String PREFIX_STATIC_RESOURCES = "/resources";
    public static final int CACHE_MAX = 60 * 60 * 24 * 30;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(CACHE_MAX);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        final Filter etgHeaderFilter = new ShallowEtagHeaderFilter();
        registration.setFilter(etgHeaderFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

}
