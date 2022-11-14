package app.adoneadmin.vo.notice;

import app.adoneadmin.domain.file.notice.NoticeFile;
import app.adoneadmin.domain.notice.Notice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoticeDetailResponseVo {

    private Notice notice;

    private List<NoticeFile> noticeFileList;
}
