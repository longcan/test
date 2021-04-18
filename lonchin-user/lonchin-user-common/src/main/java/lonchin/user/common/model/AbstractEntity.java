package lonchin.user.common.model;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 实体类父类
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
public abstract class AbstractEntity extends AbstractTimeEntity {

    private static final long serialVersionUID = -8496960292394365290L;


    @ExcelIgnore
    @Getter
    @Setter
    @ApiModelProperty(value = "创建人Id")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    protected Long createUserId;

    @ExcelIgnore
    @Getter
    @Setter
    @ApiModelProperty(value = "创建人")
    @TableField(updateStrategy = FieldStrategy.NEVER)
    protected String createAccount;

    @ExcelIgnore
    @Getter
    @Setter
    @ApiModelProperty(value = "修改人Id")
    protected Long modifiedUserId;

    @ExcelIgnore
    @Getter
    @Setter
    @ApiModelProperty(value = "修改人名称")
    protected String modifiedAccount;

}

