package lonchin.user.jwt;


import lombok.extern.slf4j.Slf4j;
import lonchin.user.common.constants.BizErrConstants;
import lonchin.user.common.model.ResultBody;
import lonchin.user.common.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登录处理
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Slf4j
public class NoAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 当访问的资源没有权限，会调用这里
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResultBody<Void> restResponse = new ResultBody<>(BizErrConstants.TOKEN_EXPIRED, "没有登录或登录已过期!");

        response.getWriter().println(JsonUtils.toJson(restResponse));
        response.getWriter().flush();
    }
}
