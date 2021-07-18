package nextstep.subway.favorite.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByMemberId(Long memberId);


    List<Favorite> findByMemberIdOrderById(Long id, Pageable pageable);


    @Query("select favorite from Favorite favorite where favorite.id >= ?1")
    List<Favorite> findByMemberId(Long memberId, Pageable pageable);
}
