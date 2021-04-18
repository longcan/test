package lonchin.user.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * JWT配置
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
@Data
@Validated
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    @NotBlank
    private String env;
    @NotNull
    private Long expiration;
    @NotBlank
    private String header;
    @NotBlank
    private String secret;
    @NotBlank
    private String tokenHead;
}
