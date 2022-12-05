package app.adoneadmin.dto.notice.response;

import app.adoneadmin.domain.file.notice.NoticeFile;
import app.adoneadmin.domain.notice.Notice;
import app.adoneadmin.dto.file.FileDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ApiModelProperty(value = "작성일", example = "2022-07-02")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime regDateTime;

    @ApiModelProperty(value = "공지사항 파일 리스트")
    private List<FileDto> noticeFileList;


    public NoticeResponseDto(Notice notice){
        this.noticeId = notice.getNoticeId();
        this.userName = notice.getMember().getUsername();
        this.noticeName = notice.getNoticeName();
        this.noticeContent = notice.getNoticeContent();
        this.regDateTime = notice.getRegDateTime();

    }

    public static NoticeResponseDto from(Notice notice, List<FileDto> noticeFileList){

        return new NoticeResponseDtoBuilder()
            .noticeId(notice.getNoticeId())
            .userName(notice.getMember().getUsername())
            .noticeName(notice.getNoticeName())
            .noticeContent(notice.getNoticeContent())
            .regDateTime(notice.getRegDateTime())
                .noticeFileList(noticeFileList)
                .build();
    }

}
