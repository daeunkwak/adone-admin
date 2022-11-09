package app.adoneadmin.filter;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.global.utl.JwtUtil;
import app.adoneadmin.repository.member.MemberRepository;
import app.adoneadmin.security.auth.PrincipalDetails;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    private static final String[] pattern = {"/", "/api/login"};

    @Autowired
    public JwtAuthorizationFilter(JwtUtil jwtUtil, MemberRepository memberRepository) {
        this.jwtUtil = jwtUtil;
        this.memberRepository = memberRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        //인증이 필요 없는 uri 일 경우 바로 통과
        if (PatternMatchUtils.simpleMatch(pattern,request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("인증필터 시작!!");

        System.out.println(request.getRequestURI());
        System.out.println(request.getRequestURL().toString());

        String accessToken = request.getHeader(JwtUtil.accessTokenName);

        //accessToken 헤더가 존재할 경우
        if (accessToken != null) {
            Long memberId = jwtUtil.validateAndExtractLoginToken(accessToken);

            //accessToken 이 유효할 경우
            if (memberId != null) {
                System.out.println("memberId: " + memberId);
                Member member = memberRepository.findById(memberId).orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 회원입니다."));

                //DB에 저장된 jwt 와 다를 경우
                if(accessToken.equals(member.getJwt())){
                    PrincipalDetails principalDetails = new PrincipalDetails(member);

                    //Authentication 객체 만들기
                    Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

                    //시큐리티의 세션에 접근하여 Authentication 객체를 저장.
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        }

        filterChain.doFilter(request, response);



    }
}
