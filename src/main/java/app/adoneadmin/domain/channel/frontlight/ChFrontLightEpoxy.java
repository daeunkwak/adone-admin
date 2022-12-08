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

    private String standard;

    private int led;

    private int depth35;

    public static ChFrontLightEpoxy create(FrontLightVo vo){

        return new ChFrontLightEpoxyBuilder()
                .standard(vo.getStandard())
                .depth35(vo.getDepth35())
                .build();
    }

    public void updateFrontLightEpoxy(FrontLightVo vo) {
            this.led = vo.getLed();
            this.depth35 = vo.getDepth35();

    }
}
