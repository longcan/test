package lonchin.user.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lonchin.user.common.constants.BizErrConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 数据封装
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
@Data
@NoArgsConstructor
@ApiModel("数据封装")
public class ResultBody<T> implements Serializable {

    private static final long serialVersionUID = 2667894614363863699L;


    /**
     * 默认成功
     */
    @ApiModelProperty("状态码:正常:200,系统错误:500,缺参数:1001")
    private Integer code = BizErrConstants.OK;

    /**
     * 返回具体数据
     */
    private T data;
    /**
     * 错误消息
     */
    @ApiModelProperty("提示")
    private String msg;
    /**
     * 错误路径
     */
    private String path;
    /**
     * 错误时间
     */
    private String timestamp;

    public ResultBody(T data) {
        this.data = data;
    }

    public ResultBody(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.timestamp = LocalDateTime.now().toString();
    }
}
