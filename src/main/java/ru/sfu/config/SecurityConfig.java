package ru.sfu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class
 * @author Agapchenko V.V.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Password encoder bean
     * @author Agapchenko V.V.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure HTTP security
     * @author Agapchenko V.V.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/register").not().fullyAuthenticated()
                .antMatchers("/tvs**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated().and()
                .formLogin(form -> form.loginPage("/login").permitAll())
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
            return http.build();
    }


//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/register").not().fullyAuthenticated()
//                .antMatchers("/tvs**").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated().and()
//                .formLogin(form -> form.loginPage("/login").permitAll())
//                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));
//    }
}

