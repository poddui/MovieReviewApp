package hh.backend.MovieRatingApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import hh.backend.MovieRatingApp.webcontroller.*;


@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig  {
	
	@Autowired
	private UserDetailService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests()
        	.requestMatchers("/css/**").permitAll() 
        	.requestMatchers("/jsonmovies/**").permitAll()
        	.requestMatchers("/signup", "/saveuser").permitAll()
        	.requestMatchers("/users").permitAll()
        	.anyRequest().authenticated()
        	.and()
      .formLogin()
      		.loginPage("/login")
          .defaultSuccessUrl("/", true)
          .permitAll()
          .and()
      .logout()
          .permitAll()
          .and()
      .httpBasic();
      return http.build();
    }


}