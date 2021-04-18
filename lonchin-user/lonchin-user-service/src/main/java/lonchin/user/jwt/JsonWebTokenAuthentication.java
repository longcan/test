package lonchin.user.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * TODO
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
public class JsonWebTokenAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = -3720190592187783214L;

    private final String jsonWebToken;
    private final UserDetails principal;


    public JsonWebTokenAuthentication(UserDetails principal, String jsonWebToken) {
        super(principal.getAuthorities());
        this.principal = principal;
        this.jsonWebToken = jsonWebToken;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getJsonWebToken() {
        return jsonWebToken;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
