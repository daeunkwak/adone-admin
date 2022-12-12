package app.adoneadmin.repository.channel.back;

import app.adoneadmin.domain.channel.back.ChBackStan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChBackStanRepository extends JpaRepository<ChBackStan, Long> {
    ChBackStan findByStandard(String standard);
}
