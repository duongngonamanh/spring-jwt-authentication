package vn.mycode.jwtauthenticationexample.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.mycode.jwtauthenticationexample.auth.filter.JwtFilter;
import vn.mycode.jwtauthenticationexample.auth.impl.AccessDeniedHandlerImpl;
import vn.mycode.jwtauthenticationexample.auth.impl.AuthenticationEntryPointImpl;
import vn.mycode.jwtauthenticationexample.util.JwtProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtFilter jwtFilter;
    private final AccessDeniedHandlerImpl accessDeniedHandlerImpl;
    private final AuthenticationEntryPointImpl authenticationEntryPointImpl;

    public WebSecurityConfig(JwtFilter jwtFilter, AccessDeniedHandlerImpl accessDeniedHandlerImpl, AuthenticationEntryPointImpl authenticationEntryPointImpl) {
        this.jwtFilter = jwtFilter;
        this.accessDeniedHandlerImpl = accessDeniedHandlerImpl;
        this.authenticationEntryPointImpl = authenticationEntryPointImpl;
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl)
                .accessDeniedHandler(accessDeniedHandlerImpl)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
