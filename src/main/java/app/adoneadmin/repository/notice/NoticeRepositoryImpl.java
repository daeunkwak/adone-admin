package app.adoneadmin.repository.notice;

import app.adoneadmin.domain.notice.Notice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static app.adoneadmin.domain.file.notice.QNoticeFile.noticeFile;
import static app.adoneadmin.domain.notice.QNotice.notice;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryQuerydsl{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Notice getNotice(Long noticeId) {
        return jpaQueryFactory.selectFrom(notice)
                .innerJoin(notice.noticeFileList, noticeFile)
                .where(notice.noticeId.eq(noticeId)
                        .and(noticeFile.isDeleted.eq(false)))
                .fetchOne();
    }
}
