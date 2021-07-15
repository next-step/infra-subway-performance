package nextstep.subway.favorite.domain;

import nextstep.subway.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(name = "idx_favorite_memberId", columnList = "memberId"))
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long sourceStationId;
    private Long targetStationId;

    public Favorite() {
    }

    public Favorite(Long memberId, Long sourceStationId, Long targetStationId) {
        this.memberId = memberId;
        this.sourceStationId = sourceStationId;
        this.targetStationId = targetStationId;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getSourceStationId() {
        return sourceStationId;
    }

    public Long getTargetStationId() {
        return targetStationId;
    }

    public boolean isCreatedBy(Long memberId) {
        return this.memberId.equals(memberId);
    }
}
