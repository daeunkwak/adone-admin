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
public class ChFrontLightGalva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int led;

    private int depth50;

    private int depth60;

    private int depth80;

    private int depth100;

    private int depth120;

    // String option, int led, int depth_50, int depth_60, int depth_80, int depth_100, int depth_120
    public static ChFrontLightGalva create(FrontLightVo vo){

        return new ChFrontLightGalvaBuilder()
                .standard(vo.getStandard())
                .depth50(vo.getDepth50())
                .depth60(vo.getDepth60())
                .depth80(vo.getDepth80())
                .depth100(vo.getDepth100())
                .depth120(vo.getDepth120())
                .build();
    }

    public void updateFrontLightGalva(FrontLightVo vo) {
        this.led = vo.getLed();
        this.depth50 = vo.getDepth50();
        this.depth60 = vo.getDepth60();
        this.depth80 = vo.getDepth80();
        this.depth100 = vo.getDepth100();
        this.depth120 = vo.getDepth120();
    }

}