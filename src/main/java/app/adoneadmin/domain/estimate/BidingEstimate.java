package app.adoneadmin.domain.estimate;

import app.adoneadmin.domain.base.BaseTimeEntity;
import app.adoneadmin.domain.estimate.constant.BidingState;
import app.adoneadmin.domain.estimate.requestEstimate.RequestEstimate;
import app.adoneadmin.domain.image.bidingEstimate.PastConstructionImage;
import app.adoneadmin.domain.member.Member;
import app.adoneadmin.domain.signboard.SignboardDesignContractor;
import lombok.*;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"member","requestEstimate"})
@Getter
@Setter
@Entity
public class BidingEstimate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bidingEstimateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_estimate_id")
    private RequestEstimate requestEstimate;    // 요청서 객체

    private int productionCost; // 디자인, 제작비

    private int constructionCost;   // 시공비

    private int equipmentCost;  // 장비사용료

    private int authorizationCost;  // 인허가대행비

    private int etcCost;    // 기타비용

    private String etcContent; // 추가사항

    private int sbLightingPrice;

    private int sbDivisionPrice;

    private int sbSizePrice;

    private int sbColorPrice;

    private int chLightingPrice;

    private int chDivisionPrice;

    private int chSizePrice;

    private int chColorPrice;

    private Integer chLightingQuantity;

    private Integer chDivisionQuantity;

    private Integer chSizeQuantity;

    private Integer chColorQuantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signboard_design_contractor_id")
    private SignboardDesignContractor signboardDesignContractor;

    @Column(nullable = false)
    private String warrantyPeriod;  // 보증기간

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BidingState bidingState = BidingState.WAIT;

    @Builder.Default
    @OneToMany(mappedBy = "bidingEstimate", fetch = FetchType.LAZY)
    private List<PastConstructionImage> pastConstructionImageList = new ArrayList<>();  // 비슷한 스타일 시공사진

}
