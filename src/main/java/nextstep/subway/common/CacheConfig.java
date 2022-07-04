package nextstep.subway.common;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(fromSerializer(StringRedisSerializer.UTF_8))
                .serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofMinutes(1));
    }

}
