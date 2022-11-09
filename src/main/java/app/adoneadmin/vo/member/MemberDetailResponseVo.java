package app.adoneadmin.vo.member;

import app.adoneadmin.domain.image.Image;
import app.adoneadmin.domain.image.companyRegister.CompanyRegisterImage;
import app.adoneadmin.domain.image.member.MemberImage;
import app.adoneadmin.domain.image.member.MemberImageRepository;
import app.adoneadmin.domain.member.Member;
import app.adoneadmin.dto.image.ImageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDetailResponseVo {

    private Member member;

    private CompanyRegisterImage companyRegisterImage;

    private MemberImage memberImage;

}
