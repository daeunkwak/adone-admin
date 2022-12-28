package app.adoneadmin.dto.channel.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "채널 단가 조회 객체")
public class ChannelDto {

    @ApiModelProperty(value = "단가 id", example = "1")
    private long id;

    @ApiModelProperty(value = "규격", example = "300 ENG")
    private String standard;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "후광채널 조회 객체")
    public static class BackLight extends ChannelDto{

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

        private int depth80;

        private int depth30;

        private int depth50;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전후광채널 조회 객체")
    public static class FrontBackLight extends ChannelDto{

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

        private int depth80;

        private int depth100;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "비조명(백채널) 조회 객체")
    public static class Back extends ChannelDto{

        private int depth80;

        private int depth30;

        private int depth50;
    }

}

