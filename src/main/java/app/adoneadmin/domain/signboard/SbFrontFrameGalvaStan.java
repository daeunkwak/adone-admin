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
public class SbFrontFrameGalvaStan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int galva;

    private int stan;

    public static SbFrontFrameGalvaStan createGalva(String standard, int cost) {

        return SbFrontFrameGalvaStan.builder()
                .standard(standard)
                .galva(cost)
                .stan(-1)
                .build();
    }

    public static SbFrontFrameGalvaStan createStan(String standard, int cost) {

        return SbFrontFrameGalvaStan.builder()
                .standard(standard)
                .galva(-1)
                .stan(cost)
                .build();
    }

}

