package app.adoneadmin.service.image;

import app.adoneadmin.domain.image.Image;
import app.adoneadmin.domain.image.companyRegister.CompanyRegisterImageRepository;
import app.adoneadmin.domain.image.member.MemberImage;
import app.adoneadmin.domain.image.member.MemberImageRepository;
import app.adoneadmin.domain.member.Member;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.global.exception.handler.NoSuchMemberException;
import app.adoneadmin.repository.image.ImageRepository;
import app.adoneadmin.repository.member.MemberRepository;
import app.adoneadmin.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
@RequiredArgsConstructor
@Service
public class ImageService {

    private final S3Uploader s3Uploader;
    private final MemberRepository memberRepository;
    private final MemberImageRepository memberImageRepository;
    private final CompanyRegisterImageRepository companyRegisterImageRepository;
    private final ImageRepository imageRepository;

    /**
     * 시공사 대표 이미지 업데이트
     */
    public MemberImage updateMemberImage(Long memberId, MultipartFile memberImage) throws IOException {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("존재하지 않는 회원입니다."));
        Long imageId = memberImageRepository.findByMemberId(memberId);
        if(imageId != null){
            remove(imageId);
        }

        String imageUrl = s3Uploader.s3UploadOfMemberImage(member, memberImage);
        MemberImage result = new MemberImage(member, imageUrl);
        return memberImageRepository.save(result);
    }

    /**
     * 시공사 업체 등록 이미지 업데이트
     */
    public MemberImage updateCompanyRegisterImage(Long memberId, MultipartFile companyRegisterImage) throws IOException {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("존재하지 않는 회원입니다."));
        Long imageId = companyRegisterImageRepository.findByMemberId(memberId);
        if(imageId != null){
            remove(imageId);
        }

        String imageUrl = s3Uploader.s3UploadOfMemberImage(member, companyRegisterImage);
        MemberImage result = new MemberImage(member, imageUrl);
        return companyRegisterImageRepository.save(result);
    }


    /**
     * 이미지 삭제
     */
    public void remove(Long imageId) {

        Image image = imageRepository.findById(imageId).orElseThrow(() ->
                new CustomException("존재하지 않는 이미지입니다."));

        //s3 이미지 파일 삭제
        s3Uploader.delete(image);
        imageRepository.deleteById(imageId);
    }
}
