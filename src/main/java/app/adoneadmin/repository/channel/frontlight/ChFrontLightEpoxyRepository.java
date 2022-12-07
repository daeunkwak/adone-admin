package app.adoneadmin.repository.channel.frontlight;

import app.adoneadmin.domain.channel.frontlight.ChFrontLightEpoxy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChFrontLightEpoxyRepository extends JpaRepository<ChFrontLightEpoxy, Long> {
    ChFrontLightEpoxy findByOption(String option);
}
