package app.adoneadmin.dto.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ApiModel(description = "처리 결과 반환 객체")
public class CommonApiResult {

    @ApiModelProperty(value = "처리 결과 코드", example = "성공시 200 반환")
    private int code;

    @ApiModelProperty(value = "처리 결과 메세지", example = "회원 정보 수정 성공")
    private String message;

    public static CommonApiResult createOk(String message){
        return CommonApiResult.builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .build();
    }

}

