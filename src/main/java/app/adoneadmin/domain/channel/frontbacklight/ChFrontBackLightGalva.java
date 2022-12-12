package app.adoneadmin.domain.channel.frontbacklight;

import app.adoneadmin.vo.channel.FrontBackLightVo;
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
public class ChFrontBackLightGalva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int led;

    private int depth80;

    private int depth100;


    public static ChFrontBackLightGalva create(FrontBackLightVo vo){

        return ChFrontBackLightGalva.builder()
                .standard(vo.getStandard())
                .led(vo.getLed())
                .depth80(vo.getDepth80())
                .depth100(vo.getDepth100())
                .build();
    }

    public void updateFrontBackLightGalva(FrontBackLightVo vo){
        this.led = vo.getLed();
        this.depth80 = vo.getDepth80();
        this.depth100 = vo.getDepth100();
    }
}
