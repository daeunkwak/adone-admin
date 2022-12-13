package app.adoneadmin.service.channel;

import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightAlu;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightGalva;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightStan;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackAluRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackGalvaRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackStanRepository;
import app.adoneadmin.vo.channel.FrontBackLightVo;
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
public class FrontBackLightService {

    private final ChFrontBackAluRepository chFrontBackAluRepository;
    private final ChFrontBackGalvaRepository chFrontBackGalvaRepository;
    private final ChFrontBackStanRepository chFrontBackStanRepository;
    private final ModelMapper modelMapper;


    /**
     * 전후광 채널 단가 추가
     */
    public void createFrontBackLight(String materialType, List<FrontBackLightVo> frontBackLightVos) {

        switch (MaterialType.of(materialType)) {
            case ALUMINUM:
                for(FrontBackLightVo vo : frontBackLightVos){
                    if(chFrontBackAluRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontBackAluRepository.save(ChFrontBackLightAlu.create(vo));
                } break;

            case GALVA:
                for(FrontBackLightVo vo : frontBackLightVos){
                    if(chFrontBackGalvaRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontBackGalvaRepository.save(ChFrontBackLightGalva.create(vo));
                } break;

            case STAN:
                for(FrontBackLightVo vo : frontBackLightVos){
                    if(chFrontBackStanRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontBackStanRepository.save(ChFrontBackLightStan.create(vo));
                } break;

        }
    }


    /**
     * 전후광 채널 단가 조회
     */
    public List<FrontBackLightVo> getFrontBackLight(String materialType) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                return chFrontBackAluRepository.findAll().stream().map(chFrontBackLightAlu -> {
                    return modelMapper.map(chFrontBackLightAlu, FrontBackLightVo.class);
                }).collect(Collectors.toList());

            case GALVA:
                return chFrontBackGalvaRepository.findAll().stream().map(chFrontBackLightAlu -> {
                    return modelMapper.map(chFrontBackLightAlu, FrontBackLightVo.class);
                }).collect(Collectors.toList());

            case STAN:
                return chFrontBackStanRepository.findAll().stream().map(chFrontBackLightAlu -> {
                    return modelMapper.map(chFrontBackLightAlu, FrontBackLightVo.class);
                }).collect(Collectors.toList());

        }

        throw new CustomException("잘못된 materialType 입니다.");
    }


    /**
     * 전후광 채널 단가 수정
     */
    public void updateFrontBackLight(String materialType, List<FrontBackLightVo> frontBackLightVos) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                for(FrontBackLightVo vo : frontBackLightVos){
                    chFrontBackAluRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontBackLightAlu(vo);
                } break;

            case GALVA:
                for(FrontBackLightVo vo : frontBackLightVos){
                    chFrontBackGalvaRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontBackLightGalva(vo);
                } break;

            case STAN:
                for(FrontBackLightVo vo : frontBackLightVos){
                    chFrontBackStanRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontBackLightStan(vo);
                } break;

        }
    }


    /**
     * 전후광 채널 단가 삭제
     */
    public void deleteFrontBackLight(DeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case GALVA:
                for(long id : req.getIdList()){
                    chFrontBackGalvaRepository.deleteById(id);
                } break;

            case STAN:
                for(long id : req.getIdList()){
                    chFrontBackStanRepository.deleteById(id);
                } break;

            case ALUMINUM:
                for(long id : req.getIdList()){
                    chFrontBackAluRepository.deleteById(id);
                } break;
        }

    }

}
