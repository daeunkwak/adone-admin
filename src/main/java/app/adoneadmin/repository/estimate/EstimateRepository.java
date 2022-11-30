package app.adoneadmin.repository.estimate;

import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EstimateRepository extends JpaRepository<BidingEstimate, Long> {

    @Transactional
    @Query("select e from BidingEstimate e where e.member.companyName like %:searchWord% order by e.bidingEstimateId desc")
    List<BidingEstimate> searchEstimates(@Param("searchWord") String searchWord);
}
