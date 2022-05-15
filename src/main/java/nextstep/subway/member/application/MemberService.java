package nextstep.subway.member.application;

import nextstep.subway.member.domain.Member;
import nextstep.subway.member.domain.MemberRepository;
import nextstep.subway.member.dto.MemberRequest;
import nextstep.subway.member.dto.MemberResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static nextstep.subway.common.CacheNames.LOGINS;
import static nextstep.subway.common.CacheNames.MEMBERS;

@Service
@Transactional
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse createMember(MemberRequest request) {
        Member member = memberRepository.save(request.toMember());
        return MemberResponse.of(member);
    }

    @Cacheable(value = MEMBERS, key = "#id")
    public MemberResponse findMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        return MemberResponse.of(member);
    }

    @Caching(put = {
            @CachePut(value = MEMBERS, key = "#id")
    }, evict = {
            @CacheEvict(value = LOGINS, allEntries = true)
    })
    public void updateMember(Long id, MemberRequest param) {
        Member member = memberRepository.findById(id).orElseThrow(RuntimeException::new);
        member.update(param.toMember());
    }

    @Caching(evict = {
            @CacheEvict(value = MEMBERS, key = "#id"),
            @CacheEvict(value = LOGINS, allEntries = true)
    })
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
