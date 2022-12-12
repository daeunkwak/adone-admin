package app.adoneadmin.repository.channel.frontbacklight;

import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightGalva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChFrontBackGalvaRepository extends JpaRepository<ChFrontBackLightGalva, Long> {

    ChFrontBackLightGalva findByStandard(String standard);
}
