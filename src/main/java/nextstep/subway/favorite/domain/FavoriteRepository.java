package nextstep.subway.favorite.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Slice<Favorite> findByMemberId(Long memberId, Pageable pageable);
}
