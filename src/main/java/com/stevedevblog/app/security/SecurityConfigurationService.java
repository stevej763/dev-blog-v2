package com.stevedevblog.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Objects;

import static com.stevedevblog.app.security.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationService extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final Environment env;

    @Autowired
    public SecurityConfigurationService(PasswordEncoder passwordEncoder, Environment env) {
        this.passwordEncoder = passwordEncoder;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/about", "/error", "/css/*", "/js/*", "/icons/*", "/blog-post/*").permitAll()
            .antMatchers("/edit-post/*", "/new-post").hasRole(ADMIN.name())
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .failureUrl("/login?authError=failure");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails adminUser = User.builder()
                .username(env.getProperty("adminUser"))
                .password(passwordEncoder.encode(env.getProperty("adminPassword")))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(adminUser);
    }
}
