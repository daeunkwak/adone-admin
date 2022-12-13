package app.adoneadmin.dto.channel.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "채널 단가 수정 요청 객체")
public class ChannelUpdateRequestDto {

    @ApiModelProperty(value = "단가 id", example = "1")
    private long id;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전광채널 단가 수정 요청 객체")
    public static class FrontLightUpdate extends ChannelUpdateRequestDto {

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
    @ApiModel(description = "후광채널 단가 수정 요청 객체")
    public static class BackLightUpdate extends ChannelUpdateRequestDto {

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

        private int depth80;

        private int depth30;

        private int depth50;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "전후광채널 단가 수정 요청 객체")
    public static class FrontBackLightUpdate extends ChannelUpdateRequestDto {

        @ApiModelProperty(value = "LED", example = "77")
        private int led;

        private int depth80;

        private int depth100;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "비조명(백채널) 단가 수정 요청 객체")
    public static class BackUpdate extends ChannelUpdateRequestDto {

        private int depth80;

        private int depth30;

        private int depth50;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "스카시 단가 수정 요청 객체")  // TODO :이것도 Rubber랑 수정/생성 분리 -> 성능 비교
    public static class ScasiUpdate extends ChannelUpdateRequestDto {

        private int depth3;

        private int depth5;

        private int depth8;

        private int depth10;

        private int depth20;

        private int depth30;

        private int depth50;

    }


}
