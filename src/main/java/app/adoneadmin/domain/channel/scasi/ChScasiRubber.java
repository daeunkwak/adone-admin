package app.adoneadmin.domain.channel.scasi;

import app.adoneadmin.vo.channel.ScasiVo;
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
public class ChScasiRubber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int depth20;

    private int depth30;

    private int depth50;


    public static ChScasiRubber create(ScasiVo vo){

        return new ChScasiRubberBuilder()
                .standard(vo.getStandard())
                .depth20(vo.getDepth20())
                .depth30(vo.getDepth30())
                .depth50(vo.getDepth50())
                .build();
    }

    public void updateScasiRubber(ScasiVo vo) {
        this.depth20 = vo.getDepth20();
        this.depth30 = vo.getDepth30();
        this.depth50 = vo.getDepth50();
    }

}
