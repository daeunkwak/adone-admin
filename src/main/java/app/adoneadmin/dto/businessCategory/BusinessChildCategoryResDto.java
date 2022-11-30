package app.adoneadmin.dto.businessCategory;

import app.adoneadmin.domain.businessCategory.BusinessCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "카테고리 하위그룹 반환 객체")
public class BusinessChildCategoryResDto {

    @ApiModelProperty(value = "카테고리 ID")
    private Long categoryId;

    @ApiModelProperty(value = "카테고리 이름")
    private String categoryName;

    public BusinessChildCategoryResDto(BusinessCategory businessCategory){
        this.categoryId = businessCategory.getBusinessCategoryId();
        this.categoryName = businessCategory.getCategoryName();
    }
}
