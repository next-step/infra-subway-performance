package nextstep.subway;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class SubwayApplication implements Jackson2ObjectMapperBuilderCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(SubwayApplication.class, args);
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        LocalDateSerializer localDateSerializer = new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE);
//        LocalTimeSerializer localTimeSerializer = new LocalTimeSerializer(DateTimeFormatter.ISO_LOCAL_TIME);

//        OffsetDateTimeSerializer offsetDateTimeSerializer = new CustomOffsetDateTimeSerializer();
//        ZonedDateTimeSerializer zonedDateTimeSerializer = new CustomZonedDateTimeSerializer();

        jacksonObjectMapperBuilder
                .timeZone(TimeZone.getDefault()) // 올바른 타임존을 설정해야 offset/zoned datetime이 올바로 설정됨.
                .locale(Locale.getDefault())
                .simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//                .serializerByType(LocalDateTime.class, localDateTimeSerializer)
//                .serializerByType(LocalDate.class, localDateSerializer)
//                .serializerByType(LocalTime.class, localTimeSerializer)
//                .serializerByType(OffsetDateTime.class, offsetDateTimeSerializer)
//                .serializerByType(ZonedDateTime.class, zonedDateTimeSerializer);
    }
}
