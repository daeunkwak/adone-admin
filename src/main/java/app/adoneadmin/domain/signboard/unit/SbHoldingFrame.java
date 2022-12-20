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
public class SbHoldingFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int aluminum;

    private int galva;

    private int stan;


    public static SbHoldingFrame create(String standard, int aluminum, int galva, int stan){

        return SbHoldingFrame.builder()
                .standard(standard)
                .aluminum(aluminum)
                .galva(galva)
                .stan(stan)
                .build();
    }

}
