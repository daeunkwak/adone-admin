package app.adoneadmin.domain.member;

import app.adoneadmin.domain.base.BaseTimeEntity;
import app.adoneadmin.domain.image.companyRegister.CompanyRegisterImage;
import app.adoneadmin.domain.image.member.MemberImage;
import app.adoneadmin.domain.member.constant.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"memberImage", "companyRegisterImage"})
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String phone;

    private String storeLocation;

    private String companyName; // 상호자등록증 상호명

    private String companyRegistrationNumber;   // 사업자등록번호

    private String representName;    // 대표자명

    private String representPhone;   // 대표자 연락처

    private String representImage;  // 대표 이미지

    private String fullAddress; // 주소

    private String buildingAddress; // 도로명/지번 주소

    private String detailAddress; // 상세 주소

    private String bio; // 인사말

    private Boolean isApproval; // jwt 검증?

    private Boolean isAuthorized; // 승인 여부

    private String jwt;

    @Builder.Default
    @Column(name = "member_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole = MemberRole.USER;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(joinColumns = @JoinColumn(name = "member_id"))
    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "role")
    private Set<MemberRole> roleSet = new HashSet<MemberRole>(List.of(MemberRole.USER));

    @Builder.Default
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private MemberImage memberImage;

    @Builder.Default
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private CompanyRegisterImage companyRegisterImage;

    public Member() {
    }

}
