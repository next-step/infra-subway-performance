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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private StationResponse 판교역;
    private StationResponse 정자역;
    private StationResponse 미금역;
    private StationResponse 동천역;
    private StationResponse 광교역;

    private TokenResponse 사용자;

    @BeforeEach
    public void setUp() {
        super.setUp();

        강남역 = StationAcceptanceTest.지하철역_등록되어_있음("강남역").as(StationResponse.class);
        양재역 = StationAcceptanceTest.지하철역_등록되어_있음("양재역").as(StationResponse.class);
        판교역 = StationAcceptanceTest.지하철역_등록되어_있음("판교역").as(StationResponse.class);
        정자역 = StationAcceptanceTest.지하철역_등록되어_있음("정자역").as(StationResponse.class);
        미금역 = StationAcceptanceTest.지하철역_등록되어_있음("미금역").as(StationResponse.class);
        동천역 = StationAcceptanceTest.지하철역_등록되어_있음("동천역").as(StationResponse.class);
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
    void manageFavorite() {
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

    @DisplayName("즐겨찾기 5개 가져오기")
    @Test
    void selectFiveFavorite() {
        // given
        즐겨찾기_생성_되어_있음(사용자, 강남역, 양재역);
        즐겨찾기_생성_되어_있음(사용자, 양재역, 판교역);
        즐겨찾기_생성_되어_있음(사용자, 판교역, 정자역);
        즐겨찾기_생성_되어_있음(사용자, 정자역, 미금역);
        즐겨찾기_생성_되어_있음(사용자, 미금역, 동천역);
        즐겨찾기_생성_되어_있음(사용자, 동천역, 광교역);
        // when
        ExtractableResponse<Response> findResponse = 즐겨찾기_목록_조회_요청(사용자);
        // then
        즐겨찾기_목록_다섯개_조회됨(findResponse);
    }


    @DisplayName("즐겨찾기 하나 가져오기 - Paging")
    @Test
    void selectPageFavorites() {
        // given
        즐겨찾기_생성_되어_있음(사용자, 강남역, 양재역);
        즐겨찾기_생성_되어_있음(사용자, 양재역, 판교역);
        즐겨찾기_생성_되어_있음(사용자, 판교역, 정자역);
        즐겨찾기_생성_되어_있음(사용자, 정자역, 미금역);
        즐겨찾기_생성_되어_있음(사용자, 미금역, 동천역);
        즐겨찾기_생성_되어_있음(사용자, 동천역, 광교역);
        // when
        ExtractableResponse<Response> findResponse = 즐겨찾기_목록_조회_원하는_페이지_요청(사용자, 2L);
        // then
        즐겨찾기_목록_하나_조회됨(findResponse);
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

    public static ExtractableResponse<Response> 즐겨찾기_목록_조회_원하는_페이지_요청(TokenResponse tokenResponse, Long lastIndex) {
        return RestAssured.given().log().all().
                auth().oauth2(tokenResponse.getAccessToken()).
                accept(MediaType.APPLICATION_JSON_VALUE).
                when().
                get("/favorites?lastIndex={lastIndex}", lastIndex).
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

    public static String 즐겨찾기_생성_되어_있음(TokenResponse tokenResponse, StationResponse source, StationResponse target) {
        ExtractableResponse<Response> response = 즐겨찾기_생성을_요청(tokenResponse, source, target);
        즐겨찾기_생성됨(response);
        return response.header("Location").split("/")[2];
    }

    private void 즐겨찾기_목록_하나_조회됨(ExtractableResponse<Response> response) {
        List<Long> responseIds = responseIds(response);
        assertThat(responseIds.size()).isEqualTo(1);
        즐겨찾기_목록_조회됨(response);
    }

    private void 즐겨찾기_목록_다섯개_조회됨(ExtractableResponse<Response> response) {
        List<Long> responseIds = responseIds(response);
        assertThat(responseIds.size()).isEqualTo(5);
        즐겨찾기_목록_조회됨(response);
    }

    public static void 즐겨찾기_목록_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 즐겨찾기_삭제됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    private List<Long> responseIds(ExtractableResponse<Response> response) {
        return response.jsonPath().getList(".", FavoriteResponse.class)
                .stream()
                .map(FavoriteResponse::getId)
                .collect(Collectors.toList());
    }
}