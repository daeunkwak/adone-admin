package app.adoneadmin.service;

import app.adoneadmin.domain.channel.frontlight.*;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.repository.channel.frontlight.*;
import app.adoneadmin.vo.channel.FrontLightVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class ChannelService {

    private final ChFrontLightAluRepository chFrontLightAluRepository;
    private final ChFrontLightGalvaRepository chFrontLightGalvaRepository;
    private final ChFrontLightStanRepository chFrontLightStanRepository;
    private final ChFrontLightAssembledRepository chFrontLightAssembledRepository;
    private final ChFrontLightEpoxyRepository chFrontLightEpoxyRepository;
    private final ModelMapper modelMapper;


    /**
     * 전광 채널 단가 추가
     */
    public void createFrontLight(String materialType, List<FrontLightVo> frontLightVos) {

        switch (MaterialType.of(materialType)) {
            case ALUMINUM:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightAluRepository.findByOption(vo.getOption()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");    // TODO : 이게 될까? 확인
                    }else {
                        ChFrontLightAlu frontLight = ChFrontLightAlu.create(vo);
                        log.info("whyyyyyyyyyyyyyyyyy ::::::::::: " + frontLight);
                        chFrontLightAluRepository.save(frontLight);
                    }
                }
                break;

            case GALVA:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightGalvaRepository.findByOption(vo.getOption()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");    // TODO : 이게 될까? 확인
                    }
                    ChFrontLightGalva frontLight = ChFrontLightGalva.create(vo);
                    chFrontLightGalvaRepository.save(frontLight);
                }
                break;

            case STAN:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightStanRepository.findByOption(vo.getOption()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");    // TODO : 이게 될까? 확인
                    }
                    ChFrontLightStan frontLight = ChFrontLightStan.create(vo);
                    chFrontLightStanRepository.save(frontLight);
                }
                break;

            case INTEGRAL:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightAssembledRepository.findByOption(vo.getOption()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");    // TODO : 이게 될까? 확인
                    }
                    ChFrontLightAssembled frontLight = ChFrontLightAssembled.create(vo);
                    chFrontLightAssembledRepository.save(frontLight);
                }
                break;

            case EPOXY:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightEpoxyRepository.findByOption(vo.getOption()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");    // TODO : 이게 될까? 확인
                    }
                    ChFrontLightAssembled frontLight = ChFrontLightAssembled.create(vo);
                    chFrontLightAssembledRepository.save(frontLight);
                }
                break;

        }

    }


}

