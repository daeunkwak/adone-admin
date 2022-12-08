package app.adoneadmin.repository.channel.frontlight;

import app.adoneadmin.domain.channel.frontlight.ChFrontLightGalva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChFrontLightGalvaRepository extends JpaRepository<ChFrontLightGalva, Long> {
    //ChFrontLightGalva findByOption(String option);

    ChFrontLightGalva findByStandard(String standard);
}
