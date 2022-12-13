package app.adoneadmin.service.channel;

import app.adoneadmin.domain.channel.backlight.ChBackLightGalva;
import app.adoneadmin.domain.channel.backlight.ChBackLightStan;
import app.adoneadmin.domain.channel.backlight.ChBackLightTitaniumGold;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.channel.backlight.ChBackLightGalvaRepository;
import app.adoneadmin.repository.channel.backlight.ChBackLightStanRepository;
import app.adoneadmin.repository.channel.backlight.ChBackLightTitaniumGoldRepository;
import app.adoneadmin.vo.channel.BackLightVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class BackLightService {

    private final ChBackLightGalvaRepository chBackLightGalvaRepository;
    private final ChBackLightStanRepository chBackLightStanRepository;
    private final ChBackLightTitaniumGoldRepository chBackLightTitaniumGoldRepository;
    private final ModelMapper modelMapper;


    /**
     * 후광 채널 갈바, 스탠 단가 추가
     */
    public void createBackLight(String materialType, List<BackLightVo> backLightVos) {

        switch (MaterialType.of(materialType)) {

            case GALVA:
                for(BackLightVo vo : backLightVos){
                    if(chBackLightGalvaRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chBackLightGalvaRepository.save(ChBackLightGalva.create(vo));
                } break;

            case STAN:
                for(BackLightVo vo : backLightVos){
                    if(chBackLightStanRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chBackLightStanRepository.save(ChBackLightStan.create(vo));
                } break;

            case TITANIUMGOLD:
                for(BackLightVo vo : backLightVos){
                    if(chBackLightTitaniumGoldRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chBackLightTitaniumGoldRepository.save(ChBackLightTitaniumGold.create(vo));
                } break;


        }
    }


    /**
     * 후광 채널 갈바, 스탠 단가 조회
     */
    public List<BackLightVo> getBackLight(String materialType) {

        switch (MaterialType.of(materialType)){
            case GALVA:
                return chBackLightGalvaRepository.findAll().stream().map(chBackLightGalva -> {
                    return modelMapper.map(chBackLightGalva, BackLightVo.class);
                }).collect(Collectors.toList());

            case STAN:
                return chBackLightStanRepository.findAll().stream().map(chBackLightStan -> {
                    return modelMapper.map(chBackLightStan, BackLightVo.class);
                }).collect(Collectors.toList());

            case TITANIUMGOLD:
                return chBackLightTitaniumGoldRepository.findAll().stream().map(chBackLightTitaniumGold -> {
                    return modelMapper.map(chBackLightTitaniumGold, BackLightVo.class);
                }).collect(Collectors.toList());

        }
        throw new CustomException("잘못된 materialType 입니다.");

    }


    /**
     * 후광 채널 갈바, 스탠 단가 수정
     */
    public void updateBackLight(String materialType, List<BackLightVo> backLightVos) {

        switch (MaterialType.of(materialType)){
            case GALVA:
                for(BackLightVo vo : backLightVos){
                    chBackLightGalvaRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateBackLightGalva(vo);
                } break;

            case STAN:
                for(BackLightVo vo : backLightVos){
                    chBackLightStanRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateBackLightStan(vo);
                } break;

            case TITANIUMGOLD:
                for(BackLightVo vo : backLightVos){
                    chBackLightTitaniumGoldRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateBackLightTitaniumGold(vo);
                } break;

        }
    }


    /**
     * 후광 채널 단가 삭제
     */
    public void deleteBackLight(DeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case GALVA:
                for(long id : req.getIdList()){
                    chBackLightGalvaRepository.deleteById(id);
                } break;

            case STAN:
                for(long id : req.getIdList()){
                    chBackLightStanRepository.deleteById(id);
                } break;
            case TITANIUMGOLD:
                for(long id : req.getIdList()){
                    chBackLightTitaniumGoldRepository.deleteById(id);
                } break;
        }

    }

}

