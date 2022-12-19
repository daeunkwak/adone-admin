package app.adoneadmin.controller.signboard;

import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.dto.signboard.LaserDto;
import app.adoneadmin.dto.signboard.PointDto;
import app.adoneadmin.dto.signboard.request.LaserRequestDto;
import app.adoneadmin.dto.signboard.request.PointRequestDto;
import app.adoneadmin.service.SignboardService;
import app.adoneadmin.vo.signboard.LaserVo;
import app.adoneadmin.vo.signboard.PointVo;
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


@Tag(name = "signboards/point", description = "돌출 포인트 간판 단가 api")
@RequestMapping("/api/signboards/point")
@RequiredArgsConstructor
@RestController
@Slf4j
public class PointController {


    private final SignboardService signboardService;
    private final ModelMapper modelMapper;


    @Tag(name = "signboards/point", description = "간판 - 돌출 포인트 간판 단가 api")
    @ApiOperation(value = "돌출 포인트 간판 단가 추가 api")
    @PostMapping(value="")
    public ResponseEntity<CommonApiResult> createPoint(@RequestBody @Valid List<PointRequestDto> req){

        List<PointVo> pointVos = req.stream().map(dto -> modelMapper.map(dto, PointVo.class)).collect(Collectors.toList());
        signboardService.createPoint(pointVos);

        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "signboards/point", description = "간판 - 돌출 포인트 간판 단가 api")
    @ApiOperation(value = "돌출 포인트 간판 단가 조회 api")
    @GetMapping(value="")
    public ResponseEntity<List<PointDto>> getPoint(){

        List<PointDto> result = signboardService.getPoint().stream().map(vo -> {
            return modelMapper.map(vo, PointDto.class);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "signboards/point", description = "간판 - 돌출 포인트 간판 단가 api")
    @ApiOperation(value = "돌출 포인트 간판 단가 수정 api")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updatePoint(@RequestBody @Valid List<PointDto> req){

        List<PointVo> pointVos = req.stream().map(dto -> modelMapper.map(dto, PointVo.class)).collect(Collectors.toList());
        signboardService.updatePoint(pointVos);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "signboards/point", description = "간판 - 돌출 포인트 간판 단가 api")
    @ApiOperation(value = "돌출 포인트 간판 단가 삭제 api")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deletePoint(@RequestBody @Valid DeleteRequestDto req){

        signboardService.deletePoint(req);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));
    }
}
