package app.adoneadmin.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@ApiModel(description = "삭제 요청 객체")
public class DeleteRequestDto {

    @ApiModelProperty(value = "삭제할 id 리스트")
    private List<Long> idList;

}
