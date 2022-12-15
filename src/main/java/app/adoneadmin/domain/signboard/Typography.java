package app.adoneadmin.domain.signboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable //jpa 내장타입
@Getter
public class Typography {

    private String mainText;

    private int mainTextType;   // 종류

    private Integer mainTextLightType;  // 조명

    private Integer mainTextMaterialType;   // 재질

    private Integer mainTextDepth;  // 입체(글자 깊이)

    private int mainTextFontSize;   // 각(글자 높이)

    @Column(nullable = true)
    private String mainTextFrontColor;  // 전면 색상

    private String mainTextSideColor;   // 옆면 색상

    //후광 LED 색상
    private String mainTextBackLedColor;

    private int mainTextFont;   // 폰트

    //서브 텍스트 관련 필드
    private String subText;

    private Integer subTextType;

    private Integer subTextLightType;

    private Integer subTextMaterialType;

    private Integer subTextDepth;

    private Integer subTextFontSize;

    //전면
    private String subTextFrontColor;

    //옆면
    private String subTextSideColor;

    //후광 LED 색상
    private String subTextBackLedColor;

    private Integer subTextFont;

    private int textDivision;  // TODO : division type String으로 변경

}
