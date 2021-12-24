package nextstep.subway.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PageRequestPerform extends PageRequest implements PagePosition {

    private PageRequestPerform(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    public static PageRequestPerform of(final int size) {
        return new PageRequestPerform(0, size, Sort.unsorted());
    }
}
