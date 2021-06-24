package nextstep.subway.map;

import nextstep.subway.map.application.MapService;
import nextstep.subway.map.dto.PathResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MapServiceTest {

    @Autowired
    MapService mapService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() {
        // given
        String key = "path::3,106";
        Long source = 3L;
        Long target = 106L;

        // when
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        PathResponse pathResponse = mapService.findPath(source, target);
        valueOperations.set(key, pathResponse);

        PathResponse findPathResponse = (PathResponse) valueOperations.get(key);

        // then
        assertAll(
                () -> assertThat(findPathResponse.getDistance()).isSameAs(pathResponse.getDistance())
        );
    }
}
