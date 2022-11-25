package app.adoneadmin.repository.signboard;

import app.adoneadmin.domain.signboard.SbFrontFrameGalvaStan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SbFrontFrameGalvaStanRepository extends JpaRepository<SbFrontFrameGalvaStan, Long> {
    @Query
    //Collection<Object> findGalva("select * from ");

    Collection<Object> findStan();
}
