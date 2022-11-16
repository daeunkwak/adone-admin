package app.adoneadmin.domain.file.notice;

import app.adoneadmin.domain.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {
    @Query("select nf.fileId from NoticeFile nf where nf.notice.noticeId =:noticeId")
    List<Long> findByNoticeId(@Param("noticeId") Long noticeId);

    @Query("delete from NoticeFile nf where nf.notice.noticeId =:noticeId")
    void deleteByNoticeId(Long noticeId);

//    @Query("update File f set f.isDeleted")
//    void updateIsDeleted(@Param("fileId") Long fileId);
}
