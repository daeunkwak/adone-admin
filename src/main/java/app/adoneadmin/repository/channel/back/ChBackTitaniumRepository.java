package app.adoneadmin.repository.channel.back;

import app.adoneadmin.domain.channel.back.ChBackTitanium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChBackTitaniumRepository extends JpaRepository<ChBackTitanium, Long> {

    ChBackTitanium findByStandard(String standard);
}
