package app.adoneadmin.domain.signboard;

import app.adoneadmin.domain.estimate.BidingEstimate;
import app.adoneadmin.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@ToString(exclude = {"member"})   // TODO : ToString "bidingEstimate" 제거
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignboardDesignContractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "signboard_design_contractor_id")
    private Long signboardDesignContractorId;

    @ManyToOne(fetch = FetchType.LAZY)    //TODO : FetchType EAGER -> LAZY 변경
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name = "biding_estimate_id")
    @OneToOne(mappedBy = "signboardDesignContractor")
    private BidingEstimate bidingEstimate;

    @Embedded
    private Signboard signboard;

    @Embedded
    private Typography typography;


    public static SignboardDesignContractor create(Member member, Signboard signboard, Typography typography) {

        return new SignboardDesignContractorBuilder()

                .member(member)
                .signboard(signboard)
                .typography(typography)
                .build();
    }
}
