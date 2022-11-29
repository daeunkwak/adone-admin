package app.adoneadmin.repository.signboard.frontframe;

import app.adoneadmin.domain.signboard.SbFrontFrame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SbFrontFrameRepository extends JpaRepository<SbFrontFrame, Long>, SbFrontFrameRepositoryQuerydsl {

//    @Query(value = "select gs from SbFrontFrameGalvaStan gs where gs.galva not -1")
//    Collection<Object> findGalva();


}
