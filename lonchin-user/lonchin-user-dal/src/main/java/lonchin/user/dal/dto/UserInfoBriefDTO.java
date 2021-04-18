package lonchin.user.dal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lonchin.core.emums.GenderEnum;

import java.io.Serializable;

/**
 * @Description: 用户简要信息
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Data
public class UserInfoBriefDTO implements Serializable {
    private static final long serialVersionUID = 8099509689148221859L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "性别:NOT_CONFIRMED,FEMALE,MALE")
    private GenderEnum gender;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
