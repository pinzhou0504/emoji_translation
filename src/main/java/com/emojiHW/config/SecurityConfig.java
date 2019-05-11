package com.emojiHW.config;

import com.emojiHW.extend.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;
//    step 1
//    @Autowired
//    public void configureGlocal(AuthenticationManagerBuilder auth)
//        throws Exception{
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("{noop}password").roles("REGISTERED_USER");
//    }
//
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//    }

    //Step 2
    @Autowired
    public void configureGlocal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("user")
//                .password("{noop}password").roles("USER");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
               .authorizeRequests().antMatchers("/api/users/login","/api/user/login","/api/users/signup")
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/api/**").hasAnyRole("REGISTERED_USER","ADMIN")
                .and()
                    .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                         .and()
                     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //                .and()
        //                  .formLogin();
    }

}
