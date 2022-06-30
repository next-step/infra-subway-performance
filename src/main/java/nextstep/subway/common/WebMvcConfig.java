package nextstep.subway.common;

import java.util.concurrent.TimeUnit;
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
    public static final String STATIC_PATH = "classpath:/static/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        settingStaticNoCachingAndPrivate(registry);
        settingCssCachingForOneYear(registry);
        settingJsNoCachingAndPrivate(registry);
    }

    private void settingJsNoCachingAndPrivate(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/**.js")
                .addResourceLocations(STATIC_PATH)
                .setCacheControl(CacheControl.noCache().cachePrivate());
    }

    private void settingCssCachingForOneYear(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/**.css")
                .addResourceLocations(STATIC_PATH)
                .setCacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS));
    }

    private void settingStaticNoCachingAndPrivate(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("**/static/**")
                .addResourceLocations(STATIC_PATH)
                .setCacheControl(CacheControl.noCache().cachePrivate());
    }

    @Bean
    public FilterRegistrationBean<Filter> shallowEtagHeaderFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        filterRegistrationBean.setFilter(etagHeaderFilter);
        filterRegistrationBean.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
        return filterRegistrationBean;
    }

}
