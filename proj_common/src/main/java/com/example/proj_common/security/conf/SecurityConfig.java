package com.example.proj_common.security.conf;

import com.example.proj_common.security.filter.JwtAuthenticationTokenFilter;
import com.example.proj_common.security.handler.AccessDeniedHandlerImpl;
import com.example.proj_common.security.handler.AuthenticationEntryPointImpl;
import com.example.proj_common.security.service.LogoutSuccessHandlerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    final LogoutSuccessHandlerImpl logoutSuccessHandler;

    final AccessDeniedHandlerImpl accessDeniedHandler;

    final AuthenticationEntryPointImpl authenticationEntryPoint;

    /**
     * 使用BCrypt加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不使用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/login", "/error/test", "/actuator/**").anonymous()
                .anyRequest().authenticated();

        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 添加Logout filter
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

        //异常处理
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

}
