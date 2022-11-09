package app.adoneadmin.service.member;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.domain.member.constant.MemberRole;
import app.adoneadmin.global.exception.handler.BadRequestException;
import app.adoneadmin.global.exception.handler.NoSuchMemberException;
import app.adoneadmin.repository.member.MemberRepository;
import app.adoneadmin.vo.member.MemberDetailResponseVo;
import app.adoneadmin.vo.member.MemberUpdateVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 시공사 회원 리스트 조회
     */
    public List<Member> getMemberList() {

        return memberRepository.findAll().stream().filter(
                member -> member.getMemberRole().equals(MemberRole.CONTRACTOR)).collect(Collectors.toList());
    }


    /**
     * 시공사 회원 상세 조회
     */
    public MemberDetailResponseVo getMemberDetail(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("존재하지 않는 회원입니다."));

        MemberDetailResponseVo memberDetailResponseVo = new MemberDetailResponseVo();
        memberDetailResponseVo.setMemberImage(member.getMemberImage());
        memberDetailResponseVo.setCompanyRegisterImage(member.getCompanyRegisterImage());
        memberDetailResponseVo.setMember(member);
        return memberDetailResponseVo;
    }


    /**
     * 시공사 회원 승인
     */
    public Member updateAuth(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("존재하지 않는 회원입니다."));
        if (member.getIsAuthorized()){
            throw new BadRequestException("이미 승인된 회원입니다.");
        }
        member.setIsAuthorized(true);
        memberRepository.save(member);
        return member;
    }


    /**
     * 시공사 회원 검색
     */
    public List<Member> getMemberSearch(String searchWord) {

        return memberRepository.getMemberSearch(searchWord);
    }


    /**
     * 시공사 회원정보 수정
     */
    public Member updateMemberInfo(MemberUpdateVo memberUpdateVo, Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("존재하지 않는 회원입니다."));

        member.setCompanyName(memberUpdateVo.getCompanyName());
        member.setRepresentName(memberUpdateVo.getRepresentName());
        member.setFullAddress(memberUpdateVo.getFullAddress());
        member.setRepresentPhone(memberUpdateVo.getRepresentPhone());
        return memberRepository.save(member);
    }


}

