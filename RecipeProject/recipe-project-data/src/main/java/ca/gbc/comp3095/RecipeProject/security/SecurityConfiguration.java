//*********************************************************************************//
//* Project: Null Recipes
//        * Assignment: Assignment #1
//        * Author(s): Nathan Power
//        * Student Number: 101247770
//        * Date: October 24th, 2021
//        * Description: This file utilizes Spring Security to encrypt the users password, provide authentication to
//        * logged in users, set limited access to unauth users, and provide redirection for the auth success handler.
//*********************************************************************************//

package ca.gbc.comp3095.RecipeProject.security;

import ca.gbc.comp3095.RecipeProject.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final AuthenticationSuccessHandler successHandler;

    public SecurityConfiguration(@Lazy UserService userService, AuthenticationSuccessHandler successHandler) {
        this.userService = userService;
        this.successHandler = successHandler;
    }

    @Bean
    public BCryptPasswordEncoder pwEncode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(pwEncode());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/registration**",
                "/forgotpassword**",
                "/resetpassword**",
                "/js/**",
                "/css/**",
                "/img/**",
                "/webjars/**",
                "/h2/**" // used for testing only by course instructor and our team*
        ).permitAll().anyRequest().authenticated()
                // next section allows use of H2 console for testing and would be removed for production in the real world***
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2/**")
                // H2 access script ends here
                .and()
                .formLogin().loginPage("/login").successHandler(successHandler).permitAll()
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();
    }
}
