package app.adoneadmin.dto.channel;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "후광채널 단가 항목 객체")
public class BackLightDto {

    @ApiModelProperty(value = "단가 id", example = "1")
    private long id;

    @ApiModelProperty(value = "규격", example = "300 ENG")
    private String standard;

    @ApiModelProperty(value = "LED", example = "77")
    private int led;

    private int depth80;

    private int depth30;

    private int depth50;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel(description = "후광채널 갈바 객체")
    public static class Galva extends BackLightDto{

        private int depth80;

        private int depth30;

        private int depth50;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel(description = "후광채널 스텐 객체")
    public static class Stan extends BackLightDto{

        private int depth80;

        private int depth30;

        private int depth50;
    }

}
