package app.adoneadmin.service;

import app.adoneadmin.domain.channel.backlight.ChBackLightGalva;
import app.adoneadmin.domain.channel.backlight.ChBackLightStan;
import app.adoneadmin.domain.channel.backlight.ChBackLightTitaniumGold;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightAlu;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightGalva;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightStan;
import app.adoneadmin.domain.channel.frontlight.*;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.channel.backlight.ChBackLightGalvaRepository;
import app.adoneadmin.repository.channel.backlight.ChBackLightStanRepository;
import app.adoneadmin.repository.channel.backlight.ChBackLightTitaniumGoldRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackAluRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackGalvaRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackStanRepository;
import app.adoneadmin.repository.channel.frontlight.*;
import app.adoneadmin.vo.channel.BackLightVo;
import app.adoneadmin.vo.channel.FrontBackLightVo;
import app.adoneadmin.vo.channel.FrontLightVo;
import app.adoneadmin.vo.signboard.StandardCostVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ChBackLightGalvaRepository chBackLightGalvaRepository;
    private final ChBackLightStanRepository chBackLightStanRepository;
    private final ChBackLightTitaniumGoldRepository chBackLightTitaniumGoldRepository;
    private final ChFrontBackAluRepository chFrontBackAluRepository;
    private final ChFrontBackGalvaRepository chFrontBackGalvaRepository;
    private final ChFrontBackStanRepository chFrontBackStanRepository;
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

        }
    }


    /**
     * 후광 채널 티타늄골드 단가 추가
     */
    public void createBackLightTitaniumGold(List<StandardCostVo> backLightVos) {

        for(StandardCostVo vo : backLightVos){
            if(chBackLightTitaniumGoldRepository.findByStandard(vo.getStandard()) != null){
                throw new CustomException("이미 존재하는 옵션입니다.");
            }
            chBackLightTitaniumGoldRepository.save(ChBackLightTitaniumGold.create(vo));
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

        }

        throw new CustomException("잘못된 materialType 입니다.");
    }


    /**
     * 후광 채널 티타늄골드 단가 조회
     */
    public List<StandardCostVo> getBackLightTitaniumGold() {

        return chBackLightTitaniumGoldRepository.findAll().stream().map(chBackLightTitaniumGold -> {
            return modelMapper.map(chBackLightTitaniumGold, StandardCostVo.class);
        }).collect(Collectors.toList());
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

        }
    }


    /**
     * 후광 채널 티타늄골드 단가 수정
     */
    public void updateBackLightTitaniumGold(List<StandardCostVo> standardCostVos) {

        for(StandardCostVo vo : standardCostVos){
            chBackLightTitaniumGoldRepository.findById(vo.getId()).orElseThrow(() -> {
                throw new NoSuchIdException("존재하지 않는 id 입니다.");
            }).updateCost(vo.getCost());
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

