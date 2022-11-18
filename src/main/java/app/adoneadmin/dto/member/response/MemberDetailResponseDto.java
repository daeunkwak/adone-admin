package app.adoneadmin.dto.member.response;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.dto.image.ImageDto;
import app.adoneadmin.vo.member.MemberDetailResponseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "회원 상세 조회 반환 객체")
public class MemberDetailResponseDto {

    @ApiModelProperty(value = "유저 id", example = "2")
    private Long memberId;

    @ApiModelProperty(value = "유저 로그인 id", example = "ssn7572")
    private String loginId;

    @ApiModelProperty(value = "회사명", example = "모협조")
    private String companyName;

    @ApiModelProperty(value = "대표자명", example = "최원서")
    private String representName;

    @ApiModelProperty(value = "사업자번호", example = "123123")
    private String companyRegisterNumber;

    @ApiModelProperty(value = "대표자 연락처", example = "010-2222-2222")
    private String representPhone;

    @ApiModelProperty(value = "업장주소지", example = "노원구 인덕대 101동 101호")
    private String fullAddress;

    @ApiModelProperty(value = "업장주소지 도로명/지번 주소", example = "노원구 인덕대")
    private String buildingAddress;

    @ApiModelProperty(value = "업장주소지 상세 주소", example = "101동 101호")
    private String detailAddress;

    @ApiModelProperty(value = "승인여부", example = "true/false")
    private Boolean isAuthorized;

    @ApiModelProperty(value = "사업자 등록증 이미지")
    private ImageDto companyRegisterImageDto;

    @ApiModelProperty(value = "시공사 대표 이미지")
    private ImageDto memberImageDto;

    public MemberDetailResponseDto(Member member, ImageDto memberImageDto, ImageDto companyRegisterImageDto) {
        this.memberId = member.getMemberId();
        this.loginId = member.getLoginId();
        this.companyName = member.getCompanyName();
        this.representName = member.getRepresentName();
        this.companyRegisterNumber = member.getCompanyRegistrationNumber();
        this.representPhone = member.getRepresentPhone();
        this.fullAddress = member.getFullAddress();
        this.buildingAddress = member.getBuildingAddress();
        this.detailAddress = member.getDetailAddress();
        this.isAuthorized = member.getIsAuthorized();
        this.memberImageDto = memberImageDto;
        this.companyRegisterImageDto = companyRegisterImageDto;
    }

}
