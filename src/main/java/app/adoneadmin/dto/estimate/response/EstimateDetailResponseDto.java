package app.adoneadmin.dto.estimate.response;

import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.domain.image.requestEstimate.ReferenceImage;
import app.adoneadmin.domain.member.Member;
import app.adoneadmin.dto.businessCategory.BusinessChildCategoryResDto;
import app.adoneadmin.dto.image.ImageDto;
import app.adoneadmin.dto.member.response.MemberResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "견적 상세 조회 반환 객체(입찰 견적서)")
public class EstimateDetailResponseDto {

    // TODO : refactor

    // 수요자
    private UserInfo userInfo;

    // 공급자
    private MemberResponseDto.SimpleMemberResponse contractorInfo;

    // 견적 내용 + 간판, 채널 단가
    private EstimateResponseDto.BidingEstimateDetail bidingEstimateResponse;

    // 설치 정보
    private RequestEstimateInfo requestEstimateInfo;

    public EstimateDetailResponseDto(BidingEstimate bidingEstimate){

        this.userInfo  = new UserInfo(bidingEstimate.getRequestEstimate().getMember());
        userInfo.setRegDateTime(bidingEstimate.getRequestEstimate().getRegDateTime());

        this.contractorInfo = new MemberResponseDto.SimpleMemberResponse(bidingEstimate.getMember());
        this.bidingEstimateResponse = new EstimateResponseDto.BidingEstimateDetail(bidingEstimate);
        this.requestEstimateInfo = new RequestEstimateInfo(bidingEstimate);
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(description = "수요자 객체")
    public static class UserInfo{

        @ApiModelProperty(value = "성명")
        private String username;

        @ApiModelProperty(value = "연락처")
        private String phone;

        @ApiModelProperty(value = "업체명")
        private String companyName;

        @ApiModelProperty(value = "매장지역")
        private String storeLocation;

        @ApiModelProperty(value = "업종")
        private BusinessChildCategoryResDto businessChildCategoryResDto;

        @ApiModelProperty(value = "요청일")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime regDateTime;

        public UserInfo(Member member){
            this.username = member.getUsername();
            this.phone = member.getPhone();
            this.companyName = member.getCompanyName();
            this.storeLocation = member.getStoreLocation();
            this.businessChildCategoryResDto = new BusinessChildCategoryResDto(member.getBusinessCategory());
        }

    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(description = "설치 정보 객체")
    public static class RequestEstimateInfo{

        @ApiModelProperty(value = "시공희망일", example = "2주 이내")
        private String desiredDate;

        @ApiModelProperty(value = "설치층수")
        private String floor;

        @ApiModelProperty(value = "철거 여부")
        private Boolean isDemolish = false;

        @ApiModelProperty(value = "요청 사항")
        private String requestedContent;

        @ApiModelProperty(value = "원하는 스타일 간판 사진 리스트")
        private List<ImageDto> referenceImageDtoList;

        @ApiModelProperty(value = "시공할 건물 사진 리스트")
        private List<ImageDto> buildingImageDtoList;

        public RequestEstimateInfo(BidingEstimate bidingEstimate){

            //원하는 간판 스타일 이미지 리스트
            List<ImageDto> referenceImageDtoList = new ArrayList<>();
            if(bidingEstimate.getRequestEstimate().getReferenceImageList() != null
                    && !bidingEstimate.getRequestEstimate().getReferenceImageList().isEmpty()){

                for(ReferenceImage referenceImage : bidingEstimate.getRequestEstimate().getReferenceImageList()){
                    referenceImageDtoList.add(new ImageDto(referenceImage.getImageId(), referenceImage.getImageUrl()));
                }
            }

            this.desiredDate = bidingEstimate.getRequestEstimate().getDesiredDate();
            this.floor = bidingEstimate.getRequestEstimate().getFloor();
            this.isDemolish = bidingEstimate.getRequestEstimate().getIsDemolish();
            this.requestedContent = bidingEstimate.getRequestEstimate().getRequestedTerm();
            this.referenceImageDtoList = referenceImageDtoList;
        }

    }


}
