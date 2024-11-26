package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Spring Security 설정을 활성화하기 위해 사용되는 어노테이션
public class SecurityConfig {

   //requestMatchers("경로") : 특정경로
   //permitAll() : 모든 사용자 허용
   //hasRole("사용자") : 특정 사용자
   //hasAnyRole("","") : 특정 사용자 2개 이상 ( 1 이거나 2 )
   //anyRequest() : 모든 요청
   //authenticated() : 인증된 사용자만 접근 허용
  
   
   // CSRF 사이트 위/변조 방지가 자동으로 설정이 되어있음 -> 그러므로 토큰을 보내야 로그인이 진행이 된다
   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       //특정 경로 인가 작업
        http
            .authorizeHttpRequests((auth) -> auth
                  .requestMatchers("/","/login").permitAll() // / 이거나 /login 경로 일 경우는 모든 접근자를 허용 하도록 설정 
                  .requestMatchers("/admin").hasRole("ADMIN") // /admin 경로일 때  "ADMIN" 역할을 가진 사용자만 사용을 할 수 있도록 설정
                  .requestMatchers("/mypage/**").hasAnyRole("ADMIN", "USER") // /mypage 하위 경로 포함 일땐 "ADMIN" or "USER" 역할을 가진 사용자만 사용할 수 있도록 설정
                  .anyRequest().authenticated() // 설정된 경로 외의 모든 요청에 대해 인증된 사용자만 접근할 수 있도록 설정
            );
       
        return http.build();
    }
}
