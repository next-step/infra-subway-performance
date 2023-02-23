package nextstep.subway.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.sun.tools.javac.util.List;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    private long refreshTime = 300L;

    @Bean
    public CacheManager centralCache() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = List.of(caffeineCache());
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    private CaffeineCache caffeineCache() {
        return new CaffeineCache("caffeineCache", Caffeine.newBuilder()
                .recordStats()
                .expireAfterWrite(refreshTime, TimeUnit.SECONDS)
                .build());
    }
}
