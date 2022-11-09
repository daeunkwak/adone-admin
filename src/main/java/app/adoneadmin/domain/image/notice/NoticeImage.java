package app.adoneadmin.domain.image.notice;


import app.adoneadmin.domain.image.Image;
import app.adoneadmin.domain.notice.Notice;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"notice"})
@DiscriminatorValue("notice")
@Entity
public class NoticeImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false)
    private Notice notice;

    protected NoticeImage(){}

    public NoticeImage(Notice notice, String imageUrl){
        super(imageUrl);
        this.notice = notice;
    }

}
