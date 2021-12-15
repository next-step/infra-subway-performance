package nextstep.subway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final int CACHE_MAX_AGE = 60 * 60 * 24 * 365;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(CACHE_MAX_AGE);
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
