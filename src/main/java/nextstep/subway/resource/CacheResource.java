package nextstep.subway.resource;

public enum CacheResource {
    FIND_PATH("findPath");

    private final String cacheValue;

    CacheResource(String cacheValue) {
        this.cacheValue = cacheValue;
    }

    public String cacheValue() {
        return cacheValue;
    }
}
