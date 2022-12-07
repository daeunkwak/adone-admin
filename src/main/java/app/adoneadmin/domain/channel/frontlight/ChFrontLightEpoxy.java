package app.adoneadmin.domain.channel.frontlight;

import app.adoneadmin.vo.channel.FrontLightVo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
public class ChFrontLightEpoxy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String option;

    private int led;

    private int depth_35;

    public static ChFrontLightEpoxy create(FrontLightVo vo){

        return new ChFrontLightEpoxyBuilder()
//                .option(vo.getOption())
//                .depth_35(vo.getDepth_35())
                .build();
    }
}
