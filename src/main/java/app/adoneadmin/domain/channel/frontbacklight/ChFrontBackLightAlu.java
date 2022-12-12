package app.adoneadmin.domain.channel.frontbacklight;

import app.adoneadmin.domain.channel.frontlight.ChFrontLightAlu;
import app.adoneadmin.vo.channel.FrontBackLightVo;
import app.adoneadmin.vo.channel.FrontLightVo;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
public class ChFrontBackLightAlu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int led;

    private int depth80;

    private int depth100;


    public static ChFrontBackLightAlu create(FrontBackLightVo vo){

        return ChFrontBackLightAlu.builder()
                .standard(vo.getStandard())
                .led(vo.getLed())
                .depth80(vo.getDepth80())
                .depth100(vo.getDepth100())
                .build();
    }

    public void updateFrontBackLightAlu(FrontBackLightVo vo){
        this.led = vo.getLed();
        this.depth80 = vo.getDepth80();
        this.depth100 = vo.getDepth100();
    }
}
