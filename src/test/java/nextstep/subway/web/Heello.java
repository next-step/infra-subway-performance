package nextstep.subway.web;

import nextstep.subway.support.SubwayVersionSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Heello {

    @Autowired
    private SubwayVersionSupport version;


    @Test
    @DisplayName("")
    void test() {
        String version1 = version.getVersion();

        System.out.println(version1);
        System.out.println("--------");
    }
}
