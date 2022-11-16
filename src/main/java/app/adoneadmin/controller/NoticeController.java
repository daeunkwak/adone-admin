package app.adoneadmin.controller;

import app.adoneadmin.domain.file.notice.NoticeFile;
import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.notice.request.NoticeDeleteRequestDto;
import app.adoneadmin.dto.notice.request.NoticeRequestDto;
import app.adoneadmin.dto.notice.response.NoticeCreateResponseDto;
import app.adoneadmin.dto.notice.response.NoticeResponseDto;
import app.adoneadmin.security.auth.PrincipalDetails;
import app.adoneadmin.service.file.FileService;
import app.adoneadmin.service.notice.NoticeService;
import app.adoneadmin.vo.member.MemberUpdateVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Tag(name = "notification", description = "공지사항 api")
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@RestController
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;
    private final FileService fileService;

    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 생성 api")
    @PostMapping(value="")
    public ResponseEntity<NoticeCreateResponseDto> createNotice(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                                @RequestBody @Valid NoticeRequestDto req){

        log.info("NoticeCreateRequestDto ::: " + req);
        Notice notice = noticeService.createNotice(principalDetails.getMember(), req.getNoticeContent(), req.getNoticeName());
        return new ResponseEntity<>(NoticeCreateResponseDto.create(notice.getNoticeId()), HttpStatus.CREATED);
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 파일 업로드 api")
    @PostMapping(value="/file/{noticeId}")
    public ResponseEntity<CommonApiResult> createNoticeFile(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                            @RequestPart(value = "noticeFiles") List<MultipartFile> noticeFiles,
                                                            @PathVariable("noticeId") Long noticeId) throws IOException {

        log.info("noticeFiles ::: " + noticeFiles);
        fileService.uploadNoticeFiles(noticeFiles, noticeId);
        return ResponseEntity.ok(CommonApiResult.createOk("공지사항이 첨부파일이 업로드 되었습니다."));
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 리스트 조회 api")
    @GetMapping(value="")
    public ResponseEntity<List<NoticeResponseDto>> getAllNotice(){

        List<Notice> noticeList = noticeService.getNoticeList();

        List<NoticeResponseDto> result = new ArrayList<>();
        for(Notice notice : noticeList){
            if(notice.getNoticeFileList() != null){
                result.add(new NoticeResponseDto(notice, notice.getNoticeFileList()));
            }
        }
        return ResponseEntity.ok(result);
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 상세 조회 api")
    @GetMapping(value="/{noticeId}")
    public ResponseEntity<NoticeResponseDto> getNotice(@PathVariable("noticeId") Long noticeId){

        Notice notice = noticeService.getNotice(noticeId);
        return ResponseEntity.ok(NoticeResponseDto.from(notice));
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 검색 api")
    @GetMapping(value="/search")
    public ResponseEntity<List<NoticeResponseDto>> getNoticeSearch(@RequestParam("searchWord") String searchWord){

        List<Notice> noticeList = noticeService.getNoticeSearch(searchWord);

        List<NoticeResponseDto> result = new ArrayList<>();
        for(Notice notice : noticeList){
            if(notice.getNoticeFileList() != null){
                result.add(new NoticeResponseDto(notice, notice.getNoticeFileList()));
            }
        }
        return ResponseEntity.ok(result);
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 수정 api")
    @PatchMapping(value = "/{noticeId}")
    public ResponseEntity<CommonApiResult> updateNotice(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                        @PathVariable("noticeId") Long noticeId,
                                                        @RequestPart(value = "req") NoticeRequestDto req){

        noticeService.updateNotice(principalDetails.getMember().getMemberId(), noticeId, req.getNoticeContent(), req.getNoticeName());
        return ResponseEntity.ok(CommonApiResult.createOk("공지사항이 업데이트 되었습니다."));
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 수정 api")
    @PatchMapping(value = "/{noticeId}")
    public ResponseEntity<CommonApiResult> updateNotice(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                        @PathVariable("noticeId") Long noticeId,
                                                        @RequestBody @Valid NoticeRequestDto req){

        noticeService.updateNotice(principalDetails.getMember().getMemberId(), noticeId, req.getNoticeContent(), req.getNoticeName());
        return ResponseEntity.ok(CommonApiResult.createOk("공지사항이 업데이트 되었습니다."));
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 첨부파일 수정 api")
    @PatchMapping(value = "/file/{noticeId}")
    public ResponseEntity<CommonApiResult> updateNoticeFile(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                                            @PathVariable("noticeId") Long noticeId,
                                                            @RequestPart(value = "noticeFiles") List<MultipartFile> noticeFiles) throws IOException {

        fileService.updateNoticeFiles(principalDetails.getMember().getMemberId(), noticeFiles, noticeId);
        return ResponseEntity.ok(CommonApiResult.createOk("공지사항 첨부파일이 업데이트 되었습니다."));
    }


    @Tag(name = "notification")
    @ApiOperation(value = "공지사항 삭제 api")
    @DeleteMapping("")
    public ResponseEntity<CommonApiResult> deleteNotice(@RequestBody NoticeDeleteRequestDto req){

        noticeService.deleteNotice(req.getNoticeIdList());
        return ResponseEntity.ok(CommonApiResult.createOk("공지사항이 삭제 되었습니다."));
    }

}
