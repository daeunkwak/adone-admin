package app.adoneadmin.vo.member;

import app.adoneadmin.dto.image.ImageDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberUpdateVo {

    private String companyName;

    private String representName;

    private String companyRegisterNumber;

    private String representPhone;

    private String buildingAddress;

    private String detailAddress;

    private ImageDto companyRegisterImage;

    private ImageDto constructorImageDto;
}
