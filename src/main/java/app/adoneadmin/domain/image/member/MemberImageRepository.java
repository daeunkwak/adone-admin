package app.adoneadmin.domain.image.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberImageRepository extends JpaRepository<MemberImage, Long> {

    @Query("select mi.imageId from MemberImage mi where mi.member.memberId =:memberId")
    Long findByMemberId(@Param("memberId") Long memberId);

}
