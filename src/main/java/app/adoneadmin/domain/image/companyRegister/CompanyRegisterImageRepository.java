package app.adoneadmin.domain.image.companyRegister;

import app.adoneadmin.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRegisterImageRepository extends JpaRepository<Image,Long> {

    @Query("select ci.imageId from CompanyRegisterImage ci where ci.member.memberId =:memberId")
    Long findByMemberId(Long memberId);
}
