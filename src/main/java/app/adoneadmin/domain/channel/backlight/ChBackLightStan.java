package app.adoneadmin.domain.channel.backlight;

import app.adoneadmin.vo.channel.BackLightVo;
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
public class ChBackLightStan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int led;

    private int depth80;

    private int depth50;

    private int depth30;


    public static ChBackLightStan create(BackLightVo vo){

        return ChBackLightStan.builder()
                .standard(vo.getStandard())
                .led(vo.getLed())
                .depth80(vo.getDepth80())
                .depth50(vo.getDepth50())
                .depth30(vo.getDepth30())
                .build();
    }

    public void updateBackLightStan(BackLightVo vo){
        this.led = vo.getLed();
        this.depth80 = vo.getDepth80();
        this.depth50 = vo.getDepth50();
        this.depth30 = vo.getDepth30();
    }

}
