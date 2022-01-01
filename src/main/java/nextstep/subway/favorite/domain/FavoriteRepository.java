package nextstep.subway.favorite.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("select favorite from Favorite favorite where favorite.id >= ?1")
    Page<Favorite> findByMemberId(Long memberId, Pageable pageable);
}
