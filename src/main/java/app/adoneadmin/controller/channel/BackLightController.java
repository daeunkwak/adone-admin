package app.adoneadmin.controller.channel;

import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.channel.response.BackLightDto;
import app.adoneadmin.dto.channel.request.ChannelRequestDto;
import app.adoneadmin.dto.channel.request.ChannelUpdateRequestDto;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.service.channel.BackLightService;
import app.adoneadmin.vo.channel.BackLightVo;
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

@Tag(name = "channels/back-light", description = "후광 채널 단가 api")
@RequestMapping("/api/channels/back-light")
@RequiredArgsConstructor
@RestController
@Slf4j
public class BackLightController {

    private final BackLightService backLightService;
    private final ModelMapper modelMapper;


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 갈바, 스텐 단가 추가 api", notes = "- materialType [ 갈바:G, 스텐:S, 티타늄골드:T ]\n")
    @PostMapping(value="")
    public ResponseEntity<CommonApiResult> createBackLight(@RequestBody @Valid List<ChannelRequestDto.BackLight> req,
                                             @RequestParam("materialType") String materialType){

        List<BackLightVo> backLightVos = req.stream().map(dto -> modelMapper.map(dto, BackLightVo.class)).collect(Collectors.toList());
        backLightService.createBackLight(materialType, backLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 단가 조회 api", notes = "- materialType [ 갈바:G, 스텐:S, 티타늄골드:T ]\n")
    @GetMapping(value="")
    public ResponseEntity<?> getBackLight(@RequestParam("materialType") String materialType){

        List<BackLightVo> result = backLightService.getBackLight(materialType);

        switch (MaterialType.of(materialType)){
            case TITANIUMGOLD:
                List<BackLightDto.TitaniumGold> titaniumGolds = result.stream().map(vo -> {
                    return modelMapper.map(vo, BackLightDto.TitaniumGold.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(titaniumGolds);

            case GALVA:
                List<BackLightDto.Galva> galvas = result.stream().map(vo -> {
                    return modelMapper.map(vo, BackLightDto.Galva.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(galvas);

            case STAN:
                List<BackLightDto.Stan> stans = result.stream().map(vo -> {
                    return modelMapper.map(vo, BackLightDto.Stan.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(stans);

        }

        throw new CustomException("잘못된 materialType 입니다.");
    }


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 단가 수정 api",
            notes = "- materialType [ 갈바:G, 스텐:S, 티타늄골드:T ]\n" + "- 해당되지 않는 depth 필드엔 아무 값이나 넣어서 주시면 됩니다.")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateBackLight(@RequestBody @Valid List<ChannelUpdateRequestDto.BackLightUpdate> req,
                                                            @RequestParam("materialType") String materialType){

        List<BackLightVo> backLightVos = req.stream().map(dto -> modelMapper.map(dto, BackLightVo.class)).collect(Collectors.toList());
        backLightService.updateBackLight(materialType, backLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 단가 삭제 api",
            notes = "- materialType [ 갈바:G, 스텐:S, 티타늄골드:T ]\n")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteBackLight(@RequestBody @Valid DeleteRequestDto req,
                                                            @RequestParam("materialType") String materialType){

        backLightService.deleteBackLight(req, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));

    }

}
