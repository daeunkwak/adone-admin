package app.adoneadmin.controller.channel;

import app.adoneadmin.dto.channel.BackLightDto;
import app.adoneadmin.dto.channel.ChannelDto;
import app.adoneadmin.dto.channel.request.BackLightRequestDto;
import app.adoneadmin.dto.channel.request.ChannelRequestDto;
import app.adoneadmin.dto.channel.request.ChannelUpdateRequestDto;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.dto.signboard.StandardCostDto;
import app.adoneadmin.dto.signboard.request.StandardCostRequestDto;
import app.adoneadmin.service.ChannelService;
import app.adoneadmin.vo.channel.BackLightVo;
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

@Tag(name = "channels/back-light", description = "후광 채널 단가 api")
@RequestMapping("/api/channels/back-light")
@RequiredArgsConstructor
@RestController
@Slf4j
public class BackLightController {

    private final ChannelService channelService;
    private final ModelMapper modelMapper;


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 갈바, 스텐 단가 추가 api", notes = "- materialType [ 갈바:G, 스텐:S ]\n")
    @PostMapping(value="")
    public ResponseEntity<?> createBackLight(@RequestBody @Valid List<ChannelRequestDto.BackLight> req,
                                             @RequestParam("materialType") String materialType){

        List<BackLightVo> backLightVos = req.stream().map(dto -> modelMapper.map(dto, BackLightVo.class)).collect(Collectors.toList());
        channelService.createBackLight(materialType, backLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }

    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 티타늄골드 단가 추가 api")
    @PostMapping(value="/titanium")
    public ResponseEntity<?> createBackLightTitaniumGold(@RequestBody @Valid List<StandardCostRequestDto> req){

        List<StandardCostVo> vos = req.stream().map(dto -> modelMapper.map(dto, StandardCostVo.class)).collect(Collectors.toList());
        channelService.createBackLightTitaniumGold(vos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 갈바, 스텐 단가 조회 api", notes = "- materialType [ 갈바:G, 스텐:S ]\n")
    @GetMapping(value="")
    public ResponseEntity<List<ChannelDto.BackLight>> getBackLight(@RequestParam("materialType") String materialType){

        List<ChannelDto.BackLight> result = channelService.getBackLight(materialType).stream().map(vo -> {
            return modelMapper.map(vo, ChannelDto.BackLight.class);}).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 티타늄골드 단가 조회 api")
    @GetMapping(value="/titanium")
    public ResponseEntity<List<StandardCostDto>> getBackLightTitaniumGold(){

        List<StandardCostDto> result = channelService.getBackLightTitaniumGold().stream().map(vo -> {
            return modelMapper.map(vo, StandardCostDto.class);}).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 갈바, 스텐 단가 수정 api",
            notes = "- materialType [ 갈바:G, 스텐:S ]\n")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateBackLight(@RequestBody @Valid List<ChannelUpdateRequestDto.BackLightUpdate> req,
                                                            @RequestParam("materialType") String materialType){

        List<BackLightVo> backLightVos = req.stream().map(dto -> modelMapper.map(dto, BackLightVo.class)).collect(Collectors.toList());
        channelService.updateBackLight(materialType, backLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 티타늄골드 단가 수정 api")
    @PatchMapping(value="/titanium")
    public ResponseEntity<CommonApiResult> updateBackLightTitaniumGold(@RequestBody @Valid List<StandardCostRequestDto.StandardCostUpdateRequestDto> req){

        List<StandardCostVo> standardCostVos = req.stream().map(dto -> modelMapper.map(dto, StandardCostVo.class)).collect(Collectors.toList());
        channelService.updateBackLightTitaniumGold(standardCostVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 수정되었습니다."));
    }

    @Tag(name = "channels/back-light", description = "채널 - 후광 채널 단가 api")
    @ApiOperation(value = "후광 채널 단가 삭제 api",
            notes = "- materialType [ 갈바:G, 스텐:S, 티타늄골드:T ]\n")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteBackLight(@RequestBody @Valid DeleteRequestDto req,
                                                            @RequestParam("materialType") String materialType){

        channelService.deleteBackLight(req, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));

    }

}
