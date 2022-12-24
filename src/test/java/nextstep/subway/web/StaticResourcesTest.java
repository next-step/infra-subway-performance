package nextstep.subway.web;

import static nextstep.subway.config.WebMvcConfig.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.CacheControl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import nextstep.subway.support.version.SubwayVersion;

@DisplayName("정적 자원 테스트")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourcesTest {

	private static final Logger logger = LoggerFactory.getLogger(StaticResourcesTest.class);

	@Autowired
	private WebTestClient client;

	@Autowired
	private SubwayVersion version;

	@DisplayName("js, css 파일을 제외한 정적 리소스는 no_cache_private를 적용")
	@Test
	void staticResourceExceptJsAndCssTest() {
		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/images/main_logo.png";
		EntityExchangeResult<String> response = client.get().uri(uri)
			.exchange().expectStatus().isOk()
			.expectHeader().cacheControl(CacheControl.noCache().cachePrivate().mustRevalidate())
			.expectBody(String.class).returnResult();

		logger.debug("body : {}", response.getResponseBody());

		String etag = response.getResponseHeaders().getETag();

		client.get().uri(uri)
			.header("If-None-Match", etag)
			.exchange().expectStatus().isNotModified();
	}

	@DisplayName("js 파일은 no_cache 적용")
	@Test
	void noCacheWithJsFilesTest() {
		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
		EntityExchangeResult<String> response = client.get().uri(uri)
			.exchange().expectStatus().isOk()
			.expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
			.expectBody(String.class)
			.returnResult();

		logger.debug("body : {}", response.getResponseBody());

		String etag = response.getResponseHeaders().getETag();

		client
			.get()
			.uri(uri)
			.header("If-None-Match", etag)
			.exchange()
			.expectStatus();
	}

	@DisplayName("css 파일은 max_age 1년 적용")
	@Test
	void oneYearMaxAgeWithCssFileTest() {
		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/test.css";
		EntityExchangeResult<String> response = client.get().uri(uri)
			.exchange().expectStatus().isOk()
			.expectHeader().cacheControl(CacheControl.maxAge(60 * 60 * 24 * 365, TimeUnit.SECONDS))
			.expectBody(String.class).returnResult();

		logger.debug("body : {}", response.getResponseBody());

		String etag = response.getResponseHeaders().getETag();

		client.get().uri(uri)
			.header("If-None-Match", etag)
			.exchange().expectStatus().isNotModified();
	}
}

