package lonchin.user.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TODO
 * @author: chenlc
 * @date: 2021/4/10
 * @version: V1.0
 */
public abstract class BaseJsonWebTokenSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JsonWebTokenAuthenticationProvider authenticationProvider;

    private final JsonWebTokenAuthenticationFilter jsonWebTokenFilter;

    protected BaseJsonWebTokenSecurityConfig(JsonWebTokenAuthenticationProvider authenticationProvider, JsonWebTokenAuthenticationFilter jsonWebTokenFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jsonWebTokenFilter = jsonWebTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // disable CSRF, http basic, form login
                .csrf().disable() //
                .httpBasic().disable() //
                .formLogin().disable()

                // ReST is stateless, no sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                // return 403 when not authenticated
                .exceptionHandling().authenticationEntryPoint(new NoAuthenticationEntryPoint());

        // Let child classes set up authorization paths
        setupAuthorization(http);

        http.addFilterBefore(jsonWebTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    protected abstract void setupAuthorization(HttpSecurity http) throws Exception;
}
