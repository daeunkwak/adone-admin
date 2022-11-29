package app.adoneadmin.repository.signboard;

import app.adoneadmin.domain.signboard.SbFrontFrame;
import app.adoneadmin.domain.signboard.SbFrontTruss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SbFrontFrameRepository extends JpaRepository<SbFrontTruss, Long> {
}
