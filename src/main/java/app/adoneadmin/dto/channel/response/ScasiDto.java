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
@ApiModel(description = "스카시 채널 단가 항목 객체")
public class ScasiDto {

    @ApiModelProperty(value = "단가 id", example = "1")
    private long id;

    @ApiModelProperty(value = "규격", example = "300ENG")
    private String standard;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Acryl extends ScasiDto{

        private int depth3;

        private int depth5;

        private int depth8;

        private int depth10;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Formex extends ScasiDto{

        private int depth3;

        private int depth5;

        private int depth8;

        private int depth10;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Rubber extends ScasiDto{

        private int depth20;

        private int depth30;

        private int depth50;

    }

}
