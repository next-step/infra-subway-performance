package nextstep.subway.common;
import nextstep.subway.common.support.SubwayVersionSupport;
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
    private static final String CLASS_PATH_STATIC = "classpath:/static/";

    private final SubwayVersionSupport subwayVersionSupport;

    public WebMvcConfig(SubwayVersionSupport subwayVersionSupport) {
        this.subwayVersionSupport = subwayVersionSupport;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersionSupport.getVersion() + "/**")
                .addResourceLocations(CLASS_PATH_STATIC)
                .setCacheControl(CacheControl.noCache().cachePrivate());

        resourceHandlerRegistry.addResourceHandler(
                        PREFIX_STATIC_RESOURCES + "/" + subwayVersionSupport.getVersion() + "/js/*.js")
                .addResourceLocations(CLASS_PATH_STATIC + "js/")
                .setCacheControl(CacheControl.noCache().cachePrivate());

        resourceHandlerRegistry.addResourceHandler(
                        PREFIX_STATIC_RESOURCES + "/" + subwayVersionSupport.getVersion() + "/css/*.css")
                .addResourceLocations(CLASS_PATH_STATIC + "css/")
                .setCachePeriod(60 * 60 * 24 * 365);
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
