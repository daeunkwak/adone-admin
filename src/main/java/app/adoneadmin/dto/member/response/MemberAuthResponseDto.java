package app.adoneadmin.dto.member.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "회원 승인 여부 반환 객체")
public class MemberAuthResponseDto {

    @ApiModelProperty(value = "회원 id")
    private Long memberId;

    @ApiModelProperty(value = "승인 여부 / true:승인됨 false:승인 전")
    private Boolean isAuthorized;
}
