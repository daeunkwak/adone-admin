package app.adoneadmin.repository.channel.backlight;

import app.adoneadmin.domain.channel.backlight.ChBackLightStan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChBackLightStanRepository extends JpaRepository<ChBackLightStan, Long> {
    ChBackLightStan findByStandard(String standard);
}
