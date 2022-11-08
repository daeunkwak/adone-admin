package app.adoneadmin.controller;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.member.request.MemberUpdateRequestDto;
import app.adoneadmin.dto.member.response.MemberAuthResponseDto;
import app.adoneadmin.dto.member.response.MemberDetailResponseDto;
import app.adoneadmin.dto.member.response.MemberResponseDto;
import app.adoneadmin.service.image.ImageService;
import app.adoneadmin.service.member.MemberService;
import app.adoneadmin.vo.member.MemberDetailResponseVo;
import app.adoneadmin.vo.member.MemberUpdateVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "members", description = "회원 api")
@RequestMapping("/api/members")
@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ModelMapper modelMapper;
    private final ImageService imageService;

    @Tag(name = "members")
    @ApiOperation(value = "시공사 회원 리스트 조회 api")
    @GetMapping(value="")
    public ResponseEntity<MemberResponseDto.GroupById> getMemberList(){

        List<MemberResponseDto> memberResponseDtoList =
                memberService.getMemberList().stream().map(MemberResponseDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(new MemberResponseDto.GroupById(memberResponseDtoList));
    }

    @Tag(name = "members")
    @ApiOperation(value = "시공사 회원 상세 조회 api")
    @GetMapping(value="/{memberId}")
    public ResponseEntity<MemberDetailResponseDto> getMemberList(@PathVariable("memberId") Long memberId){

        MemberDetailResponseVo member = memberService.getMemberDetail(memberId);    // TODO : 이걸 몇 개로 쪼개는게 맞을까
        MemberDetailResponseDto memberDetailResponseDto = MemberDetailResponseDto.from(member);
        return ResponseEntity.ok(memberDetailResponseDto);
    }

    @Tag(name = "members")
    @ApiOperation(value = "시공사 회원 승인 api", notes = "이미 승인된 회원 -> exception")
    @PatchMapping(value = "/auth/{memberId}")
    public ResponseEntity<MemberAuthResponseDto> updateAuth(@PathVariable("memberId") Long memberId) {

        Member member = memberService.updateAuth(memberId);
        MemberAuthResponseDto memberAuthResponseDto = new MemberAuthResponseDto(memberId, member.getIsAuthorized());
        return ResponseEntity.ok(memberAuthResponseDto);
    }

    @Tag(name = "members")
    @ApiOperation(value = "시공사 회원 검색 api")
    @GetMapping(value="/search")
    public ResponseEntity<MemberResponseDto.GroupById> getMemberSearch(@RequestParam("searchWord") String searchWord){

        List<MemberResponseDto> memberResponseDtoList =
                memberService.getMemberSearch(searchWord).stream().map(MemberResponseDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(new MemberResponseDto.GroupById(memberResponseDtoList));
    }

    @Tag(name = "members")
    @ApiOperation(value = "시공사 회원정보 수정 api")
    @PatchMapping(value = "/{memberId}")
    public ResponseEntity<CommonApiResult> updateMemberInfo(@PathVariable("memberId") Long memberId,
                                                            @RequestPart(value = "memberUpdateRequestDto") MemberUpdateRequestDto req,
                                                            @RequestPart(value = "companyRegisterImage") MultipartFile companyRegisterImage,
                                                            @RequestPart(value = "memberImage") MultipartFile memberImage) throws IOException {

        final MemberUpdateVo memberUpdateVo = new MemberUpdateVo();
        modelMapper.map(req, memberUpdateVo);
        memberService.updateMemberInfo(memberUpdateVo, memberId);
        imageService.updateMemberImage(memberId, memberImage);
        imageService.updateCompanyRegisterImage(memberId, companyRegisterImage);
        return ResponseEntity.ok(CommonApiResult.createOk("시공사 회원정보가 업데이트 되었습니다."));
    }

}
