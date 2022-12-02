package app.adoneadmin.domain.estimate.requestEstimate;

import app.adoneadmin.domain.base.BaseTimeEntity;
import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.domain.image.requestEstimate.BuildingImage;
import app.adoneadmin.domain.image.requestEstimate.ReferenceImage;
import app.adoneadmin.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"member"})
@Getter
@Entity
public class RequestEstimate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestEstimateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Embedded
    private RequestUserInfo requestUserInfo;

    @Column(nullable = false)
    private String desiredDate; // 시공희망일 ex 2주 이내

    @Column(nullable = false)
    private String floor;

    private String requestedContent;   // 요청사항

    @Builder.Default
    @Column(nullable = false)
    private Boolean isContract = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isDemolish = false;

    @Builder.Default
    @OneToMany(mappedBy = "requestEstimate",fetch = FetchType.LAZY)
    private List<BuildingImage> buildingImageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "requestEstimate",fetch = FetchType.LAZY)
    private List<ReferenceImage> referenceImageList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "requestEstimate", fetch = FetchType.LAZY)
    private List<BidingEstimate> bidingEstimateList = new ArrayList<>();

}
