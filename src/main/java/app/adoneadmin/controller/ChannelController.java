package app.adoneadmin.controller;

import app.adoneadmin.dto.channel.request.ChannelRequestDto;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.signboard.request.FrontFrameRequestDto;
import app.adoneadmin.service.ChannelService;
import app.adoneadmin.vo.channel.FrontLightVo;
import app.adoneadmin.vo.signboard.FrontFrameVo;
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

@Tag(name = "channels", description = "채널 단가 api")
@RequestMapping("/api/channels")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ChannelController {

    private final ChannelService channelService;
    private final ModelMapper modelMapper;

    @Tag(name = "channels/front-light", description = "채널 - 전광 채널 단가 api")
    @ApiOperation(value = "전광 채널 단가 추가 api")
    @PostMapping(value="/front-light")
    public ResponseEntity<?> createFrontLight(@RequestBody @Valid List<ChannelRequestDto> req,
                                              @RequestParam("materialType") String materialType){

        List<FrontLightVo> frontFrameVos = req.stream().map(dto -> modelMapper.map(dto, FrontLightVo.class)).collect(Collectors.toList());

        channelService.createFrontLight(materialType, frontFrameVos);

        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }



}
