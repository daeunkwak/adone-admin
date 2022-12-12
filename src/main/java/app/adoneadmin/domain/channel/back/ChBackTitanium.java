package app.adoneadmin.domain.channel.back;

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
public class ChBackTitanium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int depth80;

    private int depth50;

    private int depth30;


    public static ChBackTitanium create(BackLightVo vo){

        return ChBackTitanium.builder()
                .standard(vo.getStandard())
                .depth80(vo.getDepth80())
                .depth50(vo.getDepth50())
                .depth30(vo.getDepth30())
                .build();
    }

    public void updateChBackTitanium(BackLightVo vo){
        this.depth80 = vo.getDepth80();
        this.depth50 = vo.getDepth50();
        this.depth30 = vo.getDepth30();
    }


}
