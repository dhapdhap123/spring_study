package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.domain.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join(){
        // given
        Member member1 = new Member();
        member1.setName("sprint1");

        // when
        Long saveId = memberService.join(member1);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member1.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("sprint1");

        Member member2 = new Member();
        member2.setName("sprint1");

        // when
        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            System.out.println("그대로 진행");
//        } catch (IllegalStateException e){
//            Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.asdf");
//            System.out.println("에러 뜸");
//        }


        // then
    }
}
