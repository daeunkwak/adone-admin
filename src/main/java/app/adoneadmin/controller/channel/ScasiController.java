package app.adoneadmin.controller.channel;

import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.channel.response.ScasiDto;
import app.adoneadmin.dto.channel.request.ChannelRequestDto;
import app.adoneadmin.dto.channel.request.ChannelUpdateRequestDto;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.service.channel.ScasiService;
import app.adoneadmin.vo.channel.ScasiVo;
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

@Tag(name = "channels/scasi", description = "스카시 채널 단가 api")
@RequestMapping("/api/channels/scasi")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ScasiController {

    private final ScasiService scasiService;
    private final ModelMapper modelMapper;


    @Tag(name = "channels/scasi", description = "스카시 채널 단가 api")
    @ApiOperation(value = "스카시 단가 추가 api", notes = "- materialType [ 아크릴:C, 포맥스:F, 고무:R ]\n")
    @PostMapping(value="")
    public ResponseEntity<CommonApiResult> createScasi(@RequestBody @Valid List<ChannelRequestDto.Scasi> req,
                                                      @RequestParam("materialType") String materialType){

        List<ScasiVo> scasiVos = req.stream().map(dto -> modelMapper.map(dto, ScasiVo.class)).collect(Collectors.toList());
        scasiService.createScasi(materialType, scasiVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "channels/scasi", description = "스카시 채널 단가 api")
    @ApiOperation(value = "스카시 단가 조회 api", notes = "- materialType [ 아크릴:C, 포맥스:F, 고무:R ]\n")
    @GetMapping(value="")
    public ResponseEntity<?> getScasi(@RequestParam("materialType") String materialType){

        List<ScasiVo> result = scasiService.getScasi(materialType);

        switch (MaterialType.of(materialType)){
            case ACRYL:
                List<ScasiDto.Acryl> acryls = result.stream().map(vo -> {
                    return modelMapper.map(vo, ScasiDto.Acryl.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(acryls);

            case FORMEX:
                List<ScasiDto.Formex> formexes = result.stream().map(vo -> {
                    return modelMapper.map(vo, ScasiDto.Formex.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(formexes);

            case RUBBER:
                List<ScasiDto.Rubber> rubbers = result.stream().map(vo -> {
                    return modelMapper.map(vo, ScasiDto.Rubber.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(rubbers);

        }

        throw new CustomException("잘못된 materialType 입니다.");
    }


    @Tag(name = "channels/scasi", description = "스카시 채널 단가 api")
    @ApiOperation(value = "스카시 단가 수정 api", notes = "- materialType [ 아크릴:C, 포맥스:F, 고무:R ]\n")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateScasi(@RequestBody @Valid List<ChannelUpdateRequestDto.ScasiUpdate> req,
                                                       @RequestParam("materialType") String materialType){


        List<ScasiVo> scasiVos = req.stream().map(dto -> modelMapper.map(dto, ScasiVo.class)).collect(Collectors.toList());
        scasiService.updateScasi(materialType, scasiVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "channels/scasi", description = "스카시 채널 단가 api")
    @ApiOperation(value = "스카시 단가 삭제 api", notes = "- materialType [ 아크릴:C, 포맥스:F, 고무:R ]\n")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> updateScasi(@RequestBody @Valid DeleteRequestDto req,
                                                       @RequestParam("materialType") String materialType){

        scasiService.deleteScasi(req, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));
    }


}
