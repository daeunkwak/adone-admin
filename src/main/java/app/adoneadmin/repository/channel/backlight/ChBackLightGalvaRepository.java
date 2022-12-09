package app.adoneadmin.repository.channel.backlight;


import app.adoneadmin.domain.channel.backlight.ChBackLightGalva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChBackLightGalvaRepository extends JpaRepository<ChBackLightGalva, Long> {
    ChBackLightGalva findByStandard(String standard);
}
