package nextstep.subway.station;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.AcceptanceTest;
import nextstep.subway.station.dto.StationResponse;
import nextstep.subway.utils.ExpectedPageResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("지하철역 관련 기능")
public class StationAcceptanceTest extends AcceptanceTest {
    private static final String 강남역 = "강남역";
    private static final String 역삼역 = "역삼역";
    private static final int STATIONS_COUNT = 175;

    @DisplayName("지하철역을 생성한다.")
    @Test
    void createStation() {
        // when
        ExtractableResponse<Response> response = 지하철역_생성_요청(강남역);

        // then
        지하철역_생성됨(response);
    }

    @DisplayName("기존에 존재하는 지하철역 이름으로 지하철역을 생성한다.")
    @Test
    void createStationWithDuplicateName() {
        //given
        지하철역_등록되어_있음(강남역);

        // when
        ExtractableResponse<Response> response = 지하철역_생성_요청(강남역);

        // then
        지하철역_생성_실패됨(response);
    }

    @DisplayName("지하철역을 조회한다.")
    @Test
    void getStations() {
        // given
        ExtractableResponse<Response> createResponse1 = 지하철역_등록되어_있음(강남역);
        ExtractableResponse<Response> createResponse2 = 지하철역_등록되어_있음(역삼역);

        // when
        ExtractableResponse<Response> response = 지하철역_목록_조회_요청();

        // then
        지하철역_목록_응답됨(response);
        지하철역_목록_포함됨(response, Arrays.asList(createResponse1, createResponse2));
    }

    @DisplayName("지하철역을 페이지 형태로 조회한다.")
    @Test
    void getStationsPaging() {
        // given
        지하철역들이_등록되어있음(STATIONS_COUNT);
        PageRequest request = PageRequest.of(5, 5);
        ExpectedPageResult expected = new ExpectedPageResult(5, 5, 175);
        // when
        ExtractableResponse<Response> response = 지하철_페이지요청함(request);

        페이지_응답됨(response, expected);
    }

    @Test
    @DisplayName("지하철역을 페이지 형태로 조회한다.")
    void getLinesPageWithPk() {
        // given
        지하철역들이_등록되어있음(STATIONS_COUNT);
        PageRequest pageRequest = PageRequest.of(0, 10);
        ExpectedPageResult expected = new ExpectedPageResult(0, 10, 175);
        Long id = 5L;

        // when
        ExtractableResponse<Response> response = 지하철_페이지요청함(id, pageRequest);

        // then
        페이지_응답됨(response, expected);
    }


    @DisplayName("지하철역을 제거한다.")
    @Test
    void deleteStation() {
        // given
        ExtractableResponse<Response> createResponse = 지하철역_등록되어_있음(강남역);

        // when
        ExtractableResponse<Response> response = 지하철역_제거_요청(createResponse);

        // then
        지하철역_삭제됨(response);
    }

    public static ExtractableResponse<Response> 지하철역_등록되어_있음(String name) {
        return 지하철역_생성_요청(name);
    }

    public static void 지하철역들이_등록되어있음(int count) {
        IntStream.range(0, count)
                .forEach(index -> 지하철역_생성_요청(String.valueOf(index)));
    }

    public static ExtractableResponse<Response> 지하철역_생성_요청(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);

        return RestAssured.given().log().all().
                body(params).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                when().
                post("/stations").
                then().
                log().all().
                extract();
    }

    public static ExtractableResponse<Response> 지하철역_목록_조회_요청() {
        return RestAssured.given().log().all().
                when().
                get("/stations").
                then().
                log().all().
                extract();
    }

    public static ExtractableResponse<Response> 지하철역_제거_요청(ExtractableResponse<Response> response) {
        String uri = response.header("Location");

        return RestAssured.given().log().all().
                when().
                delete(uri).
                then().
                log().all().
                extract();
    }

    public static void 지하철역_생성됨(ExtractableResponse response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    public static void 지하철역_생성_실패됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static void 지하철역_목록_응답됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static void 지하철역_삭제됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    public static void 지하철역_목록_포함됨(ExtractableResponse<Response> response, List<ExtractableResponse<Response>> createdResponses) {
        List<Long> expectedLineIds = createdResponses.stream()
                .map(it -> Long.parseLong(it.header("Location").split("/")[2]))
                .collect(Collectors.toList());

        List<Long> resultLineIds = response.jsonPath().getList(".", StationResponse.class).stream()
                .map(StationResponse::getId)
                .collect(Collectors.toList());

        assertThat(resultLineIds).containsAll(expectedLineIds);
    }

    private void 페이지_응답됨(ExtractableResponse<Response> response, ExpectedPageResult result) {
        List<StationResponse> content = response.jsonPath().getList("content", StationResponse.class);
        Map<Object, Object> pageable = response.jsonPath().getMap("pageable");
        int totalElements = (int) response.jsonPath().get("totalElements");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(content).as("페이지 사이즈가 일치하지 않음").hasSize(result.getSize());
        assertThat((int) pageable.get("pageNumber")).as("페이지번호가 일치하지않음").isEqualTo(result.getPageNumber());
        assertThat(totalElements).as("전체 row 수가 일치하지않음").isEqualTo(result.getTotalSize());
    }

    private ExtractableResponse<Response> 지하철_페이지요청함(PageRequest pageRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("page", pageRequest.getPageNumber())
                .param("size", pageRequest.getPageSize())
                .when()
                .get("/stations/page")
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 지하철_페이지요청함(Long id, PageRequest pageRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("id", id)
                .param("page", pageRequest.getPageNumber())
                .param("size", pageRequest.getPageSize())
                .when()
                .get("/stations/page")
                .then().log().all()
                .extract();
    }

}
