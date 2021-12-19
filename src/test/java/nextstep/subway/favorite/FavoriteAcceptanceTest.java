package nextstep.subway.favorite;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.AcceptanceTest;
import nextstep.subway.auth.dto.TokenResponse;
import nextstep.subway.favorite.dto.FavoriteResponse;
import nextstep.subway.line.acceptance.LineAcceptanceTest;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.station.StationAcceptanceTest;
import nextstep.subway.station.dto.StationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static nextstep.subway.auth.acceptance.AuthAcceptanceTest.로그인_되어_있음;
import static nextstep.subway.auth.acceptance.AuthAcceptanceTest.회원_등록되어_있음;
import static nextstep.subway.line.acceptance.LineSectionAcceptanceTest.지하철_노선에_지하철역_등록_요청;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("즐겨찾기 관련 기능")
public class FavoriteAcceptanceTest extends AcceptanceTest {
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "password";

    private LineResponse 신분당선;
    private LineResponse 구호선;
    private LineResponse 일호선;
    private StationResponse 강남역;
    private StationResponse 양재역;
    private StationResponse 정자역;
    private StationResponse 광교역;
    private StationResponse 당산역;
    private StationResponse 선유도역;
    private StationResponse 영등포역;
    private StationResponse 시청역;

    private TokenResponse 사용자;

    @BeforeEach
    public void setUp() {
        super.setUp();

        강남역 = StationAcceptanceTest.지하철역_등록되어_있음("강남역").as(StationResponse.class);
        양재역 = StationAcceptanceTest.지하철역_등록되어_있음("양재역").as(StationResponse.class);
        정자역 = StationAcceptanceTest.지하철역_등록되어_있음("정자역").as(StationResponse.class);
        광교역 = StationAcceptanceTest.지하철역_등록되어_있음("광교역").as(StationResponse.class);
        당산역 = StationAcceptanceTest.지하철역_등록되어_있음("당산역").as(StationResponse.class);
        선유도역 = StationAcceptanceTest.지하철역_등록되어_있음("선유도역").as(StationResponse.class);
        영등포역 = StationAcceptanceTest.지하철역_등록되어_있음("영등포역").as(StationResponse.class);
        시청역 = StationAcceptanceTest.지하철역_등록되어_있음("시청역").as(StationResponse.class);

        신분당선 = 신분당선_지하철노선등록();
        구호선 = 구호선_지하철노선등록();
        일호선 = 일호선_지하철노선등록();

        지하철_노선에_지하철역_등록_요청(신분당선, 강남역, 양재역, 3);
        지하철_노선에_지하철역_등록_요청(신분당선, 양재역, 정자역, 3);

        회원_등록되어_있음(EMAIL, PASSWORD, 20);
        사용자 = 로그인_되어_있음(EMAIL, PASSWORD);
    }


    @DisplayName("즐겨찾기를 관리한다.")
    @Test
    void manageMember() {
        // when
        ExtractableResponse<Response> createResponse = 즐겨찾기_생성을_요청(사용자, 강남역, 정자역);
        // then
        즐겨찾기_생성됨(createResponse);

        즐겨찾기_생성을_요청(사용자, 당산역, 선유도역);
        즐겨찾기_생성을_요청(사용자, 시청역, 영등포역);
        즐겨찾기_생성을_요청(사용자, 양재역, 정자역);
        즐겨찾기_생성을_요청(사용자, 강남역, 양재역);
        즐겨찾기_생성을_요청(사용자, 강남역, 광교역);

        // when
        ExtractableResponse<Response> findResponse = 즐겨찾기_목록_조회_요청(사용자);
        // then
        즐겨찾기_목록_조회됨(findResponse);

        // when
        ExtractableResponse<Response> deleteResponse = 즐겨찾기_삭제_요청(사용자, createResponse);
        // then
        즐겨찾기_삭제됨(deleteResponse);
    }

