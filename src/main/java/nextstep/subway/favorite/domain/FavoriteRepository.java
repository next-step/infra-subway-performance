package nextstep.subway.favorite.domain;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findFirstByMemberId(Long memberId);

    Page<Favorite> findAllByMemberIdAndIdLessThanEqual(Long id, Long memberId, Pageable pageable);
}
