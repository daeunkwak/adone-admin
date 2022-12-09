package app.adoneadmin.service;

import app.adoneadmin.domain.file.File;
import app.adoneadmin.domain.file.notice.NoticeFile;
import app.adoneadmin.domain.file.notice.NoticeFileRepository;
import app.adoneadmin.domain.image.Image;
import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.dto.common.DeleteRequestDto;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchIdException;
import app.adoneadmin.repository.file.FileRepository;
import app.adoneadmin.repository.notice.NoticeRepository;
import app.adoneadmin.s3.S3Uploader;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class FileService {

    private final AmazonS3 amazonS3;
    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final S3Uploader s3Uploader;
    private final FileRepository fileRepository;


    /**
     * 공지사항 첨부파일 리스트 업로드
     */
    public List<NoticeFile> uploadNoticeFiles(List<MultipartFile> noticeFiles, Long noticeId) throws IOException {

        Notice notice = findNoticeOrThrow(noticeId);
        List<String> fileUrlList = s3Uploader.s3UploadOfNoticeFiles(notice, noticeFiles);

        List<NoticeFile> noticeFileList = new ArrayList<>();
        if(!fileUrlList.isEmpty()){
            for(String fileUrl : fileUrlList){
                NoticeFile noticeFile = noticeFileRepository.save(new NoticeFile(notice, fileUrl));
                noticeFileList.add(noticeFile);
            }
        }

        return noticeFileList;
    }


    /**
     * 공지사항 첨부파일 리스트 업데이트
     */
    public List<NoticeFile> updateNoticeFiles(long memberId, List<MultipartFile> noticeFiles, long noticeId) throws IOException {

        Notice notice = findNoticeOrThrow(noticeId);
        if(notice.getMember().getMemberId() != memberId){
            throw new CustomException("공지사항을 수정할 권한이 없습니다.");
        }

        List<Long> fileIdList = noticeFileRepository.findByNoticeId(notice.getNoticeId());
        if(!fileIdList.isEmpty()){
            for(Long fileId : fileIdList){
                File file = fileRepository.findById(fileId).orElseThrow(() -> new NoSuchIdException("요청하신 파일은 존재하지 않습니다."));
                file.updateIsDeleted();
            }
        }

        List<String> fileUrlList = s3Uploader.s3UploadOfNoticeFiles(notice, noticeFiles);
        List<NoticeFile> noticeFileList = new ArrayList<>();
        if(!fileUrlList.isEmpty()){
            for(String fileUrl : fileUrlList){
                NoticeFile noticeFile = noticeFileRepository.save(new NoticeFile(notice, fileUrl));
                noticeFileList.add(noticeFile);
            }
        }
        return noticeFileList;
    }


    /**
     * 공지사항 첨부파일 리스트 삭제
     */
    public void deleteNoticeFiles(Long noticeId, DeleteRequestDto req) {

        // TODO : 공지사항과 대조 > 예외처리
        for(Long id : req.getIdList()){
            noticeFileRepository.deleteById(id);
        }

    }


    private Notice findNoticeOrThrow(Long noticeId){
        return noticeRepository.findById(noticeId).orElseThrow(() -> {
            throw new NoSuchIdException("요청하신 공지사항은 존재하지 않습니다.");
        });
    }

}
