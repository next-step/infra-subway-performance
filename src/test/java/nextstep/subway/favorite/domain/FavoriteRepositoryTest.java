package nextstep.subway.favorite.domain;

import nextstep.subway.member.domain.Member;
import nextstep.subway.member.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FavoriteRepositoryTest {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findByMemberId() {
        // given
        LongStream.range(0, 20).forEach((i) -> memberRepository.save(new Member("email@email.com","password", 10)));
        LongStream.range(0, 20).forEach((i) -> favoriteRepository.save(new Favorite(i, i, i)));

        // when
        List<Favorite> favorites = favoriteRepository.findByMemberId(10L, PageRequest.of(0, 10));

        // then
        assertThat(favorites.size()).isEqualTo(10);
    }
}
