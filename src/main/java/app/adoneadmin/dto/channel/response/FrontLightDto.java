package app.adoneadmin.dto.channel.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "전광채널 단가 항목 객체")
public class FrontLightDto {

    @ApiModelProperty(value = "단가 id", example = "1")
    private long id;

    @ApiModelProperty(value = "규격", example = "300 ENG")
    private String standard;

    @ApiModelProperty(value = "LED", example = "77")
    private int led;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel(description = "전광채널 알루미늄 객체")
    public static class Alu extends FrontLightDto {

        private int depth80;

        private int depth100;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 일체형 객체")
    public static class Assembled extends FrontLightDto{

        private int depth35;

        private int depth60;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 에폭시 객체")
    public static class Epoxy extends FrontLightDto{

        private int depth35;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 갈바 객체")
    public static class Galva extends FrontLightDto{

        private int depth80;

        private int depth100;

        private int depth60;

        private int depth50;

        private int depth120;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 스텐 객체")
    public static class Stan extends FrontLightDto{

        private int depth80;

        private int depth100;

        private int depth60;

        private int depth50;

        private int depth120;

    }

}
