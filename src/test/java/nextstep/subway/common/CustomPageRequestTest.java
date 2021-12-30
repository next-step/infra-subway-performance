package nextstep.subway.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.data.domain.Pageable;

class CustomPageRequestTest {

    @DisplayName("페이징 DTO 테스트")
    @ParameterizedTest
    @CsvSource({
        "0,10,10",
        "1,20,20",
    })
    void page_request(int page, int size, int expected){
        // given
        CustomPageRequest customPageRequest = new CustomPageRequest(page,size);

        // when
        Pageable pageable = customPageRequest.toPageRequest();

        // then
        assertThat(pageable.getPageSize()).isEqualTo(expected);
    }


    @DisplayName("페이징 DTO 최대 사이즈 초과 실패")
    @Test
    void over_size_request_fail(){
        // given
        CustomPageRequest customPageRequest = new CustomPageRequest(0,1000);

        // when
        ThrowableAssert.ThrowingCallable actual = customPageRequest::toPageRequest;

        // then
        assertThatThrownBy(actual).isInstanceOf(IllegalArgumentException.class);
    }
}
