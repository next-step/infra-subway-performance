package nextstep.subway.favorite.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findFirstByMemberIdOrderByIdDesc(Long memberId);

    @Query("SELECT f FROM Favorite f WHERE f.id <= :id AND f.memberId = :memberId")
    List<Favorite> findLatestByMemberId(@Param("id") Long id, @Param("memberId") Long memberId, Pageable pageable);
}
