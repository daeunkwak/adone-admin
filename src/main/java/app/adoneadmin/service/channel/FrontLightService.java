package app.adoneadmin.service.channel;

import app.adoneadmin.domain.channel.frontlight.*;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.channel.frontlight.*;
import app.adoneadmin.vo.channel.FrontLightVo;
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
public class FrontLightService {

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
                    if(chFrontLightAluRepository.findByStandard(vo.getStandard()) != null) {
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontLightAluRepository.save(ChFrontLightAlu.create(vo));
                } break;

            case GALVA:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightGalvaRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontLightGalvaRepository.save(ChFrontLightGalva.create(vo));
                } break;

            case STAN:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightStanRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontLightStanRepository.save(ChFrontLightStan.create(vo));
                } break;

            case INTEGRAL:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightAssembledRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontLightAssembledRepository.save(ChFrontLightAssembled.create(vo));
                } break;

            case EPOXY:
                for(FrontLightVo vo : frontLightVos){
                    if(chFrontLightEpoxyRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chFrontLightEpoxyRepository.save(ChFrontLightEpoxy.create(vo));
                } break;

        }

    }


    /**
     * 전광 채널 단가 조회
     */
    public List<FrontLightVo> getFrontLight(String materialType) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                return chFrontLightAluRepository.findAll().stream().map(chFrontLight -> {
                    return modelMapper.map(chFrontLight, FrontLightVo.class);
                }).collect(Collectors.toList());

            case GALVA:
                return chFrontLightGalvaRepository.findAll().stream().map(chFrontLight -> {
                    return modelMapper.map(chFrontLight, FrontLightVo.class);
                }).collect(Collectors.toList());

            case STAN:
                return chFrontLightStanRepository.findAll().stream().map(chFrontLight -> {
                    return modelMapper.map(chFrontLight, FrontLightVo.class);
                }).collect(Collectors.toList());

            case EPOXY:
                return chFrontLightEpoxyRepository.findAll().stream().map(chFrontLight -> {
                    return modelMapper.map(chFrontLight, FrontLightVo.class);
                }).collect(Collectors.toList());

            case INTEGRAL:
                return chFrontLightAssembledRepository.findAll().stream().map(chFrontLight -> {
                    return modelMapper.map(chFrontLight, FrontLightVo.class);
                }).collect(Collectors.toList());
        }

        throw new CustomException("잘못된 materialType 입니다.");
    }


    /**
     * 전광 채널 단가 수정
     */
    public void updateFrontLight(String materialType, List<FrontLightVo> frontLightVos) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                for(FrontLightVo vo : frontLightVos){
                    chFrontLightAluRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontLightAlu(vo);
                } break;

            case GALVA:
                for(FrontLightVo vo : frontLightVos){
                    chFrontLightGalvaRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontLightGalva(vo);
                } break;

            case STAN:
                for(FrontLightVo vo : frontLightVos){
                    chFrontLightStanRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontLightStan(vo);
                } break;

            case EPOXY:
                for(FrontLightVo vo : frontLightVos){
                    chFrontLightEpoxyRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontLightEpoxy(vo);
                } break;

            case INTEGRAL:
                for(FrontLightVo vo : frontLightVos){
                    chFrontLightAssembledRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateFrontLightAssembled(vo);
                } break;
        }

    }


    /**
     * 전광 채널 단가 삭제
     */
    public void deleteFrontLight(DeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                for(long id : req.getIdList()){
                    chFrontLightAluRepository.deleteById(id);
                } break;

            case GALVA:
                for(long id : req.getIdList()){
                    chFrontLightGalvaRepository.deleteById(id);
                } break;

            case STAN:
                for(long id : req.getIdList()){
                    chFrontLightStanRepository.deleteById(id);
                } break;

            case EPOXY:
                for(long id : req.getIdList()){
                    chFrontLightEpoxyRepository.deleteById(id);
                } break;

            case INTEGRAL:
                for(long id : req.getIdList()){
                    chFrontLightAssembledRepository.deleteById(id);
                } break;
        }

    }
}
