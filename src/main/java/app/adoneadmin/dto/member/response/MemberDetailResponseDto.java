package app.adoneadmin.dto.member.response;

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

    @ApiModelProperty(value = "유저 id")
    private Long memberId;

    @ApiModelProperty(value = "회사명")
    private String companyName;

    @ApiModelProperty(value = "대표자명")
    private String representName;

    @ApiModelProperty(value = "사업자번호")
    private String companyRegisterNumber;

    @ApiModelProperty(value = "대표자 연락처")
    private String representPhone;

    @ApiModelProperty(value = "업장주소지")
    private String fullAddress;

    @ApiModelProperty(value = "승인여부")
    private Boolean isAuthorized;   // TODO : ADMIN <-> isAuthorized - false 기본값으로 설정해주기

    @ApiModelProperty(value = "사업자 등록증 이미지")
    private ImageDto companyRegisterImageDto;

    @ApiModelProperty(value = "시공사 대표 이미지")
    private ImageDto constructorImageDto;

    public static MemberDetailResponseDto from(MemberDetailResponseVo memberDetailResponseVo){
        return new MemberDetailResponseDtoBuilder()
                .memberId(memberDetailResponseVo.getMember().getMemberId())
                .companyName(memberDetailResponseVo.getMember().getCompanyName())
                .representName(memberDetailResponseVo.getMember().getRepresentName())
                .companyRegisterNumber(memberDetailResponseVo.getMember().getCompanyRegistrationNumber())
                .representPhone(memberDetailResponseVo.getMember().getRepresentPhone())
                .fullAddress(memberDetailResponseVo.getMember().getFullAddress())
                .isAuthorized(memberDetailResponseVo.getMember().getIsAuthorized())
               // .companyRegisterImageDto(memberDetailResponseVo.getCompanyRegisterImage())
                //.constructorImageDto(memberDetailResponseVo.getMemberImage())
                .build();
    }


}
