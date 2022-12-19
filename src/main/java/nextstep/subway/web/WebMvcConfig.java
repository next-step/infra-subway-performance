package nextstep.subway.web;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import nextstep.subway.support.version.SubwayVersion;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	public static final String PREFIX_STATIC_RESOURCES = "/resources";

	private final SubwayVersion version;

	public WebMvcConfig(SubwayVersion version) {
		this.version = version;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/**")
				.addResourceLocations("classpath:/static")
				.setCacheControl(CacheControl.noCache().cachePrivate());

		registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/**")
				.addResourceLocations("classpath:/static/js/")
				.setCacheControl(CacheControl.noCache().cachePrivate());

		registry.addResourceHandler(PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/**")
				.addResourceLocations("classpath:/static/css/")
				.setCachePeriod(60 * 60 * 24 * 365);
	}

	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
		registration.setFilter(new ShallowEtagHeaderFilter());
		registration.addUrlPatterns(PREFIX_STATIC_RESOURCES + "/*");
		return registration;
	}
}
