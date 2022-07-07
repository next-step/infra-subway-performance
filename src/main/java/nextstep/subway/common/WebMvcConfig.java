package nextstep.subway.common;

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
        /**미션 1 설정
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache().cachePrivate());
         */
        //미션 2 설정
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES  + "/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(60 * 60 * 24 * 365);
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCacheControl(CacheControl.noCache().cachePrivate());
        /**미션 3 답변
            no-cache와 no-store는 동시에 설정할 수 없다.
            no-cache는 캐시에 저장하면서 서버에 매번 캐시를 확인하지만
            no-store는 캐시를 저장하지 않는다.
         */
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        registration.setFilter(etagHeaderFilter);
        registration.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return registration;
    }
}
