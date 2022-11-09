package app.adoneadmin.global.utl;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;


@Component
@Slf4j
public class JwtUtil implements InitializingBean {

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    public final static int accessTokenExpire = 10*600000;
    public final static int refreshTokenExpire = 20160;
    public final static String accessTokenName = "accessToken";
    public final static String refreshTokenName = "refreshToken";

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // accessToken 생성
    public String generateAccessToken(Long memberId) {

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(accessTokenExpire).toInstant()))
                .claim("memberId", memberId)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long validateAndExtractLoginToken(String tokenStr){
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(tokenStr).getBody();
            return claims.get("memberId", Long.class);
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return null;
    }


}
