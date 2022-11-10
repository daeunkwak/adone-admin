package app.adoneadmin.service.notice;

import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class NoticeService {

    NoticeRepository noticeRepository;

    /**
     * 공지사항 생성
     */
    public Notice createNotice(Notice notice) {

        noticeRepository.save(notice);
        return notice;
    }

}
