package app.adoneadmin.service;

import app.adoneadmin.domain.signboard.SbFrontFrame;
import app.adoneadmin.domain.signboard.constant.MaterialType;
import app.adoneadmin.vo.channel.FrontLightVo;
import app.adoneadmin.vo.signboard.FrontFrameVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class ChannelService {

//    private final
//
//    /**
//     * 전광 채널 단가 추가
//     */
//    public void createFrontLight(String materialType, List<FrontLightVo> frontLightVos) {
//
//        switch (MaterialType.of(materialType)) {
//            case ALUMINUM:
//                for (FrontLightVo vo : frontLightVos) {
//
//                    SbFrontFrame frontFrame = sbFrontFrameRepository.isStandardExist(vo.getStandard());
//
//                    // standard 이미 있는 경우 -> 기존 레코드에 추가
//                    if(frontFrame != null){
//                        frontFrame.updateAlu(vo.getCost());
//                    } else {     // 없는 경우 -> 새로 생성
//                        SbFrontFrame alu = SbFrontFrame.createAlu(vo.getStandard(), vo.getCost());
//                        sbFrontFrameRepository.save(alu);
//                    }
//                }
//                break;
//
//            case GALVA:
//
//                break;
//
//            case STAN:
//
//                break;
//        }
//    }

}

