package nextstep.subway.utils;

/**
 * packageName : nextstep.subway.utils
 * fileName : ExpectedPageResult
 * author : haedoang
 * date : 2021/12/30
 * description :
 */
public class ExpectedPageResult {
    private final int pageNumber;
    private final int size;
    private final int totalSize;

    public ExpectedPageResult(int pageNumber, int size, int totalSize) {
        this.pageNumber = pageNumber;
        this.size = size;
        this.totalSize = totalSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getSize() {
        return size;
    }

    public int getTotalSize() {
        return totalSize;
    }
}

