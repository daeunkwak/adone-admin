package app.adoneadmin.repository.channel.frontbacklight;

import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightStan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChFrontBackStanRepository extends JpaRepository<ChFrontBackLightStan, Long> {
    ChFrontBackLightStan findByStandard(String standard);
}
