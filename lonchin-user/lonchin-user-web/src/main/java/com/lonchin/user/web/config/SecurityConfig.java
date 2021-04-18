package com.lonchin.user.web.config;

import lonchin.user.jwt.BaseJsonWebTokenSecurityConfig;
import lonchin.user.jwt.JsonWebTokenAuthenticationFilter;
import lonchin.user.jwt.JsonWebTokenAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * TODO
 *
 * @author chenlc
 * @version v1.0.0
 * @date 2021/1/28
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends BaseJsonWebTokenSecurityConfig {

    protected SecurityConfig(JsonWebTokenAuthenticationProvider authenticationProvider, JsonWebTokenAuthenticationFilter jsonWebTokenFilter) {
        super(authenticationProvider, jsonWebTokenFilter);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/doc.html",
                "/error",
                "/static/**",
                "/client/**",
                "/v2/api-docs/**",
                "/api/file/sts/ios",
                "/v2/api-docs-ext/**",
                "/swagger-resources/**",
                "/webjars/**",
                "/favicon.ico");
    }

    @Override
    protected void setupAuthorization(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/api/auth/*").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated();
    }

}
