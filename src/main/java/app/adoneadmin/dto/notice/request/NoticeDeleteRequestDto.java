package app.adoneadmin.dto.notice.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
@ApiModel(description = "공지사항 삭제 요청 객체")
public class NoticeDeleteRequestDto {

    @ApiModelProperty(value = "삭제할 공지사항 id 리스트")
    private List<Long> noticeIdList;

}
