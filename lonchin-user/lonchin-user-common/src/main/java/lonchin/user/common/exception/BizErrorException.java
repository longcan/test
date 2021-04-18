package lonchin.user.common.exception;


import lonchin.user.common.constants.BizErrConstants;

/**
 * Error异常
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
public class BizErrorException extends BizException {

    private static final long serialVersionUID = -4563081729889879821L;

    public BizErrorException(String message) {
        super(message, BizErrConstants.SYSTEN_ERROR);
    }

    public BizErrorException(Integer code, String message) {
        super(message, code);
    }
}
