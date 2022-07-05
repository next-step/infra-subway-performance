package nextstep.subway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Profile("prod")
@EnableCaching
@Configuration
public class CacheConfig extends CachingConfigurerSupport {
    public static final long DEFAULT_EXPIRE_SECONDS = 60;
    public static final String STATION = "station";
    public static final String LINE = "line";

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Bean
    public CacheManager redisCacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                                                                                 .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                                                                                 .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        cacheConfigurations.put(CacheConfig.STATION, RedisCacheConfiguration.defaultCacheConfig()
                                                                            .entryTtl(Duration.ofHours(CacheConfig.DEFAULT_EXPIRE_SECONDS)));
        cacheConfigurations.put(CacheConfig.LINE, RedisCacheConfiguration.defaultCacheConfig()
                                                                            .entryTtl(Duration.ofHours(CacheConfig.DEFAULT_EXPIRE_SECONDS)));

        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
        return redisCacheManager;
    }
}
