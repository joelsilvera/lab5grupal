package com.example.lab5grupal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConfiguracionSpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();//usa su login


        http.authorizeRequests()
                .antMatchers("/empleado/buscar/**").hasAnyAuthority("employee","manager")
                .antMatchers("/empleado/guardar/**").hasAuthority("manager")
                .antMatchers("/empleado/editar/**").hasAuthority("manager")
                .antMatchers("/empleado/nuevo/**").hasAuthority("manager")
                .anyRequest().permitAll();
        http.logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);//cuando cierre sesion,borra la cookie e invalida sesion
    }

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT email, pwd,activo FROM lab5_grupod.users where email=?")
                .authoritiesByUsernameQuery("Select email, r.authority from lab5_grupod.users u inner join authorities r on (r.username =u.idusuario) where u.email= ? and u.activo =1;");

    }
}