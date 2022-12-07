package app.adoneadmin.repository.channel.frontlight;

import app.adoneadmin.domain.channel.frontlight.ChFrontLightAssembled;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChFrontLightAssembledRepository extends JpaRepository<ChFrontLightAssembled, Long> {
    ChFrontLightAssembled findByOption(String option);
}
