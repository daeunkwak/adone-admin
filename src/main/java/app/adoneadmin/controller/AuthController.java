package app.adoneadmin.controller;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.dto.auth.SignupDto;
import app.adoneadmin.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "auth", description = "회원가입, 로그인, 로그아웃 API")
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final MemberService memberService;

//    @Tag(name = "auth")
//    @ApiOperation(value = "일반회원 회원가입 API")
//    @PostMapping("/user/signup")
//    public ResponseEntity<?> userSignup(@Valid @RequestBody SignupDto signupDto){
//
//        Member member = memberService.userSignup(signupDto);
//        //return new ResponseEntity<>(SignUpCreateResponseDto.create(memberId), HttpStatus.CREATED);
//        return ResponseEntity.ok(member);
//    }

}
