package app.adoneadmin.dto.signboard;

import app.adoneadmin.vo.signboard.StandardMaterialVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "규격/재질에 따른 단가 테이블 조회, 수정 객체")
public class StandardMaterialDto {

    @ApiModelProperty(value = "단가 칼럼 id")
    private long id;

    @ApiModelProperty(value = "규격")
    private int standard;

    @ApiModelProperty(value = "알루미늄 단가")
    private int aluminum;

    @ApiModelProperty(value = "갈바 단가")
    private int galva;

    @ApiModelProperty(value = "스탠 단가")
    private int stan;


    public StandardMaterialDto(StandardMaterialVo standardMaterialVo){

        this.id = standardMaterialVo.getId();
        this.standard = standardMaterialVo.getStandard();
        this.aluminum = standardMaterialVo.getAluminum();
        this.galva = standardMaterialVo.getGalva();
        this.stan = standardMaterialVo.getStan();
    }

}
