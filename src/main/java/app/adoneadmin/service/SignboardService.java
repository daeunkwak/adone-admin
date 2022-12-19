package app.adoneadmin.service;

import app.adoneadmin.domain.constant.MaterialType;
import app.adoneadmin.domain.signboard.unit.*;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.signboard.*;
import app.adoneadmin.repository.signboard.frontframe.SbFrontFrameRepository;
import app.adoneadmin.vo.signboard.LaserVo;
import app.adoneadmin.vo.signboard.PointVo;
import app.adoneadmin.vo.signboard.StandardCostVo;
import app.adoneadmin.vo.signboard.StandardMaterialVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final SbFrontFrameRepository sbFrontFrameRepository;
    private final SbLaserRepository sbLaserRepository;
    private final SbPointRepository sbPointRepository;
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
    public void createFrontFrame(String materialType, List<StandardCostVo> standardCostVoList){
        switch (MaterialType.of(materialType)) {
            case ALUMINUM:
                for (StandardCostVo vo : standardCostVoList) {

                    SbFrontFrame frontFrame = sbFrontFrameRepository.isStandardExist(vo.getStandard());

                    // standard 이미 있는 경우 -> 기존 레코드에 추가
                    if(frontFrame != null){
                        frontFrame.updateAlu(vo.getCost());
                    } else {     // 없는 경우 -> 새로 생성
                        SbFrontFrame alu = SbFrontFrame.createAlu(vo.getStandard(), vo.getCost());
                        log.info("loggggggggggggggg :::::::::: " + alu);
                        sbFrontFrameRepository.save(alu);
                    }
                }
                break;

            case GALVA:
                for (StandardCostVo vo : standardCostVoList) {
                    SbFrontFrame frontFrame = sbFrontFrameRepository.isStandardExist(vo.getStandard());

                    if(frontFrame != null){
                        frontFrame.updateGalva(vo.getCost());
                    } else {
                        SbFrontFrame galva = SbFrontFrame.createGalva(vo.getStandard(), vo.getCost());
                        sbFrontFrameRepository.save(galva);
                    }
                }
                break;

            case STAN:
                for(StandardCostVo vo : standardCostVoList){
                    SbFrontFrame frontFrame = sbFrontFrameRepository.isStandardExist(vo.getStandard());

                    if(frontFrame != null){
                        frontFrame.updateStan(vo.getCost());
                    } else {
                        SbFrontFrame stan = SbFrontFrame.createStan(vo.getStandard(), vo.getCost());
                        sbFrontFrameRepository.save(stan);
                    }
                }
                break;
        }
    }

    /**
     * 전면 트러스 단가표 조회
     */
    public List<StandardMaterialVo> getFrontTruss() {

        List<SbFrontTruss> sbFrontTrussList = sbFrontTrussRepository.findAll();
        log.info("trusssssssssssssssssssss ::::: " + sbFrontTrussList);
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
    public List<StandardCostVo> getFrontFrame(String materialType) {

        // TODO : 중복되는 코드 처리
        List<SbFrontFrame> sbFrontFrames;
        List<StandardCostVo> standardCostVos = new ArrayList<>();

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                sbFrontFrames = sbFrontFrameRepository.findAll()
                        .stream().filter(sbFrontFrameAlu -> sbFrontFrameAlu.getAlu() != -1).collect(Collectors.toList());

                for(SbFrontFrame sbFrontFrame : sbFrontFrames){
                    StandardCostVo vo = new StandardCostVo();
                    vo.setId(sbFrontFrame.getId());
                    vo.setStandard(sbFrontFrame.getStandard());
                    vo.setCost(sbFrontFrame.getAlu());
                    standardCostVos.add(vo);
                }
                return standardCostVos;

            case GALVA:
                sbFrontFrames = sbFrontFrameRepository.findAll()
                        .stream().filter(sbFrontFrameGalva -> sbFrontFrameGalva.getGalva() != -1).collect(Collectors.toList());

                for(SbFrontFrame sbFrontFrame : sbFrontFrames){
                    StandardCostVo vo = new StandardCostVo();
                    vo.setId(sbFrontFrame.getId());
                    vo.setStandard(sbFrontFrame.getStandard());
                    vo.setCost(sbFrontFrame.getGalva());
                    standardCostVos.add(vo);
                }
                return standardCostVos;

            case STAN:
                sbFrontFrames = sbFrontFrameRepository.findAll()
                        .stream().filter(sbFrontFrameStan -> sbFrontFrameStan.getStan() != -1).collect(Collectors.toList());

                for(SbFrontFrame sbFrontFrame : sbFrontFrames){
                    StandardCostVo vo = new StandardCostVo();
                    vo.setId(sbFrontFrame.getId());
                    vo.setStandard(sbFrontFrame.getStandard());
                    vo.setCost(sbFrontFrame.getStan());
                    standardCostVos.add(vo);
                }
                return standardCostVos;
        }

        throw new CustomException("잘못된 materialType 입니다.");
    }

    /**
     * 전면 트러스 단가 수정
     */
    public void updateFrontTruss(List<StandardMaterialVo> standardMaterialVoList) {

        for(StandardMaterialVo vo : standardMaterialVoList){
            SbFrontTruss sbFrontTruss = sbFrontTrussRepository.findById(vo.getId()).orElseThrow(() -> new NoSuchIdException("존재하지 않는 단가 id 입니다."));
            sbFrontTruss.setAluminum(vo.getAluminum());
            sbFrontTruss.setGalva(vo.getGalva());
            sbFrontTruss.setStan(vo.getStan());
            sbFrontTruss.setStandard(vo.getStandard());
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
    public void updateFrontFrame(List<StandardCostVo> standardCostVos, String materialType) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                for(StandardCostVo vo : standardCostVos){
                    SbFrontFrame frame = findSbFrontFrameOrThrow(vo.getId());
                    if(findSbFrontFrameOrThrow(vo.getId()).getStandard().equals(vo.getStandard())){     // standard를 수정하지 않는 경우 -> 해당 materialType 값만 업데이트
                        frame.updateAlu(vo.getCost());
                    }else{
                        frame.updateAlu(-1);
                        SbFrontFrame alu = SbFrontFrame.createAlu(vo.getStandard(), vo.getCost());      // standard를 수정하는 경우 -> 새로운 레코드 생성
                        sbFrontFrameRepository.save(alu);

                        if(noneCheck(frame)){       // 해당 standard에 아무 값도 남아있지 않는 경우 -> 레코드 삭제
                            sbFrontFrameRepository.deleteById(frame.getId());
                        }
                    }
                } break;

            case GALVA:
                for(StandardCostVo vo : standardCostVos){
                    SbFrontFrame frame = findSbFrontFrameOrThrow(vo.getId());
                    if(findSbFrontFrameOrThrow(vo.getId()).getStandard().equals(vo.getStandard())){
                        frame.updateGalva(vo.getCost());
                    }else{
                        frame.updateGalva(-1);
                        SbFrontFrame galva = SbFrontFrame.createGalva(vo.getStandard(), vo.getCost());
                        sbFrontFrameRepository.save(galva);

                        if(noneCheck(frame)){
                            sbFrontFrameRepository.deleteById(frame.getId());
                        }
                    }
                } break;

            case STAN:
                for(StandardCostVo vo : standardCostVos){
                    SbFrontFrame frame = findSbFrontFrameOrThrow(vo.getId());
                    if(findSbFrontFrameOrThrow(vo.getId()).getStandard().equals(vo.getStandard())){
                        frame.updateStan(vo.getCost());
                    }else{
                        frame.updateStan(-1);
                        SbFrontFrame stan = SbFrontFrame.createGalva(vo.getStandard(), vo.getCost());
                        sbFrontFrameRepository.save(stan);

                        if(noneCheck(frame)){
                            sbFrontFrameRepository.deleteById(frame.getId());
                        }
                    }
                } break;
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
    public void deleteFrontFrame(DeleteRequestDto req, String materialType) {

        switch (MaterialType.of(materialType)){
            case ALUMINUM:
                for(long id : req.getIdList()){
                    SbFrontFrame sbFrontFrame = findSbFrontFrameOrThrow(id);
                    sbFrontFrame.updateAlu(-1);

                    if(noneCheck(sbFrontFrame)){       // 해당 standard에 아무 값도 남아있지 않는 경우 -> 레코드 삭제
                        sbFrontFrameRepository.deleteById(sbFrontFrame.getId());
                    }
                } break;

            case GALVA:
                for(long id : req.getIdList()){
                    SbFrontFrame sbFrontFrame = findSbFrontFrameOrThrow(id);
                    sbFrontFrame.updateGalva(-1);

                    if(noneCheck(sbFrontFrame)){
                        sbFrontFrameRepository.deleteById(sbFrontFrame.getId());
                    }
                } break;

            case STAN:
                for(long id : req.getIdList()){
                    SbFrontFrame sbFrontFrame = findSbFrontFrameOrThrow(id);
                    sbFrontFrame.updateStan(-1);

                    if(noneCheck(sbFrontFrame)){
                        sbFrontFrameRepository.deleteById(sbFrontFrame.getId());
                    }
                } break;
        }
    }


    private boolean noneCheck(SbFrontFrame frame){
        return frame.getGalva() == -1 && frame.getAlu() == -1 && frame.getStan() == -1;
    }


    private SbFrontFrame findSbFrontFrameOrThrow(Long id){
        return sbFrontFrameRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchIdException("존재하지 않는 단가 id 입니다.");
        });
    }


    /**
     * 레이저 타공 단가 추가
     */
    public void createLaser(List<LaserVo> laserVos) {

        for (LaserVo vo : laserVos) {
            SbLaser laser = SbLaser.create(vo.getStandard(), vo.getAluminum(), vo.getGalva(), vo.getStan());
            sbLaserRepository.save(laser);
        }
    }

    /**
     * 레이저 타공 단가표 조회
     */
    public List<LaserVo> getLaser() {

        List<SbLaser> sbLasers = sbLaserRepository.findAll();
        return sbLasers.stream().map(laser ->
                modelMapper.map(laser, LaserVo.class)).collect(Collectors.toList());
    }

    /**
     * 레이저 타공 단가 수정
     */
    public void updateLaser(List<LaserVo> laserVos) {

        for(LaserVo vo : laserVos){
            SbLaser sbLaser = sbLaserRepository.findById(vo.getId()).orElseThrow(() -> new NoSuchIdException("존재하지 않는 단가 id 입니다."));
            sbLaser.setAluminum(vo.getAluminum());
            sbLaser.setGalva(vo.getGalva());
            sbLaser.setStan(vo.getStan());
            sbLaser.setStandard(vo.getStandard());
            sbLaserRepository.save(sbLaser);
        }
    }

    /**
     * 레이저 타공 단가 삭제
     */
    public void deleteLaser(DeleteRequestDto req) {

        for(long id : req.getIdList()){
            sbLaserRepository.deleteById(id);
        }
    }

    /**
     * 돌출 포인트 단가 추가
     */
    public void createPoint(List<PointVo> pointVos) {

        for (PointVo vo : pointVos) {
            SbPoint point = SbPoint.create(vo);
            sbPointRepository.save(point);
        }
    }

    /**
     * 돌출 포인트 단가표 조회
     */
    public List<PointVo> getPoint() {

        List<SbPoint> points = sbPointRepository.findAll();
        return points.stream().map(point ->
                modelMapper.map(point, PointVo.class)).collect(Collectors.toList());
    }


    /**
     * 돌출 포인트 단가 수정
     */
    public void updatePoint(List<PointVo> pointVos) {

        for(PointVo vo : pointVos){
            SbPoint sbPoint = sbPointRepository.findById(vo.getId()).orElseThrow(() -> new NoSuchIdException("존재하지 않는 단가 id 입니다."));
            sbPoint.setCircle(vo.getCircle());
            sbPoint.setRotation(vo.getRotation());
            sbPoint.setRound(vo.getRound());
            sbPoint.setSquare(vo.getSquare());
            sbPoint.setStan(vo.getStan());
            sbPoint.setStandard(vo.getStandard());
            sbPointRepository.save(sbPoint);
        }
    }

    /**
     * 돌출 포인트 단가 삭제
     */
    public void deletePoint(DeleteRequestDto req) {

        for(long id : req.getIdList()){
            sbPointRepository.deleteById(id);
        }
    }


}

