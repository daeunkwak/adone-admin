package app.adoneadmin.dto.member.request;

import app.adoneadmin.dto.image.ImageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@ApiModel(description = "시공사 회원정보 업데이트 요청 객체")
public class MemberUpdateRequestDto {

    @ApiModelProperty(value = "회사명")
    @NotNull
    private String companyName;

    @ApiModelProperty(value = "대표자명")
    @NotNull
    private String representName;

    @ApiModelProperty(value = "사업자번호")
    @NotNull
    private String companyRegisterNumber;

    @ApiModelProperty(value = "대표자 연락처")
    @NotNull
    private String representPhone;

    @ApiModelProperty(value = "업장주소지")
    @NotNull
    private String fullAddress;

    @ApiModelProperty(value = "승인여부")
    @NotNull
    private Boolean isAuthorized;   // TODO : ADMIN <-> isAuthorized - false 기본값으로 설정

    @ApiModelProperty(value = "사업자 등록증 이미지")
    @NotNull
    private ImageDto companyRegisterImage;

    @ApiModelProperty(value = "시공사 대표 이미지")
    @NotNull
    private ImageDto constructorImageDto;


}
