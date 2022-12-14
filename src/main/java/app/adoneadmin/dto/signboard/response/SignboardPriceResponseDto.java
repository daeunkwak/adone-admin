package app.adoneadmin.dto.signboard.response;

import app.adoneadmin.domain.estimate.BidingEstimate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ApiModel(value = "견적서 작성시 간판/채널 단가 반환 객체")
public class SignboardPriceResponseDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ApiModel(description = "간판 단가 객체")
    public static class SignboardPrice{

        @ApiModelProperty(value = "조명")
        private int lighting;

        @ApiModelProperty(value = "구분")
        private int division;

        @ApiModelProperty(value = "크기")
        private int size;

        @ApiModelProperty(value = "색상")
        private int color;

        public SignboardPrice(BidingEstimate bidingEstimate){
            this.lighting = bidingEstimate.getChLightingPrice();
            this.division = bidingEstimate.getChDivisionPrice();
            this.size = bidingEstimate.getChSizePrice();
            this.color = bidingEstimate.getChColorPrice();
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ApiModel(description = "채널 단가 객체")
    public static class ChannelPrice{

        @ApiModelProperty(value = "조명")
        private int lighting;

        @ApiModelProperty(value = "구분")
        private int division;

        @ApiModelProperty(value = "크기")
        private int size;

        @ApiModelProperty(value = "색상")
        private int color;

        public ChannelPrice(BidingEstimate bidingEstimate){
            this.lighting = bidingEstimate.getChLightingPrice();
            this.division = bidingEstimate.getChDivisionPrice();
            this.size = bidingEstimate.getChSizePrice();
            this.color = bidingEstimate.getChColorPrice();
        }



    }

}