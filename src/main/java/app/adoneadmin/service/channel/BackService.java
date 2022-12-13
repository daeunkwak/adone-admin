package app.adoneadmin.service.channel;

import app.adoneadmin.domain.channel.back.ChBackGalva;
import app.adoneadmin.domain.channel.back.ChBackStan;
import app.adoneadmin.domain.channel.back.ChBackTitanium;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.channel.back.ChBackGalvaRepository;
import app.adoneadmin.repository.channel.back.ChBackStanRepository;
import app.adoneadmin.repository.channel.back.ChBackTitaniumRepository;
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
public class BackService {

    private final ChBackStanRepository chBackStanRepository;
    private final ChBackTitaniumRepository chBackTitaniumRepository;
    private final ChBackGalvaRepository chBackGalvaRepository;
    private final ModelMapper modelMapper;


    /**
     * 비조명(백채널) 단가 추가
     */
    public void createBack(String materialType, List<BackLightVo> backLightVos) {

        switch (MaterialType.of(materialType)) {
            case STAN:
                for(BackLightVo vo : backLightVos){
                    if(chBackStanRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chBackStanRepository.save(ChBackStan.create(vo));
                } break;

            case GALVA:
                for(BackLightVo vo : backLightVos){
                    if(chBackGalvaRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chBackGalvaRepository.save(ChBackGalva.create(vo));
                } break;

            case TITANIUMGOLD:  // 여기서는 티타늄
                for(BackLightVo vo : backLightVos){
                    if(chBackTitaniumRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chBackTitaniumRepository.save(ChBackTitanium.create(vo));
                } break;

        }
    }


    /**
     * 비조명(백채널) 단가 조회
     */
    public List<BackLightVo> getBack(String materialType) {

        switch (MaterialType.of(materialType)){
            case TITANIUMGOLD:
                return chBackTitaniumRepository.findAll().stream().map(chBackTitanium -> {
                    return modelMapper.map(chBackTitanium, BackLightVo.class);
                }).collect(Collectors.toList());

            case GALVA:
                return chBackGalvaRepository.findAll().stream().map(chBackGalva -> {
                    return modelMapper.map(chBackGalva, BackLightVo.class);
                }).collect(Collectors.toList());

            case STAN:
                return chBackStanRepository.findAll().stream().map(chBackStan -> {
                    return modelMapper.map(chBackStan, BackLightVo.class);
                }).collect(Collectors.toList());
        }

        throw new CustomException("잘못된 materialType 입니다.");
    }


    /**
     * 비조명(백채널) 단가 수정
     */
    public void updateBack(String materialType, List<BackLightVo> backLightVos) {

        switch (MaterialType.of(materialType)){
            case TITANIUMGOLD:
                for(BackLightVo vo : backLightVos){
                    chBackTitaniumRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateChBackTitanium(vo);
                } break;

            case GALVA:
                for(BackLightVo vo : backLightVos){
                    chBackGalvaRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateChBackGalva(vo);
                } break;

            case STAN:
                for(BackLightVo vo : backLightVos){
                    chBackStanRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateChBackStan(vo);
                } break;
        }
    }


    /**
     * 비조명(백채널) 단가 삭제
     */
    public void deleteBack(DeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case TITANIUMGOLD:
                for(long id : req.getIdList()){
                    chBackTitaniumRepository.deleteById(id);
                } break;

            case STAN:
                for(long id : req.getIdList()){
                    chBackStanRepository.deleteById(id);
                } break;

            case GALVA:
                for(long id : req.getIdList()){
                    chBackGalvaRepository.deleteById(id);
                } break;
        }

    }
}
