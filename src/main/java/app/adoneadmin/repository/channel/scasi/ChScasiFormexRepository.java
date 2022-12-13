package app.adoneadmin.repository.channel.scasi;

import app.adoneadmin.domain.channel.scasi.ChScasiFormex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChScasiFormexRepository extends JpaRepository<ChScasiFormex, Long> {

    ChScasiFormex findByStandard(String standard);
}
