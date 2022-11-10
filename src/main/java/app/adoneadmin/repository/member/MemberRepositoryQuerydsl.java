package app.adoneadmin.repository.member;


import app.adoneadmin.domain.member.Member;
import app.adoneadmin.vo.member.MemberDetailResponseVo;

import java.util.List;

public interface MemberRepositoryQuerydsl {
    MemberDetailResponseVo getContractorInfo(Long memberId);


}
