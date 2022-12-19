package app.adoneadmin.dto.estimate.response;

import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.domain.image.bidingEstimate.PastConstructionImage;
import app.adoneadmin.dto.image.ImageDto;
import app.adoneadmin.dto.signboard.response.SignboardResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "견적 조회 반환 객체(입찰 견적서)")
public class EstimateResponseDto {

    @ApiModelProperty(value = "견적서 id")
    private Long bidingEstimateId;

    @ApiModelProperty(value = "견적업체명")
    private String companyName;

    @ApiModelProperty(value = "총 공급가액")
    private int totalSupplyCost;

    @ApiModelProperty(value = "합계금액(부가세 포함)")
    private int totalSurtaxCost;

    @ApiModelProperty(value = "견적 등록일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime regDateTime;

    public static EstimateResponseDto from(BidingEstimate bidingEstimate){

        return new EstimateResponseDtoBuilder()
                .bidingEstimateId(bidingEstimate.getBidingEstimateId())
                .companyName(bidingEstimate.getMember().getCompanyName())
                .totalSupplyCost(getSupplyCost(bidingEstimate))
                .totalSurtaxCost((int) Math.round(getSupplyCost(bidingEstimate) * 1.1))
                .regDateTime(bidingEstimate.getRegDateTime())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(description = "견적 내용 + 단가 객체")
    public static class BidingEstimateDetail{

        @ApiModelProperty(value = "간판 종류", example = "전면간판")
        private int signboardType;

        @ApiModelProperty(value = "간판 형태", example = "프레임")
        private int signboardForm;

        @ApiModelProperty(value = "간판 재질", example = "알루미늄")
        private int materialType;

        @ApiModelProperty(value = "채널 종류", example = "전광채널")
        private int channelType;

        @ApiModelProperty(value = "채널 재질", example = "알루미늄")
        private int channelMaterialType;

        @ApiModelProperty(value = "간판 단가 객체")
        private SignboardResponseDto.SignboardPrice signboardPrice;

        @ApiModelProperty(value = "채널 단가 객체")
        private SignboardResponseDto.ChannelPrice channelPrice;

        @NotNull
        @ApiModelProperty(value = "시공비")
        private Integer constructionCost;

        @NotNull
        @ApiModelProperty(value = "장비 사용료")
        private Integer equipmentCost;

        @NotNull
        @ApiModelProperty(value = "인허가대행비")
        private Integer authorizationCost;

        @NotNull
        @ApiModelProperty(value = "기타비용")
        private Integer etcCost;

        @NotBlank
        @ApiModelProperty(value = "A/S 보증기간", required = true)
        private String warrantyPeriod;

        @ApiModelProperty(value = "비슷한 스타일 시공사진 리스트")
        private List<ImageDto> pastConstructionImageDtoList;
        @ApiModelProperty(value = "추가 사항")
        private String etcContent;

        @ApiModelProperty(value = "총 공급가액")
        private int totalSupplyCost;

        @ApiModelProperty(value = "부가세")
        private int surtax;

        @ApiModelProperty(value = "합계금액(부가세 포함)")
        private int totalCost;

        public BidingEstimateDetail(BidingEstimate bidingEstimate){

            // 비슷한 스타일 시공사진 리스트
            List<ImageDto> pastConstructionImageDtoList = new ArrayList<>();

            List<PastConstructionImage> pastConstructionImageList = bidingEstimate.getPastConstructionImageList();
            if(pastConstructionImageList != null && !pastConstructionImageList.isEmpty()){
                for(PastConstructionImage image : pastConstructionImageList){
                    pastConstructionImageDtoList.add(new ImageDto(image.getImageId(), image.getImageUrl()));
                }
            }


            // 간판+채널 단가
            this.signboardPrice = SignboardResponseDto.SignboardPrice.build(bidingEstimate);
            this.channelPrice = SignboardResponseDto.ChannelPrice.build(bidingEstimate);

            // 간판 정보
            this.signboardType = bidingEstimate.getSignboardDesignContractor().getSignboard().getSignboardType();
            this.signboardForm = bidingEstimate.getSignboardDesignContractor().getSignboard().getSignboardForm();
            this.materialType = bidingEstimate.getSignboardDesignContractor().getSignboard().getMaterialType();

            // 채널 정보
            this.channelType = bidingEstimate.getSignboardDesignContractor().getSignboard().getChannelType();
            this.channelMaterialType = bidingEstimate.getSignboardDesignContractor().getSignboard().getChannelMaterialType();

            this.constructionCost = bidingEstimate.getConstructionCost();
            this.equipmentCost = bidingEstimate.getEquipmentCost();
            this.authorizationCost = bidingEstimate.getAuthorizationCost();
            this.etcCost = bidingEstimate.getEtcCost();
            this.warrantyPeriod = bidingEstimate.getWarrantyPeriod();
            this.etcContent = bidingEstimate.getEtcContent();
            this.pastConstructionImageDtoList = pastConstructionImageDtoList;
            this.totalSupplyCost = getSupplyCost(bidingEstimate);
            this.surtax = ((int) Math.round(getSupplyCost(bidingEstimate) * 0.1));
            this.totalCost = ((int) Math.round(getSupplyCost(bidingEstimate) * 1.1));
        }

    }

    private static int getSupplyCost(BidingEstimate bidingEstimate){

        return bidingEstimate.getAuthorizationCost() + bidingEstimate.getConstructionCost()
                + bidingEstimate.getEtcCost() + bidingEstimate.getEquipmentCost();
    }


}
