//package nextstep.subway.common;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Value("${spring.redis.port}")
//    public int port;
//
//    @Value("${spring.redis.host}")
//    public String host;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(host, port);
//        return connectionFactory;
//    }
//
//}
