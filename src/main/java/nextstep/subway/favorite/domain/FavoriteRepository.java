package nextstep.subway.favorite.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("select favorite from Favorite favorite where favorite.id >= ?1")
    List<Favorite> findByMemberId(Long memberId, Pageable pageable);
}
