package nextstep.subway.cache;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import nextstep.subway.AcceptanceTest;
import nextstep.subway.support.version.SubwayVersion;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class WebCacheTest extends AcceptanceTest {
    @Autowired
    private SubwayVersion subwayVersion;

    @Test
    void etag() {
        // given
        String uri = createUri("/js/main.js");

        // when
        Response 응답 = getResponse(uri);

        // then
        assertThat(응답.getHeader(HttpHeaders.ETAG)).isNotNull();
    }

    @Test
    void notModified_givenEtag() {
        // given
        Response 첫번째_응답 = getResponse(createUri("/js/main.js"));

        // when
        Response 두번째_응답 = getResponse(createUri("/js/main.js"), 첫번째_응답.getHeader(HttpHeaders.ETAG));

        // then
        assertAll(
                () -> assertThat(첫번째_응답.getStatusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(두번째_응답.getStatusCode()).isEqualTo(HttpStatus.NOT_MODIFIED.value())
        );
    }

    public Response getResponse(String uri) {
        return RestAssured.given()
                          .header(HttpHeaders.ACCEPT, ContentType.JSON)
                          .log()
                          .all()
                          .when()
                          .get(uri);
    }

    public Response getResponse(String uri, String etag) {
        return RestAssured.given()
                          .header(HttpHeaders.ACCEPT, ContentType.JSON)
                          .header(HttpHeaders.IF_NONE_MATCH, etag)
                          .log()
                          .all()
                          .when()
                          .get(uri);
    }

    public String createUri(String path) {
        return String.format("/resources/%s%s", subwayVersion.getVersion(), path);
    }
}
