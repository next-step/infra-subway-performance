package nextstep.subway.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Version {
	private static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";
	private String version;

	@PostConstruct
	public void init() {
		version = nowVersion();
	}

	public String getVersion() {
		return version;
	}

	private static String nowVersion() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
		return LocalDateTime.now().format(formatter);
	}
}
