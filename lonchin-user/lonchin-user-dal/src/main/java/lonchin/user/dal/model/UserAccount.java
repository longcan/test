package lonchin.user.dal.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lonchin.user.common.model.AbstractTimeEntity;

/**
 * @Description: 用户认证表
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "UserAccount对象", description = "用户登录表")
public class UserAccount extends AbstractTimeEntity {

    private static final long serialVersionUID = 6339011254723244841L;

    @ApiModelProperty(value = "帐号ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户Id")
    private Long userId;

    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户状态")
    private Boolean status;


}
