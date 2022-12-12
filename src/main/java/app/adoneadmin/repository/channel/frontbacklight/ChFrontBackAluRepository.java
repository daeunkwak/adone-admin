package app.adoneadmin.repository.channel.frontbacklight;

import app.adoneadmin.domain.channel.frontbacklight.ChFrontBackLightAlu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChFrontBackAluRepository extends JpaRepository<ChFrontBackLightAlu, Long> {

    ChFrontBackLightAlu findByStandard(String standard);
}
