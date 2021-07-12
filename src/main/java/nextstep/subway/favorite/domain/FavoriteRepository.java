package nextstep.subway.favorite.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findFirstByMemberId(Long memberId);

    List<Favorite> findByMemberIdAndIdLessThan(Long memberId, Long id, Pageable pageable);
}
