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
public class ChFrontLightAssembled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String option;

    private int led;

    private int depth_35;

    private int depth_60;


    public static ChFrontLightAssembled create(FrontLightVo vo){

        return new ChFrontLightAssembledBuilder()
                .option(vo.getOption())
//                .depth_35(vo.getDepth_35())
//                .depth_60(vo.getDepth_60())
                .build();
    }
}
