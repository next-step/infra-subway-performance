package nextstep.subway.common;

import nextstep.subway.auth.application.AuthService;
import nextstep.subway.auth.ui.AuthenticationPrincipalArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@ComponentScan({"nextstep.subway.auth"})
public class AuthenticationPrincipalConfig implements WebMvcConfigurer {
    private AuthService authService;


    public AuthenticationPrincipalConfig(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void addArgumentResolvers(List argumentResolvers) {
        argumentResolvers.add(createAuthenticationPrincipalArgumentResolver());
    }

    @Bean
    public AuthenticationPrincipalArgumentResolver createAuthenticationPrincipalArgumentResolver() {
        return new AuthenticationPrincipalArgumentResolver(authService);
    }
}
