package nextstep.subway.common;

import javax.servlet.Filter;
import nextstep.subway.support.SubwayVersion;
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
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry
            .addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + subwayVersion.getVersion() + "/**")
            .addResourceLocations("classpath:/static/")
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
