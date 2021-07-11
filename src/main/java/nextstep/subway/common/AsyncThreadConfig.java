package nextstep.subway.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@Profile("prod")
@EnableAsync
public class AsyncThreadConfig {

    @Bean
    public Executor asyncThreadTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); // 기본 스레드 사이즈
        executor.setMaxPoolSize(4);  // 최대 스레드 사이즈
        executor.setQueueCapacity(100); // 스레드가 전부 동작하는 경우 대기하는 큐(레디큐) 사이즈
        executor.setThreadNamePrefix("subway-async-");
        return executor;
    }
}