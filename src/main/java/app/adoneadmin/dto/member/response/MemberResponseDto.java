package app.adoneadmin.dto.member.response;

import app.adoneadmin.domain.member.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "회원 조회 반환 객체")
public class MemberResponseDto {

    @ApiModelProperty(value = "유저 id", example = "2")
    private Long memberId;

    @ApiModelProperty(value = "회사명", example = "모협조")
    private String companyName;

    @ApiModelProperty(value = "대표자명", example = "최원서")
    private String representName;

    @ApiModelProperty(value = "사업자번호", example = "123123")
    private String companyRegisterNumber;

    @ApiModelProperty(value = "대표자 연락처", example = "010-2222-2222")
    private String representPhone;

    @ApiModelProperty(value = "업장주소지", example = "노원구 인덕대")
    private String fullAddress;

    @ApiModelProperty(value = "승인여부", example = "true/false")
    private Boolean isAuthorized;   // TODO : ADMIN <-> isAuthorized - false 기본값으로 설정해주기

    public static MemberResponseDto from(Member member){
        return new MemberResponseDtoBuilder()
                .memberId(member.getMemberId())
                .companyName(member.getCompanyName())
                .representName(member.getRepresentName())
                .companyRegisterNumber(member.getCompanyRegistrationNumber())
                .representPhone(member.getRepresentPhone())
                .fullAddress(member.getFullAddress())
                .isAuthorized(member.getIsAuthorized())
                .build();
    }

    @Getter
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(description = "간단한 회원 조회 반환 객체")
    public static class SimpleMemberResponse {

        @ApiModelProperty(value = "상호명", example = "모협조")
        private String companyName;

        @ApiModelProperty(value = "성명", example = "최원서")
        private String representName;

        @ApiModelProperty(value = "사업자등록번호", example = "123123")
        private String companyRegisterNumber;

        @ApiModelProperty(value = "전화번호", example = "010-2222-2222")
        private String representPhone;

        @ApiModelProperty(value = "주소", example = "노원구 인덕대 017호")
        private String fullAddress;

        public SimpleMemberResponse(Member member){
            this.companyName = member.getCompanyName();
            this.representName = member.getRepresentName();
            this.companyRegisterNumber = member.getCompanyRegistrationNumber();
            this.representPhone = member.getRepresentPhone();
            this.fullAddress = member.getFullAddress();
        }
    }

}
