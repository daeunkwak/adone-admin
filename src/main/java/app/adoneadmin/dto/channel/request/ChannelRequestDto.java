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


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 단가 추가 요청 객체")
    public static class FrontLight extends ChannelRequestDto{

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

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

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

        private int depth80;

        private int depth30;

        private int depth50;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전후광채널 단가 추가 요청 객체")
    public static class FrontBackLight extends ChannelRequestDto{

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

        private int depth80;

        private int depth100;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "비조명(벡채널) 단가 추가 요청 객체")
    public static class Back extends ChannelRequestDto{

        private int depth80;

        private int depth30;

        private int depth50;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "스카시 단가 추가 요청 객체")
    public static class Scasi extends ChannelRequestDto{

        private int depth3;

        private int depth5;

        private int depth8;

        private int depth10;

        private int depth20;

        private int depth30;

        private int depth50;

    }


}

