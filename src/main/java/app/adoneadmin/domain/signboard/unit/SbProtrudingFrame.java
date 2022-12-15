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
public class SbProtrudingFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int standard;

    private int aluminum;

    private int galva;

    private int stan;


    public static SbProtrudingFrame create(int standard, int aluminum, int galva, int stan){

        return SbProtrudingFrame.builder()
                .standard(standard)
                .aluminum(aluminum)
                .galva(galva)
                .stan(stan)
                .build();
    }

}
