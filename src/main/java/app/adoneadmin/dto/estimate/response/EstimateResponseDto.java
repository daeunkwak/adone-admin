package app.adoneadmin.dto.estimate.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "견적 조회 반환 객체(입찰 견적서)")
public class EstimateResponseDto {

    @ApiModelProperty(value = "입찰 견적서 id")
    private Long bidingEstimateId;

    @ApiModelProperty(value = "견적업체명")
    private String companyName;

    @ApiModelProperty(value = "총 공급가액")
    private int totalSupplyCost;

    @ApiModelProperty(value = "합계금액(부가세 포함)")
    private int totalSurtaxCost;

    @ApiModelProperty(value = "견적 등록일")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime regDateTime;


}
