package org.taylor.nora.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.taylor.nora.gateway.handler.CustomHttpBasicServerAuthenticationEntryPoint;
import org.taylor.nora.gateway.handler.JsonServerAuthenticationFailureHandler;
import org.taylor.nora.gateway.handler.JsonServerAuthenticationSuccessHandler;
import org.taylor.nora.gateway.handler.JsonServerLogoutSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JsonServerAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private JsonServerAuthenticationFailureHandler authenticationFaillHandler;
    @Autowired
    private JsonServerLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomHttpBasicServerAuthenticationEntryPoint customHttpBasicServerAuthenticationEntryPoint;


    //security的鉴权排除列表
    private static final String[] excludedAuthPages = {
            "/login",
            "/signout",
            "/home/**",
            "/user/**",
            "/category/**"
    };


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors()
                .and()
                .authorizeExchange()
                .pathMatchers(excludedAuthPages).permitAll()  //无需进行权限过滤的请求路径
                .pathMatchers(HttpMethod.OPTIONS).permitAll() //option 请求默认放行
                .anyExchange().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .authenticationSuccessHandler(authenticationSuccessHandler) //认证成功
                .authenticationFailureHandler(authenticationFaillHandler) //登陆验证失败
                .and().logout().logoutSuccessHandler(logoutSuccessHandler)
                .and().exceptionHandling().authenticationEntryPoint(customHttpBasicServerAuthenticationEntryPoint)  //基于http的接口请求鉴权失败
                .and() .csrf().disable();//必须支持跨域
//                .logout().disable();

        return http.build();
    }



    /**
     * BCrypt密码编码
     * @return
     */
//    @Bean
//    public BCryptPasswordEncoder bcryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance(); //默认
    }



}
