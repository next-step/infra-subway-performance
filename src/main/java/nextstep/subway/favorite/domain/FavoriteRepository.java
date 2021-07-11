package nextstep.subway.favorite.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT f FROM Favorite f WHERE f.memberId = ?1 and f.id >= 0")
    Page<Favorite> findAllByMemberId(Long memberId, Pageable pageable);
}
