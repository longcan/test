package lonchin.user.common.exception;


import lonchin.user.common.constants.BizErrConstants;

/**
 * 空异常
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
public class BizEmptyException extends BizException {

    private static final long serialVersionUID = -7064751432687579380L;

    public BizEmptyException(String message) {
        super(message, BizErrConstants.EMPTY);
    }
}
