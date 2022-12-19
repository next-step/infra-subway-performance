package nextstep.subway.handlebars;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlebarsTest {

    private static final Logger logger = LoggerFactory.getLogger(HandlebarsTest.class);

    @Test
    @DisplayName("this 이해")
    void hello() throws Exception {
        Template template = getTemplate("Hello {{this}}!");
        String result = template.apply("Handlebars.java");
        assertThat(result).isEqualTo("Hello Handlebars.java!");
    }

    @Test
    @DisplayName("자바 빈 객체를 통해 데이터 전달")
    void with() throws Exception {
        Template template = getTemplate("{{#novel}}<span>{{title}}</span>{{/novel}}");
        Map<String, Object> context = new HashMap<>();
        Novel novel = new Novel("자바 웹 프로그래밍 Next Step", "javajigi");
        context.put("novel", novel);
        String result = template.apply(context);
        logger.debug(result);
        assertThat(result).isEqualTo("<span>자바 웹 프로그래밍 Next Step</span>");
    }

    private Template getTemplate(String template) throws IOException {
        Handlebars handlebars = new Handlebars();
        return handlebars.compileInline(template);
    }
}
