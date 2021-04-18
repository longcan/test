package lonchin.user.jwt;

import lonchin.user.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 * Token认证拦截器
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Component
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    @Autowired
    private final JwtProperties properties;

    public JsonWebTokenAuthenticationFilter(JwtProperties properties) {
        this.properties = properties;
        // Don't throw exceptions if the header is missing
        this.setExceptionIfHeaderMissing(false);

        // This is the request header it will look for
        this.setPrincipalRequestHeader(properties.getHeader());
        this.setCredentialsRequestHeader(properties.getTokenHead());
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
