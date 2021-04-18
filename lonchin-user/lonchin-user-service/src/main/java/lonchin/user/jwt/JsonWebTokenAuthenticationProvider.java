package lonchin.user.jwt;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import lonchin.user.common.model.AuthTokenDetail;
import lonchin.user.dal.model.UserAccount;
import lonchin.user.service.UserInfoService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 权限处理逻辑
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Slf4j
@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

    private final UserInfoService userInfoService;

    public JsonWebTokenAuthenticationProvider(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticatedUser = null;
        if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                && authentication.getPrincipal() != null) {
            String tokenHeader = (String) authentication.getPrincipal();
            UserDetails userDetails = parseToken(tokenHeader);
            if (userDetails != null) {
                authenticatedUser = new JsonWebTokenAuthentication(userDetails, tokenHeader);
            }
        } else {
            authenticatedUser = authentication;
        }
        return authenticatedUser;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
                || authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
    }

    private UserDetails parseToken(String tokenHeader) {
        AuthTokenDetail authTokenDetail = userInfoService.queryAuthDetail(tokenHeader);
        if (null == authTokenDetail) {
            return null;
        }

        Long teacherId = authTokenDetail.getUserId();

        UserAccount studentAccount = userInfoService.queryById(teacherId);
        if (Objects.isNull(studentAccount)) {
            return null;
        }
        long accessId = teacherId;
        //添加默认学生ID校验
        Boolean status = studentAccount.getStatus();
        return new org.springframework.security.core.userdetails.User(Long.toString(accessId), "",
                status, true, true, true,
                Lists.newArrayList());
    }

}
