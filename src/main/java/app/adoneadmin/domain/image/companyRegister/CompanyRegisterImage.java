package app.adoneadmin.domain.image.companyRegister;

import app.adoneadmin.domain.image.Image;
import app.adoneadmin.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"member"})
@DiscriminatorValue("member")
@Entity
@Builder
@AllArgsConstructor
public class CompanyRegisterImage extends Image {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    protected CompanyRegisterImage(){}

    public CompanyRegisterImage(Member member, String imagerUrl){
        super(imagerUrl);
        this.member = member;
    }
}
