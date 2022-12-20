package nextstep.subway.cache;

import static nextstep.subway.config.WebMvcConfig.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import nextstep.subway.config.Version;

@DisplayName("정적 리소스 캐싱 관련 기능 테스트")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticResourceCacheTest {
	private static final Logger logger = LoggerFactory.getLogger(StaticResourceCacheTest.class);
	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private Version version;

	@DisplayName("js 파일의 cache-control 설정(no-cache, private) 검증")
	@Test
	void js_resource_cache_test() {

		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";

		EntityExchangeResult<String> response = webTestClient.get()
			.uri(uri)
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK.value())
			.expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
			.expectBody(String.class)
			.returnResult();

		addResponseLog(response);
		checkCache(uri, getEtag(response));

	}

	@DisplayName("css 파일의 cache-control 설정(MAX-AGE 1년) 검증")
	@Test
	void css_resource_cache_test() {

		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/main.css";

		EntityExchangeResult<String> response = webTestClient.get()
			.uri(uri)
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK.value())
			.expectHeader().cacheControl(CacheControl.maxAge(CSS_MAX_ONE_YEAR, TimeUnit.SECONDS))
			.expectBody(String.class)
			.returnResult();

		addResponseLog(response);
		checkCache(uri, getEtag(response));

	}

	@DisplayName("static 경로 내의 css 제외 다른 파일들의 cache-control 설정(no-cache, private) 검증")
	@Test
	void other_resource_cache_test() {

		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() +"/images/main_logo.png";

		EntityExchangeResult<String> response = webTestClient.get()
			.uri(uri)
			.exchange()
			.expectStatus().isEqualTo(HttpStatus.OK.value())
			.expectHeader().cacheControl(CacheControl.noCache().cachePrivate())
			.expectBody(String.class)
			.returnResult();

		addResponseLog(response);
		checkCache(uri, getEtag(response));

	}
	private void checkCache(String uri, String etag){
		webTestClient.get()
			.uri(uri)
			.header("If-None-Match", etag)
			.exchange()
			.expectStatus()
			.isNotModified();
	}

	private void addResponseLog(EntityExchangeResult<String> response){
		logger.debug("body : {}", response.getResponseBody());
	}
	private String getEtag(EntityExchangeResult<String> response){
		return response.getResponseHeaders().getETag();
	}
}
