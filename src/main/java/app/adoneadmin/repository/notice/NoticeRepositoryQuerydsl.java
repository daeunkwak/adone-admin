package app.adoneadmin.repository.notice;

import app.adoneadmin.domain.notice.Notice;

public interface NoticeRepositoryQuerydsl {
    Notice getNotice(Long noticeId);
}
