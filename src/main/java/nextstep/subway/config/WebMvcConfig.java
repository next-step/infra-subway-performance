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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //모든 정적 리소스 no-cache, private 적용
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache().cachePrivate());

        // 모든 확장자 css인 경우는 max-age 1년 설정
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(60 * 60 * 24 * 365);

        // 모든 확장자 js인 경우는 no-cache, private 적용
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/static/js/**")
                .addResourceLocations("classpath:/static/js/")
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
