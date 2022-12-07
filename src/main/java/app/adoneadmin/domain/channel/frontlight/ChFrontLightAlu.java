package app.adoneadmin.domain.channel.frontlight;

import app.adoneadmin.domain.signboard.SbFrontTruss;
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

    @Column(name = "option")
    private String option;

    @Column(name = "led")
    private int led;

    @Column(name = "depth80")
    private int depth80;

    @Column(name = "depth100")
    private int depth100;


    public static ChFrontLightAlu create(FrontLightVo vo){

        return ChFrontLightAlu.builder()
                .option(vo.getOption())
                .led(vo.getLed())
                .depth80(vo.getDepth80())
                .depth100(vo.getDepth100())
                .build();
    }
}
