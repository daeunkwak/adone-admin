package app.adoneadmin.repository.member;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.vo.member.MemberDetailResponseVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static app.adoneadmin.domain.image.companyRegister.QCompanyRegisterImage.companyRegisterImage;
import static app.adoneadmin.domain.image.member.QMemberImage.memberImage;
import static app.adoneadmin.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryQuerydsl{

    private final JPAQueryFactory jpaQueryFactory;

//    @Override
//    public MemberDetailResponseVo getContractorInfo(Long memberId) {
//        return jpaQueryFactory.select(Projections.fields(MemberDetailResponseVo.class,
//                        member,
//                        companyRegisterImage,
//                        memberImage))
//                // .from(member)
//                .leftJoin(companyRegisterImage).on(companyRegisterImage.member.memberId.eq(member.memberId))
//                .leftJoin(memberImage).on(memberImage.member.memberId.eq(member.memberId))
//                .fetchOne();
//    }

//    @Override
//    public List<Member> getMemberSearch(String searchWord) {
//        return jpaQueryFactory.selectFrom(member)
//                .where(member.companyName.like(searchWord))
//                .fetch();
//    }



}
