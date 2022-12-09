package app.adoneadmin.dto.signboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "규격+단가 조회 반환 객체")
public class StandardCostDto {

    @ApiModelProperty(value = "단가 id", example = "1")
    private long id;

    @ApiModelProperty(value = "규격", example = "HQL 프레임")
    private String standard;

    @ApiModelProperty(value = "단가", example = "21000")
    private int cost;

}
