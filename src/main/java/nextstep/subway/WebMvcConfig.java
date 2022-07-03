package nextstep.subway;

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
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Cachecontrol defaultCacheControl = new Cachecontrol();
        defaultCacheControl.setNoCache(true);
        defaultCacheControl.setNoStore(true);
        defaultCacheControl.setCachePrivate(true);

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(defaultCacheControl.toHttpCacheControl());

        registry.addResourceHandler("/**/*.css")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(60 * 60 * 24 * 365);

        Cachecontrol jsCacheControl = new Cachecontrol();
        jsCacheControl.setCachePrivate(true);
        jsCacheControl.setNoCache(true);

        registry.addResourceHandler("/**/*.js")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(jsCacheControl.toHttpCacheControl());

    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter etagHeaderFilter = new ShallowEtagHeaderFilter();
        registration.setFilter(etagHeaderFilter);
        registration.addUrlPatterns("*.css");
        return registration;
    }
}
