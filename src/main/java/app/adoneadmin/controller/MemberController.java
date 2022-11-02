package app.adoneadmin.controller;

import app.adoneadmin.dto.member.response.MemberResponseDto;
import app.adoneadmin.dto.notice.response.NoticeCreateResponseDto;
import app.adoneadmin.dto.notice.response.NoticeResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "members", description = "회원 API")
@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
@Slf4j
public class MemberController {

//    @Tag(name = "members")
//    @ApiOperation(value = "회원 리스트 조회 api")
//    @GetMapping(value="")
//    public ResponseEntity<MemberResponseDto.GroupById> getAllNotice(// @ApiIgnore @PrincipalDetail Long memberId,
//                                                                     ){
//        List<NoticeResponseDto> noticeResponseDtos = new ArrayList<>();
//        return ResponseEntity.ok(new MemberResponseDto.GroupById(noticeResponseDtos));
//    }

}
