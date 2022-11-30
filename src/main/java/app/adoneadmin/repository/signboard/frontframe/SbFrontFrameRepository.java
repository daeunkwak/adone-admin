package app.adoneadmin.repository.signboard.frontframe;

import app.adoneadmin.domain.signboard.SbFrontFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SbFrontFrameRepository extends JpaRepository<SbFrontFrame, Long> {


    @Query("select ff from SbFrontFrame ff where ff.standard =:standard")
    SbFrontFrame isStandardExist(String standard);

}
