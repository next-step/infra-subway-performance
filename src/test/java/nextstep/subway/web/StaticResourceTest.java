package nextstep.subway.web;

import static nextstep.subway.web.WebMvcConfig.PREFIX_STATIC_RESOURCES;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.CacheControl;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import nextstep.subway.support.version.SubwayVersion;

@DisplayName("정적 리소스 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourceTest {
	@Autowired
	private WebTestClient client;

	@Autowired
	private SubwayVersion version;

	@DisplayName("css 캐시는 1년간 유지됨")
	@Test
	void testCssCacheControl() {
		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/app.css";

		EntityExchangeResult<String> response = client
			.get()
			.uri(uri)
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.cacheControl(CacheControl.maxAge(Duration.ofDays(365).getSeconds(), TimeUnit.SECONDS))
			.expectBody(String.class)
			.returnResult();

		String etag = response.getResponseHeaders()
							  .getETag();

		client
			.get()
			.uri(uri)
			.header(HttpHeaders.IF_NONE_MATCH, etag)
			.exchange()
			.expectStatus()
			.isNotModified();
	}

	@DisplayName("js 파일은 no-cache 와 private 이 적용됨")
	@Test
	void testJavascriptCacheControl() {
		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
		EntityExchangeResult<String> response = client.get()
													  .uri(uri)
													  .exchange()
													  .expectStatus()
													  .isOk()
													  .expectHeader()
													  .cacheControl(CacheControl.noCache()
																				.cachePrivate())
													  .expectBody(String.class)
													  .returnResult();

		String etag = response.getResponseHeaders().getETag();

		client.get().uri(uri)
			  .header(HttpHeaders.IF_NONE_MATCH, etag)
			  .exchange().expectStatus().isNotModified();
	}

	@DisplayName("CSS와 JS 파일 이외의 정적 리소스는 no-cache 와 private 이 적용됨")
	@Test
	void testExtraResourcesCacheControl() {
		String uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/static/images/main_logo.png";

		EntityExchangeResult<String> response = client.get()
													  .uri(uri)
													  .exchange()
													  .expectStatus()
													  .isOk()
													  .expectHeader()
													  .cacheControl(CacheControl.noCache()
																				.cachePrivate())
													  .expectBody(String.class)
													  .returnResult();

		String etag = response.getResponseHeaders().getETag();

		client.get().uri(uri)
			  .header(HttpHeaders.IF_NONE_MATCH, etag)
			  .exchange().expectStatus().isNotModified();
	}

	@DisplayName("정적 파일이 아닌 경우 캐시 적용되지 않음")
	@Test
	void nonStaticRequestCacheControl() {
		EntityExchangeResult<String> response = client
			.get()
			.uri("/lines")
			.exchange()
			.expectStatus()
			.isOk()
			.expectHeader()
			.cacheControl(CacheControl.empty())
			.expectBody(String.class)
			.returnResult();

		String etag = response
			.getResponseHeaders()
			.getETag();

		assertThat(etag).isNull();
	}

}
