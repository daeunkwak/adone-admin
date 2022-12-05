package app.adoneadmin.dto.channel.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "전광채널 단가 항목 추가 요청 객체")
public class ChannelRequestDto {

    @ApiModelProperty(value = "규격", example = "300 ENG")
    private String standard;

    @ApiModelProperty(value = "가격", example = "21000")
    private int cost;

    @ApiModelProperty(value = "LED", example = "77")
    private int led;

}
