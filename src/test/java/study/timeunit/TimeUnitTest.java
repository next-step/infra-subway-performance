package study.timeunit;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class TimeUnitTest {

    @Test
    void convert() {
        int actual = (int)TimeUnit.SECONDS.convert(365, TimeUnit.DAYS);
        int expected = 60 * 60 * 24 * 365;

        assertThat(actual).isEqualTo(expected);
    }
}
