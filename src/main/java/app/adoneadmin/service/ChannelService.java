package app.adoneadmin.service;

import app.adoneadmin.domain.channel.back.ChBackGalva;
import app.adoneadmin.domain.channel.back.ChBackStan;
import app.adoneadmin.domain.channel.back.ChBackTitanium;
import app.adoneadmin.domain.channel.backlight.ChBackLightGalva;
import app.adoneadmin.domain.channel.backlight.ChBackLightStan;
import app.adoneadmin.domain.channel.backlight.ChBackLightTitaniumGold;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightAlu;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightGalva;
import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightStan;
import app.adoneadmin.domain.channel.frontlight.*;
import app.adoneadmin.domain.channel.scasi.ChScasiAcryl;
import app.adoneadmin.domain.channel.scasi.ChScasiFormex;
import app.adoneadmin.domain.channel.scasi.ChScasiRubber;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.channel.back.ChBackGalvaRepository;
import app.adoneadmin.repository.channel.back.ChBackStanRepository;
import app.adoneadmin.repository.channel.back.ChBackTitaniumRepository;
import app.adoneadmin.repository.channel.backlight.ChBackLightGalvaRepository;
import app.adoneadmin.repository.channel.backlight.ChBackLightStanRepository;
import app.adoneadmin.repository.channel.backlight.ChBackLightTitaniumGoldRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackAluRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackGalvaRepository;
import app.adoneadmin.repository.channel.frontbacklight.ChFrontBackStanRepository;
import app.adoneadmin.repository.channel.frontlight.*;
import app.adoneadmin.repository.channel.scasi.ChScasiAcrylRepository;
import app.adoneadmin.repository.channel.scasi.ChScasiFormexRepository;
import app.adoneadmin.repository.channel.scasi.ChScasiRubberRepository;
import app.adoneadmin.vo.channel.FrontBackLightVo;
import app.adoneadmin.vo.channel.BackLightVo;
import app.adoneadmin.vo.channel.FrontLightVo;
import app.adoneadmin.vo.channel.ScasiVo;
import app.adoneadmin.vo.signboard.StandardCostVo;
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
    private final ChBackGalvaRepository chBackGalvaRepository;
    private final ChBackStanRepository chBackStanRepository;
    private final ChBackTitaniumRepository chBackTitaniumRepository;
    private final ChScasiFormexRepository chScasiFormexRepository;
    private final ChScasiRubberRepository chScasiRubberRepository;
    private final ChScasiAcrylRepository chScasiAcrylRepository;
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

            case TITANIUMGOLD:
                for(BackLightVo vo : backLightVos){
                    if(chBackLightTitaniumGoldRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chBackLightTitaniumGoldRepository.save(ChBackLightTitaniumGold.create(vo));
                } break;


        }
    }


//    /**
//     * 후광 채널 티타늄골드 단가 추가
//     */
//    public void createBackLightTitaniumGold(List<StandardCostVo> backLightVos) {
//
//        for(StandardCostVo vo : backLightVos){
//            if(chBackLightTitaniumGoldRepository.findByStandard(vo.getStandard()) != null){
//                throw new CustomException("이미 존재하는 옵션입니다.");
//            }
//            chBackLightTitaniumGoldRepository.save(ChBackLightTitaniumGold.create(vo));
//        }
//    }


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

            case TITANIUMGOLD:
                for(BackLightVo vo : backLightVos){
                    chBackLightTitaniumGoldRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateBackLightTitaniumGold(vo);
                } break;

        }
    }


//    /**
//     * 후광 채널 티타늄골드 단가 수정
//     */
//    public void updateBackLightTitaniumGold(List<StandardCostVo> standardCostVos) {
//
//        for(StandardCostVo vo : standardCostVos){
//            chBackLightTitaniumGoldRepository.findById(vo.getId()).orElseThrow(() -> {
//                throw new NoSuchIdException("존재하지 않는 id 입니다.");
//            }).updateCost(vo.getCost());
//        }
//    }


    /**
     * 후광 채널 단가 삭제
     */
    public void deleteBackLight(DeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case GALVA:
                for(long id : req.getIdList()){
                    chBackGalvaRepository.deleteById(id);
                } break;

            case STAN:
                for(long id : req.getIdList()){
                    chBackStanRepository.deleteById(id);
                } break;
            case TITANIUMGOLD:
                for(long id : req.getIdList()){
                    chBackTitaniumRepository.deleteById(id);
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


    /**
     * 스카시 채널 단가 추가
     */
    public void createScasi(String materialType, List<ScasiVo> scasiVos) {

        switch (MaterialType.of(materialType)) {
            case ACRYL:
                for(ScasiVo vo : scasiVos){
                    if(chScasiAcrylRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chScasiAcrylRepository.save(ChScasiAcryl.create(vo));
                } break;

            case FORMEX:
                for(ScasiVo vo : scasiVos){
                    if(chScasiFormexRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chScasiFormexRepository.save(ChScasiFormex.create(vo));
                } break;

            case RUBBER:
                for(ScasiVo vo : scasiVos){
                    if(chScasiRubberRepository.findByStandard(vo.getStandard()) != null){
                        throw new CustomException("이미 존재하는 옵션입니다.");
                    }
                    chScasiRubberRepository.save(ChScasiRubber.create(vo));
                } break;

        }
    }


    /**
     * 스카시 채널 단가 조회
     */
    public List<ScasiVo> getScasi(String materialType) {

        switch (MaterialType.of(materialType)){
            case ACRYL:
                return chScasiAcrylRepository.findAll().stream().map(chScasiAcryl -> {
                    return modelMapper.map(chScasiAcryl, ScasiVo.class);
                }).collect(Collectors.toList());

            case FORMEX:
                return chScasiFormexRepository.findAll().stream().map(chScasiFormex -> {
                    return modelMapper.map(chScasiFormex, ScasiVo.class);
                }).collect(Collectors.toList());

            case RUBBER:
                return chScasiRubberRepository.findAll().stream().map(chScasiRubber -> {
                    return modelMapper.map(chScasiRubber, ScasiVo.class);
                }).collect(Collectors.toList());
        }

        throw new CustomException("잘못된 materialType 입니다.");
    }


    /**
     * 스카시 채널 단가 수정
     */
    public void updateScasi(String materialType, List<ScasiVo> scasiVos) {

        switch (MaterialType.of(materialType)){
            case ACRYL:
                for(ScasiVo vo : scasiVos){
                    chScasiAcrylRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateScasiAcryl(vo);
                } break;

            case FORMEX:
                for(ScasiVo vo : scasiVos){
                    chScasiFormexRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateScasiFormex(vo);
                } break;

            case RUBBER:
                for(ScasiVo vo : scasiVos){
                    chScasiRubberRepository.findById(vo.getId()).orElseThrow(() -> {
                        throw new NoSuchIdException("존재하지 않는 id 입니다.");
                    }).updateScasiRubber(vo);
                } break;
        }

    }



    /**
     * 스카시 채널 단가 삭제
     */
    public void deleteScasi(DeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case ACRYL:
                for(long id : req.getIdList()){
                    chScasiAcrylRepository.deleteById(id);
                } break;

            case FORMEX:
                for(long id : req.getIdList()){
                    chScasiFormexRepository.deleteById(id);
                } break;

            case RUBBER:
                for(long id : req.getIdList()){
                    chScasiRubberRepository.deleteById(id);
                } break;
        }

    }

}

