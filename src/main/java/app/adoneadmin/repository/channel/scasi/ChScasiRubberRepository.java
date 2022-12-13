package app.adoneadmin.repository.channel.scasi;

import app.adoneadmin.domain.channel.scasi.ChScasiRubber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChScasiRubberRepository extends JpaRepository<ChScasiRubber, Long> {

    ChScasiRubber findByStandard(String standard);
}
