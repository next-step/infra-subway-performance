package nextstep.subway.common.infra;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"nextstep.subway"})
public class JpaConfig {
}
