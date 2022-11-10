package app.adoneadmin.service.file;

import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class FileService {

    private final AmazonS3 amazonS3;


    public void uploadNoticeFiles(List<MultipartFile> noticeFiles) {
    }
}
