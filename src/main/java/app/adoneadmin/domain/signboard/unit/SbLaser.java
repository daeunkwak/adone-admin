package app.adoneadmin.domain.signboard.unit;

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
public class SbLaser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int galva;

    private int stan;

    private int aluminum;


    public static SbLaser create(String standard, int aluminum, int galva, int stan){

        return SbLaser.builder()
                .standard(standard)
                .aluminum(aluminum)
                .galva(galva)
                .stan(stan)
                .build();
    }

}
