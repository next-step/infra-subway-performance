package nextstep.subway.common.support;

import nextstep.subway.support.version.SubwayVersionSupport;
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

    public static final String PREFIX_STATIC_RESOURCES = "/resources";
    private static final String CLASS_PATH_STATIC = "classpath:/static/";

    private final SubwayVersionSupport subwayVersionSupport;

    public WebMvcConfig(SubwayVersionSupport subwayVersionSupport) {
        this.subwayVersionSupport = subwayVersionSupport;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersionSupport.getVersion() + "/static/js/**")
            .addResourceLocations("classpath:/static/js/")
            .setCacheControl(CacheControl.noCache().cachePrivate());

        resourceHandlerRegistry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersionSupport.getVersion() + "/static/**")
            .addResourceLocations("classpath:/static/")
            .setCacheControl(CacheControl.noStore().mustRevalidate());

        resourceHandlerRegistry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersionSupport.getVersion() + "/static/css/**")
            .addResourceLocations("classpath:/static/css/")
            .setCachePeriod(60 * 60 * 24 * 365);


        resourceHandlerRegistry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersionSupport.getVersion() + "/static/images/**")
            .addResourceLocations("classpath:/static/images/")
            .setCacheControl(CacheControl.noCache().cachePrivate());
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        filterRegistrationBean.setFilter(etagHeaderFilter);
        filterRegistrationBean.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return filterRegistrationBean;
    }
}

