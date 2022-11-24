package app.adoneadmin.dto.signboard.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@ApiModel(description = "규격/재질에 따른 단가 테이블 삭제 요청 객체")
public class StandardMaterialDeleteRequestDto {

    @ApiModelProperty(value = "삭제할 단가 테이블 id 리스트")
    private List<Long> idList;

}
