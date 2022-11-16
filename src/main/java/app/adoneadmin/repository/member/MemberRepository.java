package app.adoneadmin.repository.member;

import app.adoneadmin.domain.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Member m SET m.jwt =:jwt where m.memberId =:memberId")
    void updateJwt(@Param("memberId") Long memberId, @Param("jwt") String jwt);


    @Transactional
    @Query("select m from Member m where m.companyName like %:searchWord% order by m.memberId desc")
    List<Member> getMemberSearch(@Param("searchWord") String searchWord);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Member> findByLoginId(String loginId);
}
