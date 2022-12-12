package app.adoneadmin.dto.channel.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "채널 단가 추가 요청 객체")
public class ChannelRequestDto {

    @ApiModelProperty(value = "규격", example = "300 ENG")
    private String standard;

    @ApiModelProperty(value = "LED", example = "77")
    private int led;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 단가 추가 요청 객체")
    public static class FrontLight extends ChannelRequestDto{

        private int depth80;

        private int depth100;

        private int depth35;

        private int depth60;

        private int depth50;

        private int depth120;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "후광채널 단가 추가 요청 객체")
    public static class BackLight extends ChannelRequestDto{

        private int depth80;


        private int depth30;

        private int depth50;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전후광채널 단가 추가 요청 객체")
    public static class FrontBackLight extends ChannelRequestDto{

        private int depth80;

        private int depth100;

    }


}

