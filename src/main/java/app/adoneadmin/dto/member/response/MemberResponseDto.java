package app.adoneadmin.dto.member.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "회원 조회 반환 객체")
public class MemberResponseDto {

    @ApiModelProperty(value = "유저 id")
    private Long memberId;

    @ApiModelProperty(value = "회사명")
    private String companyName;

    @ApiModelProperty(value = "대표자명")
    private String representName;

    @ApiModelProperty(value = "사업자번호")
    private String companyRegisterName;

    @ApiModelProperty(value = "대표전화")
    private String representPhone;

    @ApiModelProperty(value = "주소")
    private String fullAddress;

    @ApiModelProperty(value = "승인여부")
    private Boolean isAuthorized;

}
