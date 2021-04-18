package lonchin.user.common.constants;

/**
 * 业务错误常量
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
public class BizErrConstants {

    public final static Integer OK = 200;
    /**系统错误*/
    public final static Integer SYSTEN_ERROR = 500;
    /**空对象*/
    public final static Integer EMPTY = 1001;
    /**不存在*/
    public final static Integer NOT_FOUND = 404;
    /**不支持*/
    public final static Integer NO_PERMISSION = 401;
    /**token异常*/
    public final static Integer TOKEN_EXPIRED = 402;

}
