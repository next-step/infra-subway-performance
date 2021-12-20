package nextstep.subway.common;

public interface PagePosition {

    default Long getLastIdx(final int page, final int size) {
        return (long) page * (long) size + 1L;
    }
}
