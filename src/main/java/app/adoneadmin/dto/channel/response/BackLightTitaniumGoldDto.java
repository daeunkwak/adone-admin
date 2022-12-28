package app.adoneadmin.dto.channel.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "전광채널 티타늄골드 단가 객체")
public class BackLightTitaniumGoldDto {

    @ApiModelProperty(value = "단가 id", example = "1")
    private long id;

    @ApiModelProperty(value = "규격", example = "300ENG")
    private String standard;

    @ApiModelProperty(value = "단가", example = "170000")
    private int cost;
}
