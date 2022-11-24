package app.adoneadmin.controller;

import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.signboard.request.StandardMaterialDeleteRequestDto;
import app.adoneadmin.dto.signboard.request.StandardMaterialRequestDto;
import app.adoneadmin.dto.signboard.StandardMaterialDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.service.signboard.SignboardService;
import app.adoneadmin.vo.signboard.StandardMaterialVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "signboards", description = "간판 단가 api")
@RequestMapping("/api/signboards")
@RequiredArgsConstructor
@RestController
@Slf4j
public class SignboardController {

    private final SignboardService signboardService;
    private final ModelMapper modelMapper;


    @Tag(name = "signboards")
    @ApiOperation(value = "전면 트러스/돌출 프레임/지주 프레임 규격에 따른 단가 추가 api",
                  notes = "- signboardType <-> 전면 트러스 : 1, 돌출 프레임 : 2, 지주 프레임 : 3\n" +
                          "- -1은 입력되지 않은 값들입니다.")
    @PostMapping(value="")
    public ResponseEntity<CommonApiResult> createStandardMaterial(@RequestBody @Valid List<StandardMaterialRequestDto> req,
                                                                  @RequestParam("signboardType") int signboardType){

        List<StandardMaterialVo> standardMaterialVoList =
                req.stream().map(dto -> modelMapper.map(dto, StandardMaterialVo.class)).collect(Collectors.toList());

        switch (signboardType){
            case 1 :
                signboardService.createFrontTruss(standardMaterialVoList);
            case 2 :
                signboardService.createProtrudingFrame(standardMaterialVoList);
            case 3 :
                signboardService.createHoldingFrame(standardMaterialVoList);
        }

        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "signboards")
    @ApiOperation(value = "전면 트러스/돌출 프레임/지주 프레임 규격에 따른 단가표 조회 api",
                  notes = "- signboardType <-> 전면 트러스 : 1, 돌출 프레임 : 2, 지주 프레임 : 3\n" +
                          "- -1은 입력되지 않은 값들입니다.")
    @GetMapping(value="")
    public ResponseEntity<List<StandardMaterialDto>> getStandardMaterial(@RequestParam("signboardType") int signboardType){

        switch (signboardType){
            case 1 :
                List<StandardMaterialDto> frontTruss =
                        signboardService.getFrontTruss().stream().map(StandardMaterialDto::new).collect(Collectors.toList());
                return ResponseEntity.ok(frontTruss);
            case 2 :
                List<StandardMaterialDto> protrudingFrame =
                        signboardService.getProtrudingFrame().stream().map(StandardMaterialDto::new).collect(Collectors.toList());
                return ResponseEntity.ok(protrudingFrame);
            case 3 :
                List<StandardMaterialDto> holdingFrame =
                        signboardService.getHoldingFrame().stream().map(StandardMaterialDto::new).collect(Collectors.toList());
                return ResponseEntity.ok(holdingFrame);

        }

        throw new CustomException("잘못된 signboardType 입니다.");
    }


    @Tag(name = "signboards")
    @ApiOperation(value = "전면 트러스/돌출 프레임/지주 프레임 규격에 따른 단가표 수정 api",
            notes = "- signboardType <-> 전면 트러스 : 1, 돌출 프레임 : 2, 지주 프레임 : 3\n" +
                    "- -1은 입력되지 않은 값들입니다.")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateStandardMaterial(@RequestBody @Valid List<StandardMaterialDto> req,
                                                                  @RequestParam("signboardType") int signboardType){

        List<StandardMaterialVo> standardMaterialVoList =
                req.stream().map(dto -> modelMapper.map(dto, StandardMaterialVo.class)).collect(Collectors.toList());

        switch (signboardType){
            case 1 :
                signboardService.updateFrontTruss(standardMaterialVoList);
            case 2 :
                signboardService.updateProtrudingFrame(standardMaterialVoList);
            case 3 :
                signboardService.updateHoldingFrame(standardMaterialVoList);
        }

        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "signboards")
    @ApiOperation(value = "전면 트러스/돌출 프레임/지주 프레임 규격에 따른 단가표 삭제 api",
            notes = "- signboardType <-> 전면 트러스 : 1, 돌출 프레임 : 2, 지주 프레임 : 3\n" +
                    "- -1은 입력되지 않은 값들입니다.")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteStandardMaterial(@RequestBody @Valid StandardMaterialDeleteRequestDto req,
                                                                  @RequestParam("signboardType") int signboardType){

        switch (signboardType){
            case 1 :
                for(long id : req.getIdList()) {
                    signboardService.deleteFrontTruss(id);
                }
            case 2 :
                for(long id : req.getIdList()) {
                    signboardService.deleteProtrudingFrame(id);
                }
            case 3 :
                for(long id : req.getIdList()) {
                    signboardService.deleteHoldingFrame(id);
                }
        }

        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 삭제되었습니다."));
    }


}

