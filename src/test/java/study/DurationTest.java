package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class DurationTest {

	@Test
	void testDaysToSeconds() {
		assertThat(Duration.ofDays(365).getSeconds()).isEqualTo(60 * 60 * 24 * 365);
	}
}
