package app.adoneadmin.dto.signboard.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "전면간판 단가 항목 추가 요청 객체")
public class FrontFrameRequestDto {

    @ApiModelProperty(value = "규격", example = "HQL 프레임")
    private String standard;

    @ApiModelProperty(value = "가격", example = "21000")
    private int cost;
}
