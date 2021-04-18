package lonchin.user.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lonchin.user.common.model.AuthTokenDetail;
import lonchin.user.properties.JwtProperties;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Token工具类
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Component
@Slf4j
public class JsonWebTokenUtility {

    @Getter
    private final JwtProperties properties;

    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_ACCOUNT = "account";


    public JsonWebTokenUtility(JwtProperties properties) {
        this.properties = properties;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + properties.getExpiration() * 1000);
    }

    private String generateToken(Claims claims) {
        return properties.getEnv() + Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())
                .compact();
    }

    public String generateToken(AuthTokenDetail authTokenDetail) {
        Claims claims = new DefaultClaims();
        String userId = authTokenDetail.getUserId().toString();
        String account = authTokenDetail.getAccount();
        claims.setSubject(account);
        claims.put(CLAIM_KEY_USER_ID, userId);
        claims.put(CLAIM_KEY_ACCOUNT, account);
        return generateToken(claims);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(properties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("getClaimsFromToken:{}", e.getMessage());
            return null;
        }
        return claims;
    }

    public AuthTokenDetail parseAndValidate(String token) {
        String tokenHead = properties.getTokenHead();
        if (Strings.isNullOrEmpty(token) || !token.startsWith(tokenHead)) {
            return null;
        }
        AuthTokenDetail authTokenDetail;
        final String authToken = token.substring(tokenHead.length());
        Claims claims = getClaimsFromToken(authToken);
        if (claims == null || claims.getExpiration().before(new Date())) {
            throw new AccountExpiredException("token 已过期");
        }
        String phone = claims.getSubject();
        String userId = (String) claims.get(CLAIM_KEY_USER_ID);
        authTokenDetail = new AuthTokenDetail();
        authTokenDetail.setUserId(Long.parseLong(userId));
        authTokenDetail.setAccount(phone);
        return authTokenDetail;
    }

    public String refreshToken(AuthTokenDetail authTokenDetail) {
        return generateToken(authTokenDetail);
    }

    public long getExpiresIn() {
        return System.currentTimeMillis() + properties.getExpiration() * 1000;
    }
}
