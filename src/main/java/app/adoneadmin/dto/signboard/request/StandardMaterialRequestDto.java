package app.adoneadmin.dto.signboard.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "규격/재질에 따른 단가 테이블 생성 요청 객체")
public class StandardMaterialRequestDto {

    @ApiModelProperty(value = "규격")
    private int standard;

    @ApiModelProperty(value = "알루미늄 단가")
    private int aluminum;

    @ApiModelProperty(value = "갈바 단가")
    private int galva;

    @ApiModelProperty(value = "스탠 단가")
    private int stan;

}
