package app.adoneadmin.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@ApiModel(description = "파일 반환 객체")
public class FileDto {

    @ApiModelProperty(value = "파일 id", example = "1")
    private Long fileId;

    @ApiModelProperty(value = "저장된 파일 url", example = "https://adone-bucket.s3.ap-northeast-2.amazonaws.com/requestSignboard/8_reference_bd613014-08ba-460b-87c7-fd3ab32cb832_2022030300006_0.png")
    private String fileUrl;

    public FileDto(){
        this.fileId = null;
        this.fileUrl = null;
    }

}
