package app.adoneadmin.domain.image.member;

import app.adoneadmin.domain.image.Image;
import app.adoneadmin.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString(exclude = {"member"})
@DiscriminatorValue("member")
@Entity
@Builder
@AllArgsConstructor
public class MemberImage extends Image {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    protected MemberImage(){}

    public MemberImage(Member member, String imagerUrl){
        super(imagerUrl);
        this.member = member;
    }

}
