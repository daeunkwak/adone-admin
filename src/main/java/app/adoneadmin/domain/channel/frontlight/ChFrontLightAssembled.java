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

    private String standard;

    private int led;

    private int depth35;

    private int depth60;


    public static ChFrontLightAssembled create(FrontLightVo vo){

        return new ChFrontLightAssembledBuilder()
                .standard(vo.getStandard())
                .depth35(vo.getDepth35())
                .depth60(vo.getDepth60())
                .build();
    }

    public void updateFrontLightAssembled(FrontLightVo vo) {
            this.led = vo.getLed();
            this.depth35 = vo.getDepth35();
            this.depth60 = vo.getDepth60();

    }

}
