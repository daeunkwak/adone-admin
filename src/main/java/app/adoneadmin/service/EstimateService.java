package app.adoneadmin.service;

import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.domain.estimate.constant.BidingState;
import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.estimate.EstimateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class EstimateService {

    private final EstimateRepository estimateRepository;


    /**
     * 견적 리스트 조회
     */
    public List<BidingEstimate> getEstimates(String status) {

        if(status.equals("WAIT")){
            return estimateRepository.findAll().stream().filter(estimate -> {
                return estimate.getBidingState().equals(BidingState.WAIT);
            }).collect(Collectors.toList());
        } else {
            return estimateRepository.findAll().stream().filter(this::statusCheck).collect(Collectors.toList());
        }

    }


    /**
     * 견적 단건 조회
     */
    public BidingEstimate getEstimate(long bidingEstimateId) {
        return estimateRepository.findById(bidingEstimateId).orElseThrow(() -> new NoSuchIdException("존재하지 않는 견적입니다."));
    }


    /**
     * 견적 검색
     */
    public List<BidingEstimate> searchEstimates(String searchWord, String status) {

        if(status.equals(BidingState.WAIT.toString())) {
            return estimateRepository.searchEstimates(searchWord).stream().filter(estimate ->
                    estimate.getBidingState().equals(BidingState.WAIT)).collect(Collectors.toList());
        }else {
            return estimateRepository.searchEstimates(searchWord).stream().filter(this::statusCheck).collect(Collectors.toList());
        }

    }


    /**
     * 견적 삭제
     */
    public void deleteEstimates(DeleteRequestDto req) {

        for(long id : req.getIdList()){
            BidingState state = findEstimateOrThrow(id).getBidingState();
            if (state.equals(BidingState.COMPLETE) || state.equals(BidingState.SUCCESS)){
                throw new CustomException("계약이 완료되어 삭제할 수 없는 견적입니다.");
            }else {
                estimateRepository.deleteById(id);
            }
        }

    }


    private boolean statusCheck(BidingEstimate bidingEstimate){
        return bidingEstimate.getBidingState().equals(BidingState.SUCCESS) || bidingEstimate.getBidingState().equals(BidingState.COMPLETE);
    }


    private BidingEstimate findEstimateOrThrow(Long bidingEstimateId){
        return estimateRepository.findById(bidingEstimateId).orElseThrow(() -> {
            throw new NoSuchIdException("요청하신 견적은 존재하지 않습니다.");
        });
    }

}
