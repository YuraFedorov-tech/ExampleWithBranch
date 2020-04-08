package ru.yura.web.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.yura.web.app.handler.MySimpleUrlAuthenticationSuccessHandler;


/*
 *
 *@Data 10.03.2020
 *@autor Fedorov Yuri
 *@project spring_mvc
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
      //  log.info("configureGlobalSecurity");
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //    log.info("configure(HttpSecurity http)");
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**","/auth/**").permitAll()
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}