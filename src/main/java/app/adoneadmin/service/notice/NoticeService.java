package app.adoneadmin.service.notice;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.member.MemberRepository;
import app.adoneadmin.repository.notice.NoticeRepository;
import app.adoneadmin.vo.notice.NoticeDetailResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    /**
     * 공지사항 생성
     */
    public Notice createNotice(Member member, String noticeContent, String noticeName) {

        log.info("왜그래왜그래왜그래왜그래왜그래왜그래왜그래왜그래왜그래왜그래");
        Notice notice = Notice.create(member, noticeName, noticeContent);
        return noticeRepository.save(notice);
    }

    /**
     * 공지사항 리스트 조회
     */
    public List<Notice> getNoticeList() {
        return noticeRepository.findAll();
    }

    /**
     * 공지사항 단건 조회
     */
    public NoticeDetailResponseVo getNoticeDetail(Long noticeId) {

        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new NoSuchIdException("존재하지 않는 공지사항 id 입니다."));

        NoticeDetailResponseVo noticeDetailResponseVo = new NoticeDetailResponseVo();
        noticeDetailResponseVo.setNotice(notice);
        noticeDetailResponseVo.setNoticeFileList(notice.getNoticeFileList());
        return noticeDetailResponseVo;
    }

    /**
     * 공지사항 단건 조회
     */
    public Notice getNotice(Long noticeId) {

        return noticeRepository.getById(noticeId);
    }

    /**
     * 공지사항 검색
     */
    public List<Notice> getNoticeSearch(String searchWord) {

        return noticeRepository.getNoticeSearch(searchWord);
    }
}