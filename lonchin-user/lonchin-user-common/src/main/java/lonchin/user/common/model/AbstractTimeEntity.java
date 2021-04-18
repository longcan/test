package lonchin.user.common.model;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lonchin.user.common.utils.LocalDateUtil;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体类父类
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
public abstract class AbstractTimeEntity extends Model implements Serializable {

    private static final long serialVersionUID = -8496960292394365290L;
    /**
     * 创建时间
     */
    @ExcelIgnore
    @JsonFormat(timezone = LocalDateUtil.TIME_ZONE, pattern = LocalDateUtil.DATETIME_FORMAT)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @Getter
    @Setter
    public LocalDateTime createTime;


    /**
     * 修改时间
     */
    @ExcelIgnore
    @JsonFormat(timezone = LocalDateUtil.TIME_ZONE, pattern = LocalDateUtil.DATETIME_FORMAT)
    @TableField(value = "modified_time", fill = FieldFill.INSERT_UPDATE)
    @Getter
    @Setter
    public LocalDateTime modifiedTime;

  
}

