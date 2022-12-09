package app.adoneadmin.repository.channel.backlight;

import app.adoneadmin.domain.channel.backlight.ChBackLightTitaniumGold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChBackLightTitaniumGoldRepository extends JpaRepository<ChBackLightTitaniumGold, Long> {
    ChBackLightTitaniumGold findByStandard(String standard);
}
