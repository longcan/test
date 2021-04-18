package lonchin.user.common.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 4360725576737395684L;

    @Getter
    private final Integer code;

    public BizException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
