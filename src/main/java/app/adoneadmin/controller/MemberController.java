package app.adoneadmin.controller;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.image.ImageDto;
import app.adoneadmin.dto.member.request.MemberUpdateRequestDto;
import app.adoneadmin.dto.member.response.MemberAuthResponseDto;
import app.adoneadmin.dto.member.response.MemberDetailResponseDto;
import app.adoneadmin.dto.member.response.MemberResponseDto;
import app.adoneadmin.service.image.ImageService;
import app.adoneadmin.service.member.MemberService;
import app.adoneadmin.vo.member.MemberDetailResponseVo;
import app.adoneadmin.vo.member.MemberUpdateVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<List<MemberResponseDto>> getMemberList(){

        return ResponseEntity.ok(memberService.getMemberList().stream().map(MemberResponseDto::from).collect(Collectors.toList()));
    }


    @Tag(name = "members")
    @ApiOperation(value = "시공사 회원 상세 조회 api")
    @GetMapping(value="/{memberId}")
    public ResponseEntity<MemberDetailResponseDto> getMemberList(@PathVariable("memberId") Long memberId){

        MemberDetailResponseVo memberDetailResponseVo = memberService.getMemberDetail(memberId);

        ImageDto memberImageDto = new ImageDto();
        if(memberDetailResponseVo.getMember().getMemberImage() != null) {
            memberImageDto.setImageId(memberDetailResponseVo.getMemberImage().getImageId());
            memberImageDto.setImageUrl(memberDetailResponseVo.getMemberImage().getImageUrl());
        }

        ImageDto companyRegisterImageDto = new ImageDto();
        if(memberDetailResponseVo.getMember().getCompanyRegisterImage() != null) {
            companyRegisterImageDto.setImageId(memberDetailResponseVo.getCompanyRegisterImage().getImageId());
            companyRegisterImageDto.setImageUrl(memberDetailResponseVo.getCompanyRegisterImage().getImageUrl());
        }

        MemberDetailResponseDto memberDetailResponseDto = new MemberDetailResponseDto(memberDetailResponseVo.getMember(), memberImageDto, companyRegisterImageDto);
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
    public ResponseEntity<List<MemberResponseDto>> getMemberSearch(@RequestParam("searchWord") String searchWord){

        return ResponseEntity.ok(memberService.getMemberSearch(searchWord).stream().map(MemberResponseDto::from).collect(Collectors.toList()));
    }


    @Tag(name = "members")
    @ApiOperation(value = "시공사 회원정보 수정 api",
                  notes = "- companyRegisterImage, memberImage는 content-type=image/~,\n" +
                          "- memberUpdateRequestDto는 content-type=application/json으로 보내주시면 됩니다.\n" +
                          "- swagger에서는 인자별로 content-type 설정이 안돼서 swagger 내에서 테스트 하시면 415에러 발생합니다.")
    @PatchMapping(value = "/{memberId}")
    public ResponseEntity<CommonApiResult> updateMemberInfo(@PathVariable("memberId") Long memberId,
                                                            @RequestPart(value = "memberUpdateRequestDto") MemberUpdateRequestDto req,
                                                            @RequestPart(value = "companyRegisterImage") MultipartFile companyRegisterImage,
                                                            @RequestPart(value = "memberImage") MultipartFile memberImage) throws IOException {

        log.info("MemberUpdateRequestDto :::::" + req);
        log.info("companyRegisterImage ContentType ::::: " + companyRegisterImage.getContentType());
        log.info("memberImage ContentType ::::: " + memberImage.getContentType());

        final MemberUpdateVo memberUpdateVo = new MemberUpdateVo();

        modelMapper.map(req, memberUpdateVo);
        memberService.updateMemberInfo(memberUpdateVo, memberId);
        imageService.updateMemberImage(memberId, memberImage);
        imageService.updateCompanyRegisterImage(memberId, companyRegisterImage);
        return ResponseEntity.ok(CommonApiResult.createOk("시공사 회원정보가 업데이트 되었습니다."));
    }

}
