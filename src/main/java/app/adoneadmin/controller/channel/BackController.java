package app.adoneadmin.controller.channel;


import app.adoneadmin.dto.channel.ChannelDto;
import app.adoneadmin.dto.channel.request.ChannelRequestDto;
import app.adoneadmin.dto.channel.request.ChannelUpdateRequestDto;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.service.channel.BackService;
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

@Tag(name = "channels/back", description = "비조명(백채널) 채널 단가 api")
@RequestMapping("/api/channels/back")
@RequiredArgsConstructor
@RestController
@Slf4j
public class BackController {

    private final BackService backService;
    private final ModelMapper modelMapper;


    @Tag(name = "channels/back", description = "비조명(백채널) 채널 단가 api")
    @ApiOperation(value = "비조명(백채널) 단가 추가 api", notes = "- materialType [ 갈바:G, 스텐:S, T:티타늄 ]\n")
    @PostMapping(value="")
    public ResponseEntity<CommonApiResult> createBack(@RequestBody @Valid List<ChannelRequestDto.Back> req,
                                             @RequestParam("materialType") String materialType){

        List<BackLightVo> backLightVos = req.stream().map(dto -> modelMapper.map(dto, BackLightVo.class)).collect(Collectors.toList());
        backService.createBack(materialType, backLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 추가되었습니다."));
    }


    @Tag(name = "channels/back", description = "비조명(백채널) 채널 단가 api")
    @ApiOperation(value = "비조명(백채널) 단가 조회 api", notes = "- materialType [ 갈바:G, 스텐:S, T:티타늄 ]\n")
    @GetMapping(value="")
    public ResponseEntity<List<ChannelDto.Back>> getBack(@RequestParam("materialType") String materialType){

        List<ChannelDto.Back> result = backService.getBack(materialType).stream().map(vo -> {
            return modelMapper.map(vo, ChannelDto.Back.class);}).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Tag(name = "channels/back", description = "비조명(백채널) 채널 단가 api")
    @ApiOperation(value = "비조명(백채널) 단가 수정 api", notes = "- materialType [ 갈바:G, 스텐:S, T:티타늄 ]\n")
    @PatchMapping(value="")
    public ResponseEntity<CommonApiResult> updateBack(@RequestBody @Valid List<ChannelUpdateRequestDto.BackUpdate> req,
                                                              @RequestParam("materialType") String materialType){

        List<BackLightVo> backLightVos = req.stream().map(dto -> modelMapper.map(dto, BackLightVo.class)).collect(Collectors.toList());
        backService.updateBack(materialType, backLightVos);
        return ResponseEntity.ok(CommonApiResult.createOk("항목이 정상적으로 수정되었습니다."));
    }


    @Tag(name = "channels/back", description = "비조명(백채널) 채널 단가 api")
    @ApiOperation(value = "비조명(백채널) 단가 삭제 api", notes = "- materialType [ 갈바:G, 스텐:S, T:티타늄 ]\n")
    @DeleteMapping(value="")
    public ResponseEntity<CommonApiResult> deleteBack(@RequestBody @Valid DeleteRequestDto req,
                                                           @RequestParam("materialType") String materialType){

        backService.deleteBack(req, materialType);
        return ResponseEntity.ok(CommonApiResult.OK("항목이 정상적으로 삭제되었습니다."));
    }

}
