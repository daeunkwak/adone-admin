package app.adoneadmin.domain.file.notice;


import app.adoneadmin.domain.file.File;
import app.adoneadmin.domain.notice.Notice;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(exclude = {"notice"})
@DiscriminatorValue("notice")
@Entity
public class NoticeFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id", nullable = false)
    private Notice notice;

    protected NoticeFile(){}

    public NoticeFile(Notice notice, String fileUrl){
        super(fileUrl);
        this.notice = notice;
    }

}
