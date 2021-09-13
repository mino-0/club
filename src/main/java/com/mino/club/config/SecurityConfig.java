package com.mino.club.config;

import com.mino.club.security.handler.ClubLoginSuccessHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasAnyRole("USER");
        http.formLogin(); //인가/인증 문제시 로그인 화면
        http.csrf().disable(); //CSRF 토큰 발행하지 않게 설정

        http.oauth2Login().successHandler(successHandler());
    }

    @Bean
    public ClubLoginSuccessHandler successHandler() {
        return new ClubLoginSuccessHandler(passwordEncoder());
    }
/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //사용자 계정 : user1
        auth.inMemoryAuthentication().withUser("user1")
        //1111 패스워드 인코딩결과
        .password("$2a$10$oopwTBEpyqIWzZPdkoFxceIF.uRA64CjHzWXjvjlsU7SxXo5MNBgG")
        .roles("USER");
    }*/
}
