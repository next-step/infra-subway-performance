package nextstep.subway.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class AsyncThreadConfig {
    private static final String THREAD_NAME_PREFIX = "subway-async-";
    private static final int BASIC_THREAD_SIZE = 2;
    private static final int MAX_THREAD_SIZE = 4;
    private static final int MAX_THREAD_QUEUE_SIZE = 100;

    @Bean
    public Executor asyncThreadTaskExecutor() {
        ThreadPoolTaskExecutor exexcutor = new ThreadPoolTaskExecutor();
        exexcutor.setCorePoolSize(BASIC_THREAD_SIZE);
        exexcutor.setMaxPoolSize(MAX_THREAD_SIZE);
        exexcutor.setQueueCapacity(MAX_THREAD_QUEUE_SIZE);
        exexcutor.setThreadNamePrefix(THREAD_NAME_PREFIX);

        return exexcutor;
    }

}

