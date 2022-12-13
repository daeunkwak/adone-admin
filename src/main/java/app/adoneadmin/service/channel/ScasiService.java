package app.adoneadmin.service.channel;

import app.adoneadmin.domain.channel.scasi.ChScasiAcryl;
import app.adoneadmin.domain.channel.scasi.ChScasiFormex;
import app.adoneadmin.domain.channel.scasi.ChScasiRubber;
import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.channel.scasi.ChScasiAcrylRepository;
import app.adoneadmin.repository.channel.scasi.ChScasiFormexRepository;
import app.adoneadmin.repository.channel.scasi.ChScasiRubberRepository;
import app.adoneadmin.vo.channel.ScasiVo;
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
public class ScasiService {

    private final ChScasiFormexRepository chScasiFormexRepository;
    private final ChScasiRubberRepository chScasiRubberRepository;
    private final ChScasiAcrylRepository chScasiAcrylRepository;
    private final ModelMapper modelMapper;


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
