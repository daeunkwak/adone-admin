package app.adoneadmin.dto.signboard;

import app.adoneadmin.dto.signboard.request.PointRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "돌출 포인트 간판 객체")
public class PointDto {

    @ApiModelProperty(value = "단가 id")
    private long id;

    @ApiModelProperty(value = "규격")
    private String standard;

    @ApiModelProperty(value = "스텐돌출")
    private boolean stan;

    @ApiModelProperty(value = "원형돌출")
    private boolean circle;

    @ApiModelProperty(value = "사각돌출")
    private boolean square;

    @ApiModelProperty(value = "라운드입간판")
    private boolean round;

    @ApiModelProperty(value = "회전간판")
    private boolean rotation;

}
