package nextstep.subway.common;

import javax.servlet.Filter;
import nextstep.subway.version.SubwayVersion;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Cache.Cachecontrol;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String PREFIX_STATIC_RESOURCES = "/resources";

    private final SubwayVersion subwayVersion;

    public WebMvcConfig(SubwayVersion subwayVersion) {
        this.subwayVersion = subwayVersion;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        final String DEFAULT_URI = PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion();
        // 확장자는 css인 경우는 max-age를 1년
        registry.addResourceHandler(DEFAULT_URI + "/css/**")
            .addResourceLocations("classpath:/static/css/")
            .setCachePeriod(60 * 60 * 24 * 365);

        //  js인 경우는 no-cache, private 설정
        Cachecontrol jsCacheControl = new Cachecontrol();
        jsCacheControl.setCachePrivate(true);
        jsCacheControl.setNoCache(true);

        registry.addResourceHandler(DEFAULT_URI + "/js/**")
            .addResourceLocations("classpath:/static/js/")
            .setCacheControl(jsCacheControl.toHttpCacheControl());

        //그외 모든 경우는 no-cache, no-store, private을 설정한다.
        Cachecontrol defaultCacheControl = new Cachecontrol();
        defaultCacheControl.setNoCache(true);
        defaultCacheControl.setNoStore(true);
        defaultCacheControl.setCachePrivate(true);

        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/")
            .setCacheControl(defaultCacheControl.toHttpCacheControl());
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
