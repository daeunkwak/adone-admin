package app.adoneadmin.dto.notice.request;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.domain.notice.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;


@Getter
@Data
@ApiModel(description = "공지사항 생성 요청 객체")
public class NoticeCreateRequestDto {

    @ApiModelProperty(value = "공지사항 제목")
    private String noticeName;

    @ApiModelProperty(value = "공지사항 내용")
    private String noticeContent;

    public Notice toEntity(Member member){

        Notice notice = Notice.builder()
                .noticeName(this.noticeName)
                .noticeContent(this.noticeContent)
                .build();

        notice.setMember(member);
        return notice;
    }

}
