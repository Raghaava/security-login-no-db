package org.learning.securityloginnodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder bcryptPasswordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .passwordEncoder(bcryptPasswordEncoder)
        .withUser("Test").password(bcryptPasswordEncoder.encode("Test")).roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/user/**").hasRole("USER")
        .antMatchers("/").permitAll()
        .and()
        .formLogin().loginPage("/login").defaultSuccessUrl("/user")
        .and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
  }

  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
