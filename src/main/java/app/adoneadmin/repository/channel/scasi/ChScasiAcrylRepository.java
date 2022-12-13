package app.adoneadmin.repository.channel.scasi;

import app.adoneadmin.domain.channel.scasi.ChScasiAcryl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChScasiAcrylRepository extends JpaRepository<ChScasiAcryl, Long> {

    ChScasiAcryl findByStandard(String standard);
}
