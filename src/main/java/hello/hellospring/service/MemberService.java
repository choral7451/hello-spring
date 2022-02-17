package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepositroy;

    public MemberService(MemberRepository memberRepositroy) {
        this.memberRepositroy = memberRepositroy;
    }

    /* 회원가입 */
    public long join(Member member) {
        //같은 이름이 있는 중복 회원X
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepositroy.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepositroy.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers() {
        return memberRepositroy.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepositroy.findById(memberId);
    }

}
