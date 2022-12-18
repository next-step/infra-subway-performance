package nextstep.subway.common;

import io.restassured.RestAssured;
import nextstep.subway.AcceptanceTest;
import org.junit.jupiter.api.Test;

class WebMvcConfigTest extends AcceptanceTest {

    @Test
    void get_static_resources() {
        RestAssured.given().log().all()
                .when().get("/js/main.js")
                .then().header("Cache-Control", "no-cache, private");
    }
}
