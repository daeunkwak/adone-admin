package app.adoneadmin.service;

import app.adoneadmin.domain.file.notice.NoticeFile;
import app.adoneadmin.domain.file.notice.NoticeFileRepository;
import app.adoneadmin.domain.member.Member;
import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.dto.file.FileDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.global.exception.handler.NoSuchMemberException;
import app.adoneadmin.repository.member.MemberRepository;
import app.adoneadmin.repository.notice.NoticeRepository;
import app.adoneadmin.vo.notice.NoticeDetailResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final NoticeFileRepository noticeFileRepository;

    /**
     * 공지사항 생성
     */
    public Notice createNotice(Member member, String noticeContent, String noticeName) {

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

        return findNoticeOrThrow(noticeId);
    }


    /**
     * 공지사항 검색
     */
    public List<Notice> getNoticeSearch(String searchWord) {

        return noticeRepository.getNoticeSearch(searchWord);
    }

    /**
     * 공지사항 수정
     */
    public Notice updateNotice(Long memberId, Long noticeId, String noticeContent, String noticeName) {

        Notice notice = findNoticeOrThrow(noticeId);

        if(notice.getMember().getMemberId() != memberId){
            throw new CustomException("공지사항을 수정할 권한이 없습니다.");
        }

        notice.updateNoticeName(noticeName);
        notice.updateNoticeContent(noticeContent);
        return notice;
    }


    /**
     * 공지사항 삭제
     */
    public void deleteNotice(List<Long> noticeIdList) {


        for(Long noticeId : noticeIdList){


            //noticeFileRepository.deleteByNoticeId(noticeId);
            Notice notice = findNoticeOrThrow(noticeId);

            List<NoticeFile> noticeFileList = notice.getNoticeFileList();
            for(NoticeFile noticeFile : noticeFileList){
                noticeFileRepository.deleteById(noticeFile.getFileId());
            }

            noticeRepository.deleteById(noticeId);
        }
    }


    private Notice findNoticeOrThrow(Long noticeId){
        return noticeRepository.findById(noticeId).orElseThrow(() -> {
            throw new NoSuchIdException("요청하신 공지사항은 존재하지 않습니다.");
        });
    }

    private Member findMemberOrThrow(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new NoSuchMemberException("존재하지 않는 회원입니다.");
        });
    }


}
