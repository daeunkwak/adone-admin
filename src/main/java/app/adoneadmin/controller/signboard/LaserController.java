package app.adoneadmin.controller.signboard;

import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.dto.signboard.LaserDto;
import app.adoneadmin.dto.signboard.request.LaserRequestDto;
import app.adoneadmin.service.SignboardService;
import app.adoneadmin.vo.signboard.LaserVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "signboards/laser", description = "실내 레이저 타공 단가 api")
@RequestMapping("/api/signboards/laser")
@RequiredArgsConstructor
@RestController
@Slf4j
public class LaserController {

    private final SignboardService signboardService;
    private final ModelMapper modelMapper;


    @Tag(name = "signboards/laser", description = "간판 - 실내 레이저 타공 단가 api")
    @ApiOperation(value = "실내 레이저 타공 단가 추가 api")
    @PostMapping(value="")
    public ResponseEntity<CommonApiResult> createLaser(@RequestBody @Valid List<LaserRequestDto> req){

        List<LaserVo> laserVos = req.stream().map(dto -> modelMapper.map(dto, LaserVo.class)).collect(Collectors.toList());
        signboardService.createLaser(laserVos);

        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "signboards/laser", description = "간판 - 실내 레이저 타공 단가 api")
    @ApiOperation(value = "실내 레이저 타공 단가 조회 api")
    @GetMapping(value="")
    public ResponseEntity<List<LaserDto>> getLaser(){

        List<LaserDto> result = signboardService.getLaser().stream().map(vo -> {
            return modelMapper.map(vo, LaserDto.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "signboards/laser", description = "간판 - 실내 레이저 타공 단가 api")
    @ApiOperation(value = "실내 레이저 타공 단가 수정 api")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateLaser(@RequestBody @Valid List<LaserRequestDto.Update> req){

        List<LaserVo> laserVos = req.stream().map(dto -> modelMapper.map(dto, LaserVo.class)).collect(Collectors.toList());
        signboardService.updateLaser(laserVos);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "signboards/laser", description = "간판 - 실내 레이저 타공 단가 api")
    @ApiOperation(value = "실내 레이저 타공 단가 삭제 api")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteLaser(@RequestBody @Valid DeleteRequestDto req){

        signboardService.deleteLaser(req);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));
    }


}
