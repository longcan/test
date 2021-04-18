package lonchin.user.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Token对象信息
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/2/4
 */
@Data
@NoArgsConstructor
public class AuthTokenDetail implements Serializable {

    private static final long serialVersionUID = 1054852579711778221L;

    private Long userId;

    private String account;

    public AuthTokenDetail(Long userId, String account) {
        this.userId = userId;
        this.account = account;
    }
}
