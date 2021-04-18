package lonchin.user.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lonchin.user.common.constants.BizErrConstants;
import lonchin.user.common.exception.BizEmptyException;
import lonchin.user.common.exception.BizErrorException;
import org.springframework.util.Assert;

/**
 * 校验工具类
 *
 * @Author chenlc
 * @Date 2019/9/22 18:09
 * @Version 1.0
 */
public class AssertUtil extends Assert {

    /**
     * 功能描述: 当条件不成立时抛出异常
     * @author chenlc
     * @date 2021/1/15
     * @param condition 条件
     * @param msg 异常消息
     * @return void
     */
    public static void assertTrue (boolean condition, String msg) {
        if (!condition) failAlert(BizErrConstants.SYSTEN_ERROR, msg);
    }

    /**
     * 功能描述: 当条件不成立时抛出异常
     * @author chenlc
     * @date 2021/1/15
     * @param condition 条件
     * @return void
     */
    public static void assertTrue(boolean condition) {
        assertTrue(condition, null);
    }

    /**
     * 功能描述: 字符串为空或空串时抛出异常
     * @author chenlc
     * @date 2021/1/15
     * @param param 条件判断的字符串
     * @param msg 异常消息
     * @return void
     */
    public static void assertBlank (String param, String msg) {
        if (StringUtils.isBlank(param)) failAlert(BizErrConstants.SYSTEN_ERROR, msg);
    }

    /**
     * 功能描述: 对象为空时抛出异常
     * @author chenlc
     * @date 2021/1/15
     * @param param 条件判断的对象
     * @param msg 异常消息
     * @return void
     */
    public static void assertNull (Object param, String msg) {
        if (null == param) failAlert(null, msg);
    }

    public static void failAlert(Integer code, String message) {
        if (message == null) {
            throw new BizEmptyException(message);
        } else {
            if (null == code) {
                throw new BizErrorException(message);
            } else {
                throw new BizErrorException(code, message);
            }
        }
    }
}
