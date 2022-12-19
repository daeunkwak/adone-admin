package app.adoneadmin.dto.signboard.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "레이저 타공 단가 추가 요청 객체")
public class LaserRequestDto {

    @ApiModelProperty(value = "규격")
    private String standard;

    @ApiModelProperty(value = "알루미늄 단가")
    private int aluminum;

    @ApiModelProperty(value = "갈바 단가")
    private int galva;

    @ApiModelProperty(value = "스탠 단가")
    private int stan;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update extends LaserRequestDto{

        @ApiModelProperty(value = "단가 id")
        private long id;

    }
}
