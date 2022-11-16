package app.adoneadmin.repository.notice;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Transactional
    @Query("select n from Notice n where n.noticeName like %:searchWord% order by n.noticeId desc")
    List<Notice> getNoticeSearch(@Param("searchWord") String searchWord);

}
