package app.adoneadmin.domain.channel.frontlight;

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
@Table(name="ch_front_light_alu")
public class ChFrontLightAlu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int led;

    private int depth80;

    private int depth100;


    public static ChFrontLightAlu create(FrontLightVo vo){

        return ChFrontLightAlu.builder()
                .standard(vo.getStandard())
                .led(vo.getLed())
                .depth80(vo.getDepth80())
                .depth100(vo.getDepth100())
                .build();
    }

    public void updateFrontLightAlu(FrontLightVo vo){
        this.led = vo.getLed();
        this.depth80 = vo.getDepth80();
        this.depth100 = vo.getDepth100();
    }

}
