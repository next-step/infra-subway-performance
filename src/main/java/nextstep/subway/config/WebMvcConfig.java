package nextstep.subway.config;

import nextstep.subway.support.ResourceVersion;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.time.Duration;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final String PREFIX_STATIC_RESOURCES = "/resources";

    private final ResourceVersion version;

    public WebMvcConfig(ResourceVersion version) {
        this.version = version;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry
                .addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noCache().cachePrivate());
        resourceHandlerRegistry
                .addResourceHandler(
                        PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/*.js")
                .addResourceLocations("classpath:/static/js/")
                .setCacheControl(CacheControl.noCache().cachePrivate());
        resourceHandlerRegistry.
                addResourceHandler(
                        PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/*.css")
                .addResourceLocations("classpath:/static/css/")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        filterRegistrationBean.setFilter(etagHeaderFilter);
        filterRegistrationBean.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return filterRegistrationBean;
    }
}
