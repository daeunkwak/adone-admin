package app.adoneadmin.dto.auth;

import app.adoneadmin.domain.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
@ApiModel(description = "임시 회원가입 요청 객체")
public class SignupDto {

    @ApiModelProperty(value = "로그인id")
    @NotNull
    private String loginId;

    @ApiModelProperty(value = "비밀번호")
    @NotNull
    private String password;

    @ApiModelProperty(value = "닉네임")
    @NotNull
    private String username;

//    public Member toEntity(){
//
//        return Member.builder()
//                .loginId(this.loginId)
//                .password(this.password)
//                .username(this.username)
//                .build();
//    }
}

