package app.adoneadmin.domain.signboard.constant;

import app.adoneadmin.global.exception.handler.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum MaterialType {

    ALUMINUM("A", "알루미늄"),
    GALVA("G", "갈바"),
    STAN("S", "스텐")
    ;

    private String alias;
    private String description;

    public static MaterialType of(String alias) {
        return Arrays.stream(values()).filter(t -> t.alias.equals(alias.toUpperCase()))
                .findFirst().orElseThrow(() -> new CustomException("해당되는 MaterialType을 찾을 수 없습니다."));
    }

}
