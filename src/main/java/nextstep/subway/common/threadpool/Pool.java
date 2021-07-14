package nextstep.subway.common.threadpool;

import java.time.Duration;

public class Pool {
    private int queueCapacity = 2147483647;
    private int coreSize = 8;
    private int maxSize = 2147483647;
    private boolean allowCoreThreadTimeout = true;
    private Duration keepAlive = Duration.ofSeconds(60L);
}
