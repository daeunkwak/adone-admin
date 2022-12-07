package app.adoneadmin.repository.channel.frontlight;

import app.adoneadmin.domain.channel.frontlight.ChFrontLightStan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChFrontLightStanRepository extends JpaRepository<ChFrontLightStan, Long> {
    ChFrontLightStan findByOption(String option);
}
