package app.adoneadmin.controller;

import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.dto.estimate.response.EstimateDetailResponseDto;
import app.adoneadmin.dto.estimate.response.EstimateResponseDto;
import app.adoneadmin.dto.member.response.MemberResponseDto;
import app.adoneadmin.dto.notice.response.NoticeResponseDto;
import app.adoneadmin.service.EstimateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "estimates", description = "견적 api")
@RequestMapping("/api/estimates")
@RequiredArgsConstructor
@RestController
@Slf4j
public class EstimateController {

    private final EstimateService estimateService;

    @Tag(name = "estimates")
    @ApiOperation(value = "견적 리스트 조회 api", notes = "- 입찰 진행 : WAIT, 계약,시공 완료 : COMPLETE")
    @GetMapping("")
    public ResponseEntity<List<EstimateResponseDto>> getEstimates(@RequestParam("status") String status){

        List<EstimateResponseDto> result = estimateService.getEstimates(status).stream().map(EstimateResponseDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "estimates")
    @ApiOperation(value = "견적 상세 조회 api", notes = "- 입찰 진행 : WAIT, 계약,시공 완료 : COMPLETE")
    @GetMapping("/{bidingEstimateId}")
    public ResponseEntity<EstimateDetailResponseDto> getEstimates(@PathVariable("bidingEstimateId") long bidingEstimateId){

        BidingEstimate bidingEstimate = estimateService.getEstimate(bidingEstimateId);
        return ResponseEntity.ok(new EstimateDetailResponseDto(bidingEstimate));
    }


    @Tag(name = "estimates")
    @ApiOperation(value = "견적 검색 api")
    @GetMapping("/search")
    public ResponseEntity<List<EstimateResponseDto>> searchEstimates(@RequestParam("searchWord") String searchWord,
                                                                     @RequestParam("status") String status){

        List<EstimateResponseDto> result = estimateService.searchEstimates(searchWord, status).stream().map(EstimateResponseDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "estimates")
    @ApiOperation(value = "견적 삭제 api")
    @DeleteMapping("")
    public ResponseEntity<CommonApiResult> deleteEstimates(@RequestBody DeleteRequestDto req){

        estimateService.deleteEstimates(req);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));

    }
    
}

