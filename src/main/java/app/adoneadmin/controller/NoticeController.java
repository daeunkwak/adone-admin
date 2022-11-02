package app.adoneadmin.controller;

import app.adoneadmin.dto.notice.request.NoticeCreateRequestDto;
import app.adoneadmin.dto.notice.response.NoticeCreateResponseDto;
import app.adoneadmin.dto.notice.response.NoticeResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Tag(name = "notice", description = "공지사항 API")
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@RestController
@Slf4j
public class NoticeController {

//    @Tag(name = "notice")
//    @ApiOperation(value = "공지사항 생성 api")
//    @PostMapping(value="")
//    public ResponseEntity<NoticeCreateResponseDto> createNotice(//@ApiIgnore @PrincipalDetail Long memberId,
//                                                                @RequestBody @Valid NoticeCreateRequestDto req){
//
//        //return ResponseEntity.ok(NoticeCreateResponseDto.create(noticeId), HttpStatus.CREATED);
//        return new ResponseEntity<>(NoticeCreateResponseDto.create(1L), HttpStatus.CREATED);
//    }
//
//    @Tag(name = "notice")
//    @ApiOperation(value = "공지사항 리스트 조회 api")
//    @GetMapping(value="")
//    public ResponseEntity<NoticeResponseDto.GroupById> getAllNotice(// @ApiIgnore @PrincipalDetail Long memberId,
//                                                                    @RequestBody @Valid NoticeCreateResponseDto req){
//        List<NoticeResponseDto> noticeResponseDtos = new ArrayList<>();
//        return ResponseEntity.ok(new NoticeResponseDto.GroupById(noticeResponseDtos));
//    }
//
//    @Tag(name = "notice")
//    @ApiOperation(value = "공지사항 단건 조회 api")
//    @GetMapping(value="/{noticeId}")
//    public ResponseEntity<NoticeResponseDto> getNotice(// @ApiIgnore @PrincipalDetail Long memberId,
//                                                                 @PathVariable("noticeId") Long noticeId){
//        List<NoticeResponseDto> noticeResponseDtos = new ArrayList<>();
//        return ResponseEntity.ok(NoticeResponseDto.from(notice));
//    }

    // 공지사항 검색 NoticeResponseDto

    // 공지사항 수정 NoticeResponseDto.GroupById

    // 공지사항 삭제 OK

}
