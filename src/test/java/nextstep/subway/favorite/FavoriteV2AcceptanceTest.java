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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static nextstep.subway.auth.acceptance.AuthAcceptanceTest.로그인_되어_있음;
import static nextstep.subway.auth.acceptance.AuthAcceptanceTest.회원_등록되어_있음;
import static nextstep.subway.line.acceptance.LineSectionAcceptanceTest.지하철_노선에_지하철역_등록_요청;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("즐겨찾기 관련 기능")
public class FavoriteV2AcceptanceTest extends AcceptanceTest {
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

    @DisplayName("즐겨찾기를 목록 페이지 처리 확인")
    @Test
    void favoriteList() {
        // when
        즐겨찾기_생성을_요청(사용자, 강남역, 양재역);
        즐겨찾기_생성을_요청(사용자, 강남역, 정자역);
        즐겨찾기_생성을_요청(사용자, 강남역, 광교역);
        즐겨찾기_생성을_요청(사용자, 양재역, 강남역);

        ExtractableResponse<Response> 즐겨찾기_양재_정자 = 즐겨찾기_생성을_요청(사용자, 양재역, 정자역);
        String 즐겨찾기_양재_정자_아이디 = 즐겨찾기_아이디_추출(즐겨찾기_양재_정자);
        ExtractableResponse<Response> 즐겨찾기_양재_광교 = 즐겨찾기_생성을_요청(사용자, 양재역, 광교역);
        String 즐겨찾기_양재_광교_아이디 = 즐겨찾기_아이디_추출(즐겨찾기_양재_광교);
        ExtractableResponse<Response> 즐겨찾기_광교_강남 = 즐겨찾기_생성을_요청(사용자, 광교역, 강남역);
        String 즐겨찾기_광교_강남_아이디 = 즐겨찾기_아이디_추출(즐겨찾기_광교_강남);
        ExtractableResponse<Response> 즐겨찾기_광교_양제 = 즐겨찾기_생성을_요청(사용자, 광교역, 양재역);
        String 즐겨찾기_광교_양재_아이디 = 즐겨찾기_아이디_추출(즐겨찾기_광교_양제);
        ExtractableResponse<Response> 즐겨찾기_광교_정자 = 즐겨찾기_생성을_요청(사용자, 광교역, 정자역);
        String 즐겨찾기_광교_정자_아이디 = 즐겨찾기_아이디_추출(즐겨찾기_광교_정자);

        // when
        ExtractableResponse<Response> findResponse = 즐겨찾기_페이징처리된_목록_조회_요청(사용자);
        // then
        즐겨찾기_페이징처리된_목록_조회됨(findResponse,
                Arrays.asList(
                        즐겨찾기_광교_정자_아이디,
                        즐겨찾기_광교_양재_아이디,
                        즐겨찾기_광교_강남_아이디,
                        즐겨찾기_양재_광교_아이디,
                        즐겨찾기_양재_정자_아이디
                ));

    }

    public static String 즐겨찾기_아이디_추출(ExtractableResponse<Response> response) {
        return response.header("Location").split("/")[2];
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

    public static ExtractableResponse<Response> 즐겨찾기_페이징처리된_목록_조회_요청(TokenResponse tokenResponse) {
        return RestAssured.given().log().all().
                auth().oauth2(tokenResponse.getAccessToken()).
                accept(MediaType.APPLICATION_JSON_VALUE).
                when().
                get("/favorites-v2").
                then().
                log().all().
                extract();
    }

    public static void 즐겨찾기_페이징처리된_목록_조회됨(ExtractableResponse<Response> response, List<String> favoriteIds) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        List<Long> content = response.jsonPath().getList("content.id", Long.class);
        List<Long> transFavoiteIds = favoriteIds.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());
        assertThat(content).containsAll(transFavoiteIds);
    }

}
