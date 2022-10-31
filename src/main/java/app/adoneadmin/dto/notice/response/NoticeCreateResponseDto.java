package app.adoneadmin.dto.notice.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
@ApiModel(description = "공지사항 생성 응답 객체")
public class NoticeCreateResponseDto {

    @ApiModelProperty(value = "공지사항 id")
    @NotNull
    public Long noticeId;

    public static NoticeCreateResponseDto create(Long noticeId){
        return NoticeCreateResponseDto.builder()
                .noticeId(noticeId)
                .build();
    }
}
