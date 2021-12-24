package nextstep.subway.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * packageName : nextstep.subway.common
 * fileName : BaseResponse
 * author : haedoang
 * date : 2021/12/24
 * description :
 */
public abstract class BaseResponse {
    public BaseResponse() {
    }

    public BaseResponse(LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime createDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime modifiedDate;

}
