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

    private boolean galva;

    private boolean stan;

    private boolean aluminum;

    private int hqlFrame;

    private int indirectLightFrame;

    private int specialFrame;

    private int normalFrame;

    private int lighting;

    private int nonLighting;


}
