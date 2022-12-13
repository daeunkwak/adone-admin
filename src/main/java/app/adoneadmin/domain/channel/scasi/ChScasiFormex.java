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
public class ChScasiFormex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int depth3;

    private int depth5;

    private int depth8;

    private int depth10;


    public static ChScasiFormex create(ScasiVo vo){

        return new ChScasiFormexBuilder()
                .standard(vo.getStandard())
                .depth3(vo.getDepth3())
                .depth5(vo.getDepth5())
                .depth8(vo.getDepth8())
                .depth10(vo.getDepth10())
                .build();
    }

    public void updateScasiFormex(ScasiVo vo) {
        this.depth3 = vo.getDepth3();
        this.depth5 = vo.getDepth5();
        this.depth8 = vo.getDepth8();
        this.depth10 = vo.getDepth10();
    }

}
