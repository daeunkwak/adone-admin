package app.adoneadmin.controller;

import app.adoneadmin.dto.estimate.response.EstimateResponseDto;
import app.adoneadmin.dto.member.response.MemberResponseDto;
import app.adoneadmin.dto.notice.response.NoticeResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "estimates", description = "견적 API")
@RequestMapping("/estimates")
@RequiredArgsConstructor
@RestController
@Slf4j
public class EstimateController {

    @Tag(name = "estimates")
    @ApiOperation(value = "견적 리스트 조회 api")
    @GetMapping(value="")
    public ResponseEntity<EstimateResponseDto.GroupById> getAllNotice(// @ApiIgnore @PrincipalDetail Long memberId,
    ){
        List<NoticeResponseDto> noticeResponseDtos = new ArrayList<>();
        return ResponseEntity.ok(new MemberResponseDto.GroupById(noticeResponseDtos));
    }
}
