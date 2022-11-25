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
public class SbFrontFrameAlu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String standard;

    private int aluminum;


    public static SbFrontFrameAlu create(String standard, int aluminum){

        return SbFrontFrameAlu.builder()
                .standard(standard)
                .aluminum(aluminum)
                .build();
    }

}
