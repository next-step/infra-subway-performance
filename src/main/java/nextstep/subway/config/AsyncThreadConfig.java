package nextstep.subway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class AsyncThreadConfig {

    @Bean
    public Executor asyncThreadTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /* MaxThread가 동작하는 경우 대기하는 Queue 사이즈 */
        executor.setQueueCapacity(100);
        /* 기본 Thread 사이즈 */
        executor.setCorePoolSize(2);
        /* 최대 Thread 사이즈 */
        executor.setMaxPoolSize(4);

        executor.setAllowCoreThreadTimeOut(true);

        executor.setKeepAliveSeconds(60);

        executor.setThreadNamePrefix("subway-async-");

        return executor;
    }
}
