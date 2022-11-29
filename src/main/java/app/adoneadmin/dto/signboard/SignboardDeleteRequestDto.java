package app.adoneadmin.dto.signboard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@ApiModel(description = "단가 테이블 삭제 요청 객체")
public class SignboardDeleteRequestDto {

    @ApiModelProperty(value = "삭제할 단가 테이블 id 리스트")
    private List<Long> idList;

}
