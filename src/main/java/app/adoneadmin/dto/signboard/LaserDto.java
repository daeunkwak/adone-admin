package app.adoneadmin.dto.signboard;


import app.adoneadmin.vo.signboard.LaserVo;
import app.adoneadmin.vo.signboard.StandardMaterialVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "레이저 타공 단가 반환 객체")
public class LaserDto {

    // TODO : StandardMaterialDto와 같이 refactor

    @ApiModelProperty(value = "단가 id")
    private long id;

    @ApiModelProperty(value = "규격")
    private String standard;

    @ApiModelProperty(value = "알루미늄 단가")
    private int aluminum;

    @ApiModelProperty(value = "갈바 단가")
    private int galva;

    @ApiModelProperty(value = "스탠 단가")
    private int stan;


    public LaserDto(LaserVo laserVo){

        this.id = laserVo.getId();
        this.standard = laserVo.getStandard();
        this.aluminum = laserVo.getAluminum();
        this.galva = laserVo.getGalva();
        this.stan = laserVo.getStan();
    }

}
