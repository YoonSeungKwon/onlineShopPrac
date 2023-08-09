package yoon.project.onlineShop.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import yoon.project.onlineShop.domain.Members;
import yoon.project.onlineShop.repository.MemberRepository;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final MemberRepository memberRepository;

    private long acc_exp = 30 * 60 * 1000l;
    private long ref_exp = 3 * 60 * 60 * 1000l;
    private String SECRET_KEY = "ovnierfnklwmdomlsmclkonh8129931";
    final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private String findIdByToken(String token){
        return memberRepository.findMembersByRefreshToken(token).getId();
    }
    public String createAccessToken(String id){

        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + acc_exp));

        return  Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .claim("username", id)
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(){

        Claims claims = Jwts.claims()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ref_exp));

        return  Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(secretKey)
                .compact();
    }

    public String createNewToken(String ref_token){
        return createAccessToken(findIdByToken(ref_token));
    }

    public Authentication getAuthentication(String token){
        Members member = memberRepository.findMembersById(getId(token));
        return new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
    }

    public String getId(String token){
        return (String)Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
                .getBody().get("username");
    }

    public String resolveAccessToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer"))
            return token.substring(7);
        else return null;
    }

    public String resolveRefreshToken(HttpServletRequest request){
        String token = request.getHeader("XRefreshToken");
        if(StringUtils.hasText(token) && token.startsWith("Bearer"))
            return token.substring(7);
        else return null;
    }

    public boolean validateToken(String token){
        try{
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                    .parseClaimsJws(token).getBody();
            return !claims.getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

}