    @DisplayName("페이징 즐겨찾기 조회 결과값 : " +
            "(id: 3 , 시청역, 영등포역)" +
            "(id: 2 , 당산역, 선유도역)" +
            "(id: 1 , 강남역, 정자역)"
    )
    @Test
    void pageSeelctTest() {
        // when
        즐겨찾기_생성을_요청(사용자, 강남역, 정자역);
        즐겨찾기_생성을_요청(사용자, 당산역, 선유도역);
        즐겨찾기_생성을_요청(사용자, 시청역, 영등포역);

        즐겨찾기_생성을_요청(사용자, 양재역, 정자역);
        즐겨찾기_생성을_요청(사용자, 강남역, 양재역);
        즐겨찾기_생성을_요청(사용자, 강남역, 광교역);

        // when
        ExtractableResponse<Response> findResponse = 즐겨찾기_목록_페이징_조회_요청(사용자, 1);
        // then
        즐겨찾기_목록_조회됨(findResponse);
    }


    public static ExtractableResponse<Response> 즐겨찾기_생성을_요청(TokenResponse tokenResponse, StationResponse source, StationResponse target) {
        Map<String, String> params = new HashMap<>();
        params.put("source", source.getId() + "");
        params.put("target", target.getId() + "");

        return RestAssured.given().log().all().
                auth().oauth2(tokenResponse.getAccessToken()).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                body(params).
                when().
                post("/favorites").
                then().
                log().all().
                extract();
    }

    public static ExtractableResponse<Response> 즐겨찾기_목록_조회_요청(TokenResponse tokenResponse) {
        return RestAssured.given().log().all().
                auth().oauth2(tokenResponse.getAccessToken()).
                accept(MediaType.APPLICATION_JSON_VALUE).
                when().
                get("/favorites").
                then().
                log().all().
                extract();
    }

    public static ExtractableResponse<Response> 즐겨찾기_목록_페이징_조회_요청(TokenResponse tokenResponse, int pageNumber) {
        return RestAssured.given().log().all().
                auth().oauth2(tokenResponse.getAccessToken()).
                accept(MediaType.APPLICATION_JSON_VALUE).
                when().
                get("/favorites?page={pageNumber}", pageNumber).
                then().
                log().all().
                extract();
        ///paths?source={sourceId}&target={targetId}", source, target).
    }

    public static ExtractableResponse<Response> 즐겨찾기_삭제_요청(TokenResponse tokenResponse, ExtractableResponse<Response> response) {
        String uri = response.header("Location");

        return RestAssured.given().log().all().
                auth().oauth2(tokenResponse.getAccessToken()).
                when().
                delete(uri).
                then().
                log().all().
                extract();
    }

    public static void 즐겨찾기_생성됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    public static void 즐겨찾기_목록_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 즐겨찾기_삭제됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    private LineResponse 일호선_지하철노선등록() {
        Map<String, String> lineCreateParams = new HashMap<>();
        lineCreateParams.put("name", "일호선");
        lineCreateParams.put("color", "bg-red-602");
        lineCreateParams.put("upStationId", 시청역.getId() + "");
        lineCreateParams.put("downStationId", 영등포역.getId() + "");
        lineCreateParams.put("distance", 30 + "");
        return LineAcceptanceTest.지하철_노선_등록되어_있음(lineCreateParams).as(LineResponse.class);
    }

    private LineResponse 구호선_지하철노선등록() {
        Map<String, String> lineCreateParams = new HashMap<>();
        lineCreateParams.put("name", "구호선");
        lineCreateParams.put("color", "bg-red-601");
        lineCreateParams.put("upStationId", 당산역.getId() + "");
        lineCreateParams.put("downStationId", 선유도역.getId() + "");
        lineCreateParams.put("distance", 20 + "");
        return LineAcceptanceTest.지하철_노선_등록되어_있음(lineCreateParams).as(LineResponse.class);
    }

    private LineResponse 신분당선_지하철노선등록() {
        Map<String, String> lineCreateParams = new HashMap<>();
        lineCreateParams.put("name", "신분당선");
        lineCreateParams.put("color", "bg-red-600");
        lineCreateParams.put("upStationId", 강남역.getId() + "");
        lineCreateParams.put("downStationId", 광교역.getId() + "");
        lineCreateParams.put("distance", 10 + "");
        return LineAcceptanceTest.지하철_노선_등록되어_있음(lineCreateParams).as(LineResponse.class);
    }
}
