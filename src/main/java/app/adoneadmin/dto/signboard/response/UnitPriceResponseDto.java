package app.adoneadmin.dto.signboard.response;

import app.adoneadmin.domain.estimate.BidingEstimate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel(description = "품목, 수량, 단가 정보 객체")
public class UnitPriceResponseDto {

    @ApiModelProperty(value = "품목", example = "조명용")
    private String item;

    @ApiModelProperty(value = "수량", example = "108")
    private int quantity;

    @ApiModelProperty(value = "단가", example = "21000")
    private int price;

    @ApiModelProperty(value = "소계", example = "32400")
    private int total;


    public static UnitPriceResponseDto createChLighting(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item(String.valueOf(bidingEstimate.getSignboardDesignContractor().getTypography().getMainTextLightType()))
                .quantity(bidingEstimate.getChLightingQuantity())
                .price(bidingEstimate.getChLightingPrice())
                .total(bidingEstimate.getChLightingQuantity() * bidingEstimate.getChLightingPrice())
                .build();
    }

    public static UnitPriceResponseDto createChDivision(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item(String.valueOf(bidingEstimate.getSignboardDesignContractor().getTypography().getTextDivision()))
                .quantity(bidingEstimate.getChDivisionQuantity())
                .price(bidingEstimate.getChDivisionPrice())
                .total(bidingEstimate.getChDivisionQuantity() * bidingEstimate.getChDivisionPrice())
                .build();
    }

    public static UnitPriceResponseDto createChSize(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item(String.valueOf(bidingEstimate.getSignboardDesignContractor().getTypography().getMainTextFontSize()))
                .quantity(bidingEstimate.getChSizeQuantity())
                .price(bidingEstimate.getChSizePrice())
                .total(bidingEstimate.getChSizeQuantity() * bidingEstimate.getChSizePrice())
                .build();
    }

    public static UnitPriceResponseDto createChColor(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item("기본색 외 도장")
                .quantity(bidingEstimate.getChColorQuantity())
                .price(bidingEstimate.getChColorPrice())
                .total(bidingEstimate.getChColorQuantity() * bidingEstimate.getChColorPrice())
                .build();
    }

    public static UnitPriceResponseDto createSbLighting(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item(String.valueOf(bidingEstimate.getSignboardDesignContractor().getSignboard().getLightType()))
                .price(bidingEstimate.getSbLightingPrice())
                .total(bidingEstimate.getSbLightingPrice())
                .build();
    }

    public static UnitPriceResponseDto createSbDivision(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item(String.valueOf(bidingEstimate.getSignboardDesignContractor().getSignboard().getVertical()))
                .price(bidingEstimate.getSbDivisionPrice())
                .total(bidingEstimate.getSbDivisionPrice())
                .build();
    }

    public static UnitPriceResponseDto createSbSize(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item(String.valueOf(bidingEstimate.getSignboardDesignContractor().getSignboard().getWidth()))
                .price(bidingEstimate.getSbSizePrice())
                .total(bidingEstimate.getSbSizePrice())
                .build();
    }


    public static UnitPriceResponseDto createSbColor(BidingEstimate bidingEstimate){

        return UnitPriceResponseDto.builder()
                .item("기본색 외 도장")
                .price(bidingEstimate.getSbColorPrice())
                .total(bidingEstimate.getSbColorPrice())
                .build();
    }

}
