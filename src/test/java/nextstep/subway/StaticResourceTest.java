package nextstep.subway;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class StaticResourceTest extends AcceptanceTest {

    @Test
    void get_static_resources() {
        RestAssured.given().log().all()
            .when().get("/js/main.js")
            .then().header("Cache-Control", "no-cache, private");
    }

}
