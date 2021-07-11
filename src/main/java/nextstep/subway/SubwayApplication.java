package nextstep.subway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"nextstep.subway"})
@SpringBootApplication
public class SubwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubwayApplication.class, args);
    }

}
