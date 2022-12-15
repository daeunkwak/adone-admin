package app.adoneadmin.dto.signboard.response;

import app.adoneadmin.domain.estimate.BidingEstimate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@ApiModel(value = "견적서 작성시 간판/채널 단가 반환 객체")
public class SignboardResponseDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ApiModel(description = "간판 정보 객체")
    public static class SignboardPrice{

        @ApiModelProperty(value = "조명 객체")
        private UnitPriceResponseDto lighting;

        @ApiModelProperty(value = "구분 객체")
        private UnitPriceResponseDto division;

        @ApiModelProperty(value = "크기 객체")
        private UnitPriceResponseDto size;

        @ApiModelProperty(value = "색상 객체")
        private UnitPriceResponseDto color;


        public static SignboardPrice build(BidingEstimate bidingEstimate){

            return SignboardPrice.builder()
                    .lighting(UnitPriceResponseDto.createSbLighting(bidingEstimate))
                    .division(UnitPriceResponseDto.createSbDivision(bidingEstimate))
                    .size(UnitPriceResponseDto.createSbSize(bidingEstimate))
                    .color(UnitPriceResponseDto.createSbColor(bidingEstimate))
                    .build();
        }


    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ApiModel(description = "채널 정보 객체")
    public static class ChannelPrice{

        @ApiModelProperty(value = "조명 객체")
        private UnitPriceResponseDto lighting;

        @ApiModelProperty(value = "구분 객체")
        private UnitPriceResponseDto division;

        @ApiModelProperty(value = "크기 객체")
        private UnitPriceResponseDto size;

        @ApiModelProperty(value = "색상 객체")
        private UnitPriceResponseDto color;


        public static ChannelPrice build(BidingEstimate bidingEstimate){

            return ChannelPrice.builder()
                    .lighting(UnitPriceResponseDto.createChLighting(bidingEstimate))
                    .division(UnitPriceResponseDto.createChDivision(bidingEstimate))
                    .size(UnitPriceResponseDto.createChSize(bidingEstimate))
                    .color(UnitPriceResponseDto.createChColor(bidingEstimate))
                    .build();
        }

    }

}