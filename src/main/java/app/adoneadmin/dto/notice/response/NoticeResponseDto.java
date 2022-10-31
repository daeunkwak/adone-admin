package app.adoneadmin.dto.notice.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "공지사항 조회 반환 객체")
public class NoticeResponseDto {

    @ApiModelProperty(value = "공지사항 id")
    private long noticeId;

    @ApiModelProperty(value = "작성자")
    private String userName;

    @ApiModelProperty(value = "공지사항 제목")
    private String noticeName;

    @ApiModelProperty(value = "공지사항 내용")
    private String noticeContent;

    @ApiModelProperty(value = "작성일", example = "2022-07-02T09:00:00")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createTime;

//    public static NoticeResponseDto from (Notice notice){
//
//    }

    @ApiModel(description = "공지사항 리스트 조회 반환 객체")
    public static class GroupById{

        @ApiModelProperty(value = "공지사항 리스트")
        private Map<Long, NoticeResponseDto> noticeResponseDtoGroupById;

        public GroupById(List<NoticeResponseDto> noticeResponseDtos){
            Map<Long, NoticeResponseDto> result = new HashMap<>();
            for(NoticeResponseDto noticeResponseDto : noticeResponseDtos){
                result.put(noticeResponseDto.getNoticeId(), noticeResponseDto);
            }
            this.noticeResponseDtoGroupById = result;
        }

    }

}
