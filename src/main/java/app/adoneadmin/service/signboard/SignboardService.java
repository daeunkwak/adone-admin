package app.adoneadmin.service.signboard;

import app.adoneadmin.domain.signboard.SbFrontTruss;
import app.adoneadmin.domain.signboard.SbHoldingFrame;
import app.adoneadmin.domain.signboard.SbProtrudingFrame;
import app.adoneadmin.dto.common.CommonApiResult;
import app.adoneadmin.dto.signboard.request.StandardMaterialDeleteRequestDto;
import app.adoneadmin.dto.signboard.request.StandardMaterialRequestDto;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.signboard.SbFrontFrameRepository;
import app.adoneadmin.repository.signboard.SbFrontTrussRepository;
import app.adoneadmin.repository.signboard.SbHoldingFrameRepository;
import app.adoneadmin.repository.signboard.SbProtrudingFrameRepository;
import app.adoneadmin.vo.signboard.StandardMaterialVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class SignboardService {

    private final SbFrontTrussRepository sbFrontTrussRepository;
    private final SbHoldingFrameRepository sbHoldingFrameRepository;
    private final SbProtrudingFrameRepository sbProtrudingFrameRepository;
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


}























