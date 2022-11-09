package app.adoneadmin.dto.image;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "이미지 반환 객체")
public class ImageDto {

    @ApiModelProperty(value = "이미지 id", example = "1")
    private Long imageId;

    @ApiModelProperty(value = "저장된 이미지 url", example = "https://adone-bucket.s3.ap-northeast-2.amazonaws.com/requestSignboard/8_reference_bd613014-08ba-460b-87c7-fd3ab32cb832_2022030300006_0.png")
    private String imageUrl;

}
