package com.persoanltoy.backend.config.security;

import com.persoanltoy.backend.config.jwt.JwtAccessDeniedHandler;
import com.persoanltoy.backend.config.jwt.JwtAuthenticationEntryPoint;
import com.persoanltoy.backend.config.jwt.JwtSecurityConfig;
import com.persoanltoy.backend.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PERMIT_URL_ARRAY = {
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    private final TokenProvider tokenProvider;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/favicon.ico"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                    .headers().frameOptions().sameOrigin()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeHttpRequests()
                    .antMatchers(PERMIT_URL_ARRAY).permitAll()
                    .antMatchers("/auth/signup").permitAll()
                    .antMatchers("/auth/signin").permitAll()
                    .antMatchers("/usr/search1").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/usr/search2/*").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .apply(new JwtSecurityConfig(tokenProvider));
    }

}
