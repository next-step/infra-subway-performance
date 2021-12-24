package nextstep.subway.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncThreadConfig {
    public static final int QUEUE_CAPACITY = 100;
    public static final String THREAD_NAME_PREFIX = "subway-async-";
    public static final int MAX_POOL_SIZE = 4;
    public static final int CORE_POOL_SIZE = 2;

    @Bean
    public Executor asyncThreadTaskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /* 기본 Thread 사이즈 */
        executor.setCorePoolSize(CORE_POOL_SIZE);
        /* 최대 Thread 사이즈 */
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        /* MaxThread가 동작하는 경우 대기하는 Queue 사이즈 */
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        return executor;
    }
}
