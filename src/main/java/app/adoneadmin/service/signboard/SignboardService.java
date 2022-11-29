package app.adoneadmin.service.signboard;

import app.adoneadmin.domain.signboard.*;
import app.adoneadmin.domain.signboard.constant.MaterialType;
import app.adoneadmin.dto.signboard.SignboardDeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.signboard.*;
import app.adoneadmin.repository.signboard.frontframe.SbFrontFrameRepository;
import app.adoneadmin.vo.signboard.FrontFrameVo;
import app.adoneadmin.vo.signboard.StandardMaterialVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class SignboardService {

    private final SbFrontTrussRepository sbFrontTrussRepository;
    private final SbHoldingFrameRepository sbHoldingFrameRepository;
    private final SbProtrudingFrameRepository sbProtrudingFrameRepository;
    private final SbFrontFrameRepository sbFrontFrameRepository;
    private final ModelMapper modelMapper;


    /**
     * 전면 트러스 항목 추가
     */
    public void createFrontTruss(List<StandardMaterialVo> standardMaterialVoList) {

        for (StandardMaterialVo vo : standardMaterialVoList) {
            SbFrontTruss sbFrontTruss = SbFrontTruss.create(vo.getStandard(), vo.getAluminum(), vo.getGalva(), vo.getStan());
            sbFrontTrussRepository.save(sbFrontTruss);
        }
    }

    /**
     * 돌출 프레임 항목 추가
     */
    public void createProtrudingFrame(List<StandardMaterialVo> standardMaterialVoList) {

        for (StandardMaterialVo vo : standardMaterialVoList) {
            SbProtrudingFrame sbProtrudingFrame = SbProtrudingFrame.create(vo.getStandard(), vo.getAluminum(), vo.getGalva(), vo.getStan());
            sbProtrudingFrameRepository.save(sbProtrudingFrame);
        }
    }

    /**
     * 지주 프레임 항목 추가
     */
    public void createHoldingFrame(List<StandardMaterialVo> standardMaterialVoList) {

        for (StandardMaterialVo vo : standardMaterialVoList) {
            SbHoldingFrame sbHoldingFrame = SbHoldingFrame.create(vo.getStandard(), vo.getAluminum(), vo.getGalva(), vo.getStan());
            sbHoldingFrameRepository.save(sbHoldingFrame);
        }
    }

    /**
     * 전면 프레임 항목 추가
     */
    public void createFrontFrame(String materialType, List<FrontFrameVo> frontFrameVoList){
        switch (MaterialType.of(materialType)) {
            case ALUMINUM:
                for (FrontFrameVo vo : frontFrameVoList) {
                    SbFrontFrame alu = SbFrontFrame.createAlu(vo.getStandard(), vo.getCost());
                    sbFrontFrameRepository.save(alu);
                }
                break;
            case GALVA:
                for (FrontFrameVo vo : frontFrameVoList) {
                    SbFrontFrame galva = SbFrontFrame.createGalva(vo.getStandard(), vo.getCost());
                    sbFrontFrameRepository.save(galva);
                }
                break;
            case STAN:
                for(FrontFrameVo vo : frontFrameVoList){
                    SbFrontFrame stan = SbFrontFrame.createStan(vo.getStandard(), vo.getCost());
                    sbFrontFrameRepository.save(stan);
                }
                break;
        }
    }

    /**
     * 전면 트러스 단가표 조회
     */
    public List<StandardMaterialVo> getFrontTruss() {

        List<SbFrontTruss> sbFrontTrussList = sbFrontTrussRepository.findAll();
        return sbFrontTrussList.stream().map(sbFrontTruss ->
                modelMapper.map(sbFrontTruss, StandardMaterialVo.class)).collect(Collectors.toList());
    }

    /**
     * 돌출 프레임 단가표 조회
     */
    public List<StandardMaterialVo> getProtrudingFrame() {

        List<SbProtrudingFrame> sbProtrudingFrameList = sbProtrudingFrameRepository.findAll();
        return sbProtrudingFrameList.stream().map(sbProtrudingFrame ->
                modelMapper.map(sbProtrudingFrame, StandardMaterialVo.class)).collect(Collectors.toList());
    }

    /**
     * 지주 프레임 단가표 조회
     */
    public List<StandardMaterialVo> getHoldingFrame() {

        List<SbHoldingFrame> sbHoldingFrameList = sbHoldingFrameRepository.findAll();
        return sbHoldingFrameList.stream().map(sbHoldingFrame ->
                modelMapper.map(sbHoldingFrame, StandardMaterialVo.class)).collect(Collectors.toList());
    }

    /**
     * 전면 프레임 단가표 조회
     */
    public List<FrontFrameVo> getFrontFrame(String materialType) {

        // TODO : 중복되는 코드 처리
        List<SbFrontFrame> sbFrontFrames;
        List<FrontFrameVo> frontFrameVos = new ArrayList<>();

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                sbFrontFrames = sbFrontFrameRepository.findAll()
                        .stream().filter(sbFrontFrameAlu -> sbFrontFrameAlu.getAlu() != -1).collect(Collectors.toList());

                for(SbFrontFrame sbFrontFrame : sbFrontFrames){
                    FrontFrameVo vo = new FrontFrameVo();
                    vo.setId(sbFrontFrame.getId());
                    vo.setStandard(sbFrontFrame.getStandard());
                    vo.setCost(sbFrontFrame.getAlu());
                    frontFrameVos.add(vo);
                }
                return frontFrameVos;

            case GALVA:
                sbFrontFrames = sbFrontFrameRepository.findAll()
                        .stream().filter(sbFrontFrameGalva -> sbFrontFrameGalva.getGalva() != -1).collect(Collectors.toList());

                for(SbFrontFrame sbFrontFrame : sbFrontFrames){
                    FrontFrameVo vo = new FrontFrameVo();
                    vo.setId(sbFrontFrame.getId());
                    vo.setStandard(sbFrontFrame.getStandard());
                    vo.setCost(sbFrontFrame.getGalva());
                    frontFrameVos.add(vo);
                }
                return frontFrameVos;

            case STAN:
                sbFrontFrames = sbFrontFrameRepository.findAll()
                        .stream().filter(sbFrontFrameStan -> sbFrontFrameStan.getStan() != -1).collect(Collectors.toList());

                for(SbFrontFrame sbFrontFrame : sbFrontFrames){
                    FrontFrameVo vo = new FrontFrameVo();
                    vo.setId(sbFrontFrame.getId());
                    vo.setStandard(sbFrontFrame.getStandard());
                    vo.setCost(sbFrontFrame.getStan());
                    frontFrameVos.add(vo);
                }
                return frontFrameVos;
        }

        throw new CustomException("잘못된 materialType 입니다.");
    }

    /**
     * 전면 트러스 단가 수정
     */
    public void updateFrontTruss(List<StandardMaterialVo> standardMaterialVoList) {

        for(StandardMaterialVo vo : standardMaterialVoList){
            SbFrontTruss sbFrontTruss = sbFrontTrussRepository.findById(vo.getId()).orElseThrow(() -> new NoSuchIdException("존재하지 않는 단가 id 입니다."));
            sbFrontTruss.setStandard(vo.getStandard());
            sbFrontTruss.setAluminum(vo.getAluminum());
            sbFrontTruss.setGalva(vo.getGalva());
            sbFrontTruss.setStan(vo.getStan());
            sbFrontTrussRepository.save(sbFrontTruss);
        }
    }

    /**
     * 돌출 프레임 단가 수정
     */
    public void updateProtrudingFrame(List<StandardMaterialVo> standardMaterialVoList) {

        for(StandardMaterialVo vo : standardMaterialVoList){
            SbProtrudingFrame sbProtrudingFrame = sbProtrudingFrameRepository.findById(vo.getId()).orElseThrow(() -> new NoSuchIdException("존재하지 않는 단가 id 입니다."));
            sbProtrudingFrame.setStandard(vo.getStandard());
            sbProtrudingFrame.setAluminum(vo.getAluminum());
            sbProtrudingFrame.setGalva(vo.getGalva());
            sbProtrudingFrame.setStan(vo.getStan());
            sbProtrudingFrameRepository.save(sbProtrudingFrame);
        }
    }

    /**
     * 지주 프레임 단가 수정
     */
    public void updateHoldingFrame(List<StandardMaterialVo> standardMaterialVoList) {

        for(StandardMaterialVo vo : standardMaterialVoList){
            SbHoldingFrame sbHoldingFrame = sbHoldingFrameRepository.findById(vo.getId()).orElseThrow(() -> new NoSuchIdException("존재하지 않는 단가 id 입니다."));
            sbHoldingFrame.setStandard(vo.getStandard());
            sbHoldingFrame.setAluminum(vo.getAluminum());
            sbHoldingFrame.setGalva(vo.getGalva());
            sbHoldingFrame.setStan(vo.getStan());
            sbHoldingFrameRepository.save(sbHoldingFrame);
        }
    }

    /**
     * 전면 프레임 단가 수정
     */
    public void updateFrontFrame(List<FrontFrameVo> frontFrameVos, String materialType) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                for(FrontFrameVo vo : frontFrameVos){
                    findSbFrontFrameOrThrow(vo.getId()).updateAlu(vo.getCost());
                }

            case GALVA:
                for(FrontFrameVo vo : frontFrameVos){
                    findSbFrontFrameOrThrow(vo.getId()).updateGalva(vo.getCost());
                }

            case STAN:
                for(FrontFrameVo vo : frontFrameVos){
                    findSbFrontFrameOrThrow(vo.getId()).updateStan(vo.getCost());
                }
        }
    }

    /**
     * 전면 트러스 단가 삭제
     */
    public void deleteFrontTruss(long id) {

        sbFrontTrussRepository.deleteById(id);
    }

    /**
     * 돌출 프레임 단가 삭제
     */
    public void deleteProtrudingFrame(long id) {

        sbProtrudingFrameRepository.deleteById(id);
    }

    /**
     * 지주 프레임 단가 삭제
     */
    public void deleteHoldingFrame(long id) {

        sbHoldingFrameRepository.deleteById(id);
    }

    /**
     * 전면 프레임 단가 삭제
     */
    public void deleteFrontFrame(SignboardDeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                for(long id : req.getIdList()){
                    SbFrontFrame sbFrontFrame = findSbFrontFrameOrThrow(id);
                    sbFrontFrame.updateAlu(-1);
                }

            case GALVA:
                for(long id : req.getIdList()){
                    SbFrontFrame sbFrontFrame = findSbFrontFrameOrThrow(id);
                    sbFrontFrame.updateGalva(-1);
                }

            case STAN:
                for(long id : req.getIdList()){
                    SbFrontFrame sbFrontFrame = findSbFrontFrameOrThrow(id);
                    sbFrontFrame.updateStan(-1);
                }
        }
    }


    private SbFrontFrame findSbFrontFrameOrThrow(Long id){
        return sbFrontFrameRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchIdException("존재하지 않는 단가 id 입니다.");
        });
    }


}

