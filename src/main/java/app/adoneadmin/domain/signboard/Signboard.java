package app.adoneadmin.domain.signboard;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@ToString
@Embeddable //jpa 내장타입
@Getter
public class Signboard {

    private int signboardType;

    private int signboardForm;

    private int materialType;

    private int channelType;

    private int channelMaterialType;

    private int lightType;

    private int division;

    private int vertical;

    private int width;

    @Column(nullable = false)
    private String mainColor;

    private String otherColor;

    protected Signboard(){};

    public Signboard(int signboardType, int signboardForm, int materialType, int lightType,
                     int division, int vertical, int width, String mainColor, String otherColor){

        this.signboardType = signboardType;
        this.signboardForm = signboardForm;
        this.materialType = materialType;
        this.lightType = lightType;
        this.division = division;
        this.vertical = vertical;
        this.width = width;
        this.mainColor = mainColor;
        this.otherColor = otherColor;
    }

}
