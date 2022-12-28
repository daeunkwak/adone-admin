package app.adoneadmin.controller.signboard;

import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.dto.signboard.StandardCostDto;
import app.adoneadmin.dto.signboard.request.StandardCostRequestDto;
import app.adoneadmin.service.SignboardService;
import app.adoneadmin.vo.signboard.StandardCostVo;
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

@Tag(name = "signboards/front-frame", description = "전면 프레임 간판 단가 api")
@RequestMapping("/api/signboards/front-frame")
@RequiredArgsConstructor
@RestController
@Slf4j
public class FrontFrameController {

    private final SignboardService signboardService;
    private final ModelMapper modelMapper;


    @Tag(name = "signboards/front-frame", description = "간판 - 전면 프레임 단가 api")
    @ApiOperation(value = "전면 프레임 단가 추가 api", notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]")
    @PostMapping(value="")
    public ResponseEntity<?> createFrontFrame(@RequestBody @Valid List<StandardCostRequestDto> req,
                                              @RequestParam("materialType") String materialType){

        List<StandardCostVo> standardCostVoList = req.stream().map(dto -> modelMapper.map(dto, StandardCostVo.class)).collect(Collectors.toList());
        signboardService.createFrontFrame(materialType, standardCostVoList);

        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "signboards/front-frame", description = "간판 - 전면 프레임 단가 api")
    @ApiOperation(value = "전면 프레임 단가 조회 api",
            notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]\n" + "- -1은 입력되지 않은 값들입니다.")
    @GetMapping(value="")
    public ResponseEntity<List<StandardCostDto>> getFrontFrame(@RequestParam("materialType") String materialType){

        List<StandardCostDto> result = signboardService.getFrontFrame(materialType).stream().map(standardCostVo -> {
            return modelMapper.map(standardCostVo, StandardCostDto.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "signboards/front-frame", description = "간판 - 전면 프레임 단가 api")
    @ApiOperation(value = "전면 프레임 단가 수정 api",
            notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateFrontFrame(@RequestBody @Valid List<StandardCostRequestDto.UpdateStandardCost> req,
                                                            @RequestParam("materialType") String materialType){

        List<StandardCostVo> standardCostVos = req.stream().map(dto -> modelMapper.map(dto, StandardCostVo.class)).collect(Collectors.toList());
        signboardService.updateFrontFrame(standardCostVos, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "signboards/front-frame", description = "간판 - 전면 프레임 단가 api")
    @ApiOperation(value = "전면 프레임 단가 삭제 api",
            notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteFrontFrame(@RequestBody @Valid DeleteRequestDto req,
                                                            @RequestParam("materialType") String materialType){

        signboardService.deleteFrontFrame(req, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));
    }

}
