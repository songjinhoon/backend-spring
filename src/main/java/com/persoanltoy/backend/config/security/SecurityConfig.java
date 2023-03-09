package com.persoanltoy.backend.config.security;

import com.persoanltoy.backend.config.jwt.JwtAccessDeniedHandler;
import com.persoanltoy.backend.config.jwt.JwtAuthenticationEntryPoint;
import com.persoanltoy.backend.config.jwt.JwtFilter;
import com.persoanltoy.backend.config.jwt.TokenProvider;
import com.persoanltoy.backend.config.security.custom.CustomAuthenticationProvider;
import com.persoanltoy.backend.config.security.custom.CustomLoginFailureHandler;
import com.persoanltoy.backend.config.security.filter.CustomAuthenticationFilter;
import com.persoanltoy.backend.domains.member.domain.entity.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PERMIT_URL_ARRAY = {
            "/docs/index.html"
            /* swagger v2 */
//            "/v2/api-docs",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**",
            /* swagger v3 */
//            "/v3/api-docs/**",
//            "/swagger-ui/**"
    };

    private final CustomAuthenticationProvider customAuthenticationProvider;

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
        http.csrf().disable();
        http.httpBasic().disable();
        http.cors()
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                //세션 사용 하지 않음
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeHttpRequests()
                .antMatchers(PERMIT_URL_ARRAY).permitAll()
                .antMatchers("/member/sign-up").permitAll()
                .antMatchers("/member/sign-in").permitAll()
                .antMatchers("/admin/*").hasRole(Role.ROLE_ADMIN.name())
//                .antMatchers("/member/update").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/usr/search2/*").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class) // sign-in 작동
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class) // 그외 다 작동

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        final CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager(), tokenProvider);
        authenticationFilter.setFilterProcessesUrl("/member/sign-in");
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authenticationFilter.afterPropertiesSet();
        return authenticationFilter;
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(tokenProvider);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler();
    }

}
