package nextstep.subway.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncThreadConfig {

    /*
     * 물리 코어 수: 2
     * 물리 CPU 수: 1
     * 리눅스 전체 코어(프로세스) 수: 2
     *
     * 적절한 스레드 수 = 사용 가능한 코어 수 * (1 + 대기 시간/서비스 시간)
     * 즉, 적절한 스레드 수는 사용 가능한 코어 수의 1 ~ 2 배 내 수렴
     */
    @Bean
    public Executor asyncThreadTaskExecutor() {
        ThreadPoolTaskExecutor exexcutor = new ThreadPoolTaskExecutor();

        /* 기본 Thread 사이즈 */
        exexcutor.setCorePoolSize(2); 

        /* 최대 Thread 사이즈 */
        exexcutor.setMaxPoolSize(4); 

        /* MaxThread가 동작하는 경우 대기하는 Queue 사이즈 */
        exexcutor.setQueueCapacity(100);
        exexcutor.setThreadNamePrefix("subway-async-"); 
        return exexcutor; 
    } 
}
