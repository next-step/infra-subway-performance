package nextstep.subway.common;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncThreadConfig {

    private final CustomThreadConfig customThreadConfig;

    public AsyncThreadConfig(final CustomThreadConfig customThreadConfig) {
        this.customThreadConfig = customThreadConfig;
    }

    @Bean
    public Executor asyncThreadTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(customThreadConfig.getCorePoolSize());
        executor.setMaxPoolSize(customThreadConfig.getMaxPoolSize());
        executor.setQueueCapacity(customThreadConfig.getQueueCapacity());
        executor.setThreadNamePrefix(customThreadConfig.getThreadNamePrefix());
        return executor;
    }
}
