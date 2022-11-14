package app.adoneadmin.service.file;

import app.adoneadmin.domain.file.notice.NoticeFile;
import app.adoneadmin.domain.file.notice.NoticeFileRepository;
import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.global.exception.handler.CustomException;
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


    public List<NoticeFile> uploadNoticeFiles(List<MultipartFile> noticeFiles, Long noticeId) throws IOException {

        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new CustomException("존재하지 않는 공지사항입니다."));
        List<String> fileUrlList = s3Uploader.s3UploadOfNoticeFiles(notice, noticeFiles);

        log.info("file urlllllllllllllllllllllllllll ::: " + fileUrlList);

        List<NoticeFile> noticeFileList = new ArrayList<>();
        if(!fileUrlList.isEmpty()){
            for(String fileUrl : fileUrlList){
                NoticeFile noticeFile = noticeFileRepository.save(new NoticeFile(notice, fileUrl));
                noticeFileList.add(noticeFile);
            }
        }

        return noticeFileList;
    }
}
