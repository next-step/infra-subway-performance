package nextstep.subway;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.support.version.SubwayVersion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@DisplayName("정적 리소스 관련 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StaticResourceTest {

    private static final String PREFIX_STATIC_RESOURCES = "/resources";

    @LocalServerPort
    int port;

    @Autowired
    private SubwayVersion version;

    @BeforeEach
    public void setUp() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }

    }

    @DisplayName("정적리소스(js) 조회 테스트")
    @Test
    void get_static_resources_js() {
        //given
        String main_uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/main.js";
        String vendors_uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/js/vendors.js";

        //when
        ExtractableResponse<Response> main1 = NO_CACHE_조회하기(main_uri);
        ExtractableResponse<Response> vendor1 = NO_CACHE_조회하기(vendors_uri);
        String mainEtag = main1.header("etag");
        String vendorEtag = vendor1.header("etag");

        //then
        assertThat(mainEtag).isNotBlank();
        assertThat(vendorEtag).isNotBlank();

        //when
        ExtractableResponse<Response> main2 = NONE_MATCH_조회하기(main_uri, mainEtag);
        ExtractableResponse<Response> vendor2 = NONE_MATCH_조회하기(vendors_uri, vendorEtag);

        //then
        assertThat(main2.statusCode()).isEqualTo(HttpStatus.NOT_MODIFIED.value());
        assertThat(vendor2.statusCode()).isEqualTo(HttpStatus.NOT_MODIFIED.value());
    }

    @DisplayName("정적리소스(img) 조회 테스트")
    @Test
    void get_static_resources_img() {
        //given
        String logo_small_uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/logo_small.png";
        String main_logo_uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/images/main_logo.png";

        //when
        String logo_small_etag = NO_CACHE_조회하기(logo_small_uri).header("etag");
        String main_logo_etag = NO_CACHE_조회하기(main_logo_uri).header("etag");

        //then
        assertThat(logo_small_etag).isNotBlank();
        assertThat(main_logo_etag).isNotBlank();

        //when
        ExtractableResponse<Response> logo_small = NONE_MATCH_조회하기(logo_small_uri, logo_small_etag);
        ExtractableResponse<Response> main_logo = NONE_MATCH_조회하기(main_logo_uri, main_logo_etag);

        //then
        assertThat(logo_small.statusCode()).isEqualTo(HttpStatus.NOT_MODIFIED.value());
        assertThat(main_logo.statusCode()).isEqualTo(HttpStatus.NOT_MODIFIED.value());
    }

    @DisplayName("정적리소스(css) 조회 테스트")
    @Test
    void get_static_resources_css() {
        //given
        String test_css_uri = PREFIX_STATIC_RESOURCES + "/" + version.getVersion() + "/css/test.css";

        //when
        String test_css_etag = NO_CACHE_조회하기(test_css_uri).header("etag");

        //then
        assertThat(test_css_etag).isNotBlank();

        //when
        ExtractableResponse<Response> test_css = NONE_MATCH_조회하기(test_css_uri, test_css_etag);
        String cacheControl = test_css.header("Cache-Control");

        //then
        assertThat(test_css.statusCode()).isEqualTo(HttpStatus.NOT_MODIFIED.value());
        assertThat(cacheControl).isEqualTo("max-age=31536000");


    }


    private ExtractableResponse<Response> NONE_MATCH_조회하기(String uri, String etag) {
        return RestAssured
                .given().header("If-None-Match", etag)
                .log().headers()
                .when().get(uri)
                .then()
                .log().all().extract();
    }

    private ExtractableResponse<Response> NO_CACHE_조회하기(String uri) {
        return RestAssured
                .given().header("Cache-Control", "no-cache, private")
                .log().headers()
                .when().get(uri)
                .then().log().headers()
                .extract();
    }

}
