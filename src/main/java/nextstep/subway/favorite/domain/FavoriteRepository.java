package nextstep.subway.favorite.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("select f from Favorite f where f.id >= ?1")
    List<Favorite> findByMemberId(Long memberId, Pageable pageable);
}
