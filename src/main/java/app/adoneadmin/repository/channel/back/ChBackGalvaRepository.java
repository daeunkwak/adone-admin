package app.adoneadmin.repository.channel.back;

import app.adoneadmin.domain.channel.back.ChBackGalva;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChBackGalvaRepository extends JpaRepository<ChBackGalva, Long> {

    ChBackGalva findByStandard(String standard);
}
