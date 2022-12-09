package app.adoneadmin.domain.channel.backlight;

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

    private int cost;


    public static ChBackLightTitaniumGold create(StandardCostVo vo){

        return ChBackLightTitaniumGold.builder()
                .standard(vo.getStandard())
                .cost(vo.getCost())
                .build();
    }

    public void updateCost(int cost){
        this.cost = cost;
    }

}
