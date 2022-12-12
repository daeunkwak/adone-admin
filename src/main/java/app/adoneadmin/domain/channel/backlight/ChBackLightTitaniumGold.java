package app.adoneadmin.domain.channel.backlight;

import app.adoneadmin.vo.channel.BackLightVo;
import app.adoneadmin.vo.signboard.StandardCostVo;
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
public class ChBackLightTitaniumGold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int led;

    private int depth30;


    public static ChBackLightTitaniumGold create(BackLightVo vo){

        return ChBackLightTitaniumGold.builder()
                .standard(vo.getStandard())
                .led(vo.getLed())
                .depth30(vo.getDepth30())
                .build();
    }

    public void updateBackLightTitaniumGold(BackLightVo vo){
        this.led = vo.getLed();
        this.depth30 = vo.getDepth30();
    }

}
