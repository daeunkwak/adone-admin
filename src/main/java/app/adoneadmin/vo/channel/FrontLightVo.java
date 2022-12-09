package app.adoneadmin.vo.channel;

import io.swagger.annotations.ApiModel;
import lombok.*;


@Getter
@Setter
public class FrontLightVo {

    private long id;

    private String standard;

    private int led;

    private int depth80;

    private int depth100;

    private int depth35;

    private int depth60;

    private int depth50;

    private int depth120;

}
