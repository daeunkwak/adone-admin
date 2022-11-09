package app.adoneadmin.domain.notice;

import app.adoneadmin.domain.base.BaseTimeEntity;
import app.adoneadmin.domain.image.notice.NoticeImage;
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
@Setter
@Entity
public class Notice extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String noticeName;

    private String noticeContent;

    @Builder.Default
    @OneToMany(mappedBy = "notice", fetch = FetchType.LAZY)
    private List<NoticeImage> noticeImageList = new ArrayList<>();

}
