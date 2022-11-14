package app.adoneadmin.security.auth;

import app.adoneadmin.domain.member.Member;
import app.adoneadmin.global.exception.handler.CustomException;
import app.adoneadmin.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() ->
                new CustomException("존재하지 않는 아이디입니다."));


        return new PrincipalDetails(member);
    }
}