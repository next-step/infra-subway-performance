package nextstep.subway.favorite;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.AcceptanceTest;
import nextstep.subway.auth.dto.TokenResponse;
import nextstep.subway.favorite.dto.FavoriteRequest;
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
import java.util.List;
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
    private StationResponse 강남역;
    private StationResponse 양재역;
    private StationResponse 정자역;
    private StationResponse 광교역;

    private TokenResponse 사용자;

    @BeforeEach
    public void setUp() {
        super.setUp();

        강남역 = StationAcceptanceTest.지하철역_등록되어_있음("강남역").as(StationResponse.class);
        양재역 = StationAcceptanceTest.지하철역_등록되어_있음("양재역").as(StationResponse.class);
        정자역 = StationAcceptanceTest.지하철역_등록되어_있음("정자역").as(StationResponse.class);
        광교역 = StationAcceptanceTest.지하철역_등록되어_있음("광교역").as(StationResponse.class);

        Map<String, String> lineCreateParams;
        lineCreateParams = new HashMap<>();
        lineCreateParams.put("name", "신분당선");
        lineCreateParams.put("color", "bg-red-600");
        lineCreateParams.put("upStationId", 강남역.getId() + "");
        lineCreateParams.put("downStationId", 광교역.getId() + "");
        lineCreateParams.put("distance", 10 + "");
        신분당선 = LineAcceptanceTest.지하철_노선_등록되어_있음(lineCreateParams).as(LineResponse.class);

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

        // when
        ExtractableResponse<Response> findResponse = 즐겨찾기_목록_조회_요청(사용자);
        // then
        즐겨찾기_목록_조회됨(findResponse);

        // when
        ExtractableResponse<Response> deleteResponse = 즐겨찾기_삭제_요청(사용자, createResponse);
        // then
        즐겨찾기_삭제됨(deleteResponse);
    }

    @DisplayName("즐겨찾기 페이징을 적용한다.")
    @Test
    void findFavoriteByPage() {
        // given
        StationResponse 잠실역 = StationAcceptanceTest.지하철역_등록되어_있음("잠실역").as(StationResponse.class);
        StationResponse 신천역 = StationAcceptanceTest.지하철역_등록되어_있음("신천역").as(StationResponse.class);
        StationResponse 성내역 = StationAcceptanceTest.지하철역_등록되어_있음("성내역").as(StationResponse.class);
        StationResponse 강변역 = StationAcceptanceTest.지하철역_등록되어_있음("강변역").as(StationResponse.class);
        StationResponse 구의역 = StationAcceptanceTest.지하철역_등록되어_있음("구의역").as(StationResponse.class);
        StationResponse 건대역 = StationAcceptanceTest.지하철역_등록되어_있음("건대역").as(StationResponse.class);
        지하철_노선에_지하철역_등록_요청(신분당선, 정자역, 잠실역, 3);
        지하철_노선에_지하철역_등록_요청(신분당선, 잠실역, 신천역, 3);
        지하철_노선에_지하철역_등록_요청(신분당선, 신천역, 성내역, 3);
        지하철_노선에_지하철역_등록_요청(신분당선, 성내역, 강변역, 3);
        지하철_노선에_지하철역_등록_요청(신분당선, 강변역, 구의역, 3);
        지하철_노선에_지하철역_등록_요청(신분당선, 구의역, 건대역, 3);
        즐겨찾기_생성을_요청(사용자, 정자역, 잠실역);
        즐겨찾기_생성을_요청(사용자, 잠실역, 신천역);
        즐겨찾기_생성을_요청(사용자, 신천역, 성내역);
        즐겨찾기_생성을_요청(사용자, 성내역, 강변역);
        즐겨찾기_생성을_요청(사용자, 강변역, 구의역);
        즐겨찾기_생성을_요청(사용자, 구의역, 건대역);
        // when
        ExtractableResponse<Response> 페이지_2 = 즐겨찾기_목록_조회_요청(사용자, 1);
        // then
        List<FavoriteResponse> favoriteResponses = 페이지_2.jsonPath().getList("", FavoriteResponse.class);
        assertThat(favoriteResponses.size()).isEqualTo(1);
        assertThat(favoriteResponses.get(0).getSource().getName()).isEqualTo("정자역");
        assertThat(favoriteResponses.get(0).getTarget().getName()).isEqualTo("잠실역");
    }

    private ExtractableResponse<Response> 즐겨찾기_목록_조회_요청(TokenResponse tokenResponse, int page) {
        Map<String, Integer> param = new HashMap<>();
        param.put("page", page);
        param.put("size", 5);
        return RestAssured.given().log().all().
                auth().oauth2(tokenResponse.getAccessToken()).
                accept(MediaType.APPLICATION_JSON_VALUE).
                params(param).
                when().
                get("/favorites/").
                then().
                log().all().
                extract();
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
}