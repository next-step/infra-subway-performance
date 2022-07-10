package nextstep.subway.config;

import java.util.concurrent.TimeUnit;
import javax.servlet.Filter;

import nextstep.subway.support.version.SubwayVersion;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SubwayVersion subwayVersion;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //모든 정적 자원에 대해 no-cache, private 설정
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noStore().mustRevalidate());
        
        //확장자는 css 인 경우는 max-age 를 1년
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/static/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        //js 는 no-cache, private
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/static/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCacheControl(CacheControl.noCache().cachePrivate());

        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "static/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCacheControl(CacheControl.noCache().cachePrivate());
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        registrationBean.setFilter(etagHeaderFilter);
        registrationBean.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return registrationBean;
    }
}
