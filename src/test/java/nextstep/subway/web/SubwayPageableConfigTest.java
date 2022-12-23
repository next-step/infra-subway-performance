package nextstep.subway.web;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import java.util.List;
import nextstep.subway.AcceptanceTest;
import nextstep.subway.station.dto.StationResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@Sql(scripts = "classpath:sql/stations-data.sql",
    executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class SubwayPageableConfigTest extends AcceptanceTest {

    static final int ALL_STATIONS_COUNT = 103;

    @Test
    @DisplayName("Default Pageable 테스트")
    void testDefaultPageable() {
        final List<StationResponse> allStations = getAllStations();

        assertThat(allStations).hasSize(ALL_STATIONS_COUNT);
    }

    @Test
    @DisplayName("Pageable 테스트")
    void testPageable() {
        final List<StationResponse> pagedStations = getAllPagedStations(10, 7);

        assertThat(pagedStations).hasSize(10);
        assertThat(pagedStations).element(0)
                                 .extracting(StationResponse::getId)
                                 .isEqualTo(71L);
        assertThat(pagedStations).element(9)
                                 .extracting(StationResponse::getId)
                                 .isEqualTo(80L);
    }

    private List<StationResponse> getAllStations() {
        return getAllStations("");
    }

    private List<StationResponse> getAllPagedStations(int size, int page) {
        return getAllStations(format("?size=%s&page=%s&sort=id,ASC", size, page));
    }

    private List<StationResponse> getAllStations(String queryString) {
        return RestAssured.given().log().all()
                          .when().get("/stations"+ queryString)
                          .then().log().all()
                          .extract().body().as(new TypeRef<List<StationResponse>>() {});
    }
}
