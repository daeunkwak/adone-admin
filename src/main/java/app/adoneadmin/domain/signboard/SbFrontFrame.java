package app.adoneadmin.domain.signboard;

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
public class SbFrontFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int galva;

    private int stan;

    private int alu;

    public static SbFrontFrame createGalva(String standard, int cost) {

        return SbFrontFrame.builder()
                .standard(standard)
                .galva(cost)
                .stan(-1)
                .alu(-1)
                .build();
    }

    public static SbFrontFrame createStan(String standard, int cost) {

        return SbFrontFrame.builder()
                .standard(standard)
                .galva(-1)
                .stan(cost)
                .alu(-1)
                .build();
    }

    public static SbFrontFrame createAlu(String standard, int cost) {

        return SbFrontFrame.builder()
                .standard(standard)
                .galva(-1)
                .stan(-1)
                .alu(cost)
                .build();
    }

    public void updateGalva(int cost){
        this.galva = cost;
    }

    public void updateAlu(int cost){
        this.alu = cost;
    }

    public void updateStan(int cost){
        this.stan = cost;
    }
}

