package nextstep.subway.common;

import org.springframework.data.domain.PageRequest;

public class CustomPageRequest  {

    private static final int MAX_SIZE = 100;

    private final int page;
    private final int size;

    public CustomPageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public PageRequest toPageRequest(){
        if(MAX_SIZE < size) {
            throw new IllegalArgumentException("page over size exception");
        }
        return PageRequest.of(page,size);
    }
}
