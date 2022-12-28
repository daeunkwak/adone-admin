package app.adoneadmin.controller.channel;

import app.adoneadmin.dto.channel.response.ChannelDto;
import app.adoneadmin.dto.channel.request.ChannelRequestDto;
import app.adoneadmin.dto.channel.request.ChannelUpdateRequestDto;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.service.channel.FrontBackLightService;
import app.adoneadmin.vo.channel.FrontBackLightVo;
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

@Tag(name = "channels/front-back-light", description = "전후광 채널 단가 api")
@RequestMapping("/api/channels/front-back-light")
@RequiredArgsConstructor
@RestController
@Slf4j
public class FrontBackLightController {

    private final FrontBackLightService frontBackLightService;
    private final ModelMapper modelMapper;


    @Tag(name = "channels/front-back-light", description = "채널 - 전후광 채널 단가 api")
    @ApiOperation(value = "전후광 채널 단가 추가 api", notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]\n")
    @PostMapping(value="")
    public ResponseEntity<?> createFrontBackLight(@RequestBody @Valid List<ChannelRequestDto.FrontBackLight> req,
                                              @RequestParam("materialType") String materialType){

        List<FrontBackLightVo> frontBackLightVos = req.stream().map(dto -> modelMapper.map(dto, FrontBackLightVo.class)).collect(Collectors.toList());
        frontBackLightService.createFrontBackLight(materialType, frontBackLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "channels/front-back-light", description = "채널 - 전후광 채널 단가 api")
    @ApiOperation(value = "전후광 채널 단가 조회 api", notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]\n")
    @GetMapping(value="")
    public ResponseEntity<List<ChannelDto.FrontBackLight>> getFrontBackLight(@RequestParam("materialType") String materialType){

        List<ChannelDto.FrontBackLight> result = frontBackLightService.getFrontBackLight(materialType).stream().map(vo -> {
            return modelMapper.map(vo, ChannelDto.FrontBackLight.class);}).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "channels/front-back-light", description = "채널 - 전후광 채널 단가 api")
    @ApiOperation(value = "전후광 채널 단가 수정 api", notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]\n")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateFrontBackLight(@RequestBody @Valid List<ChannelUpdateRequestDto.FrontBackLightUpdate> req,
                                                                                @RequestParam("materialType") String materialType){

        List<FrontBackLightVo> frontBackLightVos = req.stream().map(dto -> modelMapper.map(dto, FrontBackLightVo.class)).collect(Collectors.toList());
        frontBackLightService.updateFrontBackLight(materialType, frontBackLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "channels/front-back-light", description = "채널 - 전후광 채널 단가 api")
    @ApiOperation(value = "전후광 채널 단가 삭제 api", notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S ]\n")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteFrontBackLight(@RequestBody @Valid DeleteRequestDto req,
                                                                @RequestParam("materialType") String materialType){

        frontBackLightService.deleteFrontBackLight(req, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));
    }

}
