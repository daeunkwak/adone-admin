package app.adoneadmin.domain.signboard.unit;

import app.adoneadmin.vo.signboard.PointVo;
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
public class SbPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private byte stan;

    private byte circle;

    private byte square;

    private byte round;

    private byte rotation;


    public static SbPoint create(PointVo vo){

        return SbPoint.builder()
                .standard(vo.getStandard())
                .id(vo.getId())
                .stan(vo.getStan())
                .circle(vo.getCircle())
                .square(vo.getSquare())
                .rotation(vo.getRotation())
                .round(vo.getRound())
                .build();
    }

}
