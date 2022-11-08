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

    @ApiModelProperty(value = "유저 id")
    private Long memberId;

    @ApiModelProperty(value = "회사명")
    private String companyName;

    @ApiModelProperty(value = "대표자명")
    private String representName;

    @ApiModelProperty(value = "사업자번호")
    private String companyRegisterNumber;

    @ApiModelProperty(value = "대표자 연락처")
    private String representPhone;

    @ApiModelProperty(value = "업장주소지")
    private String fullAddress;

    @ApiModelProperty(value = "승인여부")
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
    @ApiModel(description = "회원 리스트 조회 반환 객체")
    public static class GroupById{

        @ApiModelProperty(value = "id : MemberResponseDto")
        private final Map<Long, MemberResponseDto> memberResponseDtoGroupById;

        public GroupById(List<MemberResponseDto> memberResponseDtoList){
            Map<Long, MemberResponseDto> result = new HashMap<>();
            for(MemberResponseDto memberResponseDto : memberResponseDtoList){
                result.put(memberResponseDto.getMemberId(), memberResponseDto);
            }
            this.memberResponseDtoGroupById = result;
        }
    }

}
