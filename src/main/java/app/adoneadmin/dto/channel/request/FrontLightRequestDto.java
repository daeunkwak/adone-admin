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
public class FrontLightRequestDto {

    @ApiModelProperty(value = "규격", example = "300 ENG")
    private String standard;

    @ApiModelProperty(value = "LED", example = "77")
    private int led;

    private int depth80;

    private int depth100;

    private int depth35;

    private int depth60;

    private int depth50;

    private int depth120;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 단가 항목 수정 요청 객체")
    public static class FrontLightUpdateRequestDto{

        @ApiModelProperty(value = "단가 id", example = "1")
        private long id;

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

        private int depth80;

        private int depth100;

        private int depth35;

        private int depth60;

        private int depth50;

        private int depth120;

    }
}
