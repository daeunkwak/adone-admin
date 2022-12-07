package app.adoneadmin.repository.channel.frontlight;

import app.adoneadmin.domain.channel.frontlight.ChFrontLightAlu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChFrontLightAluRepository extends JpaRepository<ChFrontLightAlu, Long> {
//
//    @Query("select fl from ChFrontLightAlu fl where fl.option =:option")
//    boolean isOptionExist(String option);

    ChFrontLightAlu findByOption(String option);
}
