package lonchin.core.emums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 性别
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@AllArgsConstructor
public enum GenderEnum {
    //性别
    NOT_CONFIRMED("未确定"), MALE("男"), FEMALE("女");

    @Getter
    private final String type;

}
