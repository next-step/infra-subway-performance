package nextstep.subway.helper;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.ObjectUtils;

public class PageRequest {

    private final int FIX_SIZE = 10;
    private Integer size;
    private Integer page;
    private Sort.Direction direction;

    public void setPage(Integer page) {
        if (ObjectUtils.isEmpty(page) || page <= 0) {
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setDirection(Direction direction) {
        if (ObjectUtils.isEmpty(direction)) {
            this.direction = Direction.ASC;
            return;
        }
        if (ObjectUtils.isEmpty(page) || page <= 0) {
            this.page = 1;
        }

        this.direction = direction;
    }

    public void setSize(int size) {
        this.size = FIX_SIZE;
    }

    public org.springframework.data.domain.PageRequest of() {
        setDefaultIfValuesIsEmpty();
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction,
            "createdDate");
    }

    private void setDefaultIfValuesIsEmpty() {
        if (ObjectUtils.isEmpty(direction)) {
            this.direction = Direction.ASC;
        }
        if (ObjectUtils.isEmpty(page) || page <= 0) {
            this.page = 1;
        }
        this.size = FIX_SIZE;
    }
}
