package nextstep.subway.config;

import nextstep.support.subwayVersion.SubwayVersion;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Cache.Cachecontrol;
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
    private static final int ONE_YEAR = 60 * 60 * 24 * 365;

    private final SubwayVersion version;

    public WebMvcConfig(SubwayVersion version) {
        this.version = version;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        setCssCacheControl(registry);
        setJsCacheControl(registry);
        setDefaultCacheControl(registry);
    }

    private void setCssCacheControl(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PREFIX_STATIC_RESOURCES  + "/" + version.getVersion() + "/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(ONE_YEAR);
    }

    private void setJsCacheControl(ResourceHandlerRegistry registry) {
        Cachecontrol jsCacheControl = new Cachecontrol();
        jsCacheControl.setCachePrivate(true);
        jsCacheControl.setNoCache(true);

        registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCacheControl(jsCacheControl.toHttpCacheControl());
    }

    private void setDefaultCacheControl(ResourceHandlerRegistry registry) {
        Cachecontrol defaultCacheControl = new Cachecontrol();
        defaultCacheControl.setNoCache(true);
        defaultCacheControl.setNoStore(true);

        registry.addResourceHandler( PREFIX_STATIC_RESOURCES + "/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(defaultCacheControl.toHttpCacheControl());
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
