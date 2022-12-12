package app.adoneadmin.controller.channel;

import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.channel.FrontLightDto;
import app.adoneadmin.dto.channel.request.ChannelRequestDto;
import app.adoneadmin.dto.channel.request.ChannelUpdateRequestDto;
import app.adoneadmin.dto.channel.request.FrontLightRequestDto;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.service.ChannelService;
import app.adoneadmin.vo.channel.FrontLightVo;
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

@Tag(name = "channels/front-light", description = "전광 채널 단가 api")
@RequestMapping("/api/channels/front-light")
@RequiredArgsConstructor
@RestController
@Slf4j
public class FrontLightController {

    private final ChannelService channelService;
    private final ModelMapper modelMapper;

    @Tag(name = "channels/front-light", description = "채널 - 전광 채널 단가 api")
    @ApiOperation(value = "전광 채널 단가 추가 api",
                notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S, 일체형:I, 에폭시:E ]\n" + "- 해당되지 않는 depth필드 제외해서 보내주시면 됩니다.\n" +
                        "- ex) 알루미늄 [ { \"depth100\": 123, \"depth80\": 123, \"led\": 77, \"standard\": \"1000ENG\" } ]")
    @PostMapping(value="")
    public ResponseEntity<?> createFrontLight(@RequestBody @Valid List<ChannelRequestDto.FrontLight> req,
                                                 @RequestParam("materialType") String materialType){

        List<FrontLightVo> frontLightVos = req.stream().map(dto -> modelMapper.map(dto, FrontLightVo.class)).collect(Collectors.toList());
        channelService.createFrontLight(materialType, frontLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "channels/front-light", description = "채널 - 전광 채널 단가 api")
    @ApiOperation(value = "전광 채널 단가 조회 api",
                notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S, 일체형:I, 에폭시:E ]\n")
    @GetMapping(value="")
    public ResponseEntity<?> getFrontLight(@RequestParam("materialType") String materialType){

        List<FrontLightVo> result = channelService.getFrontLight(materialType);

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                List<FrontLightDto.Alu> alus = result.stream().map(vo -> {
                    return modelMapper.map(vo, FrontLightDto.Alu.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(alus);

            case GALVA:
                List<FrontLightDto.Galva> galvas = result.stream().map(vo -> {
                    return modelMapper.map(vo, FrontLightDto.Galva.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(galvas);

            case STAN:
                List<FrontLightDto.Stan> stans = result.stream().map(vo -> {
                    return modelMapper.map(vo, FrontLightDto.Stan.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(stans);

            case EPOXY:
                List<FrontLightDto.Epoxy> epoxies = result.stream().map(vo -> {
                    return modelMapper.map(vo, FrontLightDto.Epoxy.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(epoxies);

            case INTEGRAL:
                List<FrontLightDto.Assembled> assembleds = result.stream().map(vo -> {
                    return modelMapper.map(vo, FrontLightDto.Assembled.class);}).collect(Collectors.toList());
                return ResponseEntity.ok(assembleds);
        }

        throw new CustomException("잘못된 materialType 입니다.");
    }

    @Tag(name = "channels/front-light", description = "채널 - 전광 채널 단가 api")
    @ApiOperation(value = "전광 채널 단가 수정 api",
            notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S, 일체형:I, 에폭시:E ]\n" + "- 해당되지 않는 depth 필드엔 아무 값이나 넣어서 주시면 됩니다.")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateFrontLight(@RequestBody @Valid List<ChannelUpdateRequestDto.FrontLightUpdate> req,
                                                 @RequestParam("materialType") String materialType){

        List<FrontLightVo> frontLightVos = req.stream().map(dto -> modelMapper.map(dto, FrontLightVo.class)).collect(Collectors.toList());
        channelService.updateFrontLight(materialType, frontLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 수정되었습니다."));
    }

    @Tag(name = "channels/front-light", description = "채널 - 전광 채널 단가 api")
    @ApiOperation(value = "전광 채널 단가 삭제 api",
            notes = "- materialType [ 알루미늄:A, 갈바:G, 스텐:S, 일체형:I, 에폭시:E ]\n")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteFrontLight(@RequestBody @Valid DeleteRequestDto req,
                                              @RequestParam("materialType") String materialType){

        channelService.deleteFrontLight(req, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));

    }


}
