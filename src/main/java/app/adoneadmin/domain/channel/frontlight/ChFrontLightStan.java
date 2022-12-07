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
public class ChFrontLightStan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String option;

    private int led;

    private int depth_50;

    private int depth_60;

    private int depth_80;

    private int depth_100;

    private int depth_120;


    public static ChFrontLightStan create(FrontLightVo vo){

        return new ChFrontLightStanBuilder()
                .option(vo.getOption())
//                .depth_50(vo.getDepth_50())
//                .depth_60(vo.getDepth_60())
//                .depth_80(vo.getDepth80())
//                .depth_100(vo.getDepth100())
//                .depth_120(vo.getDepth_120())
                .build();
    }
}