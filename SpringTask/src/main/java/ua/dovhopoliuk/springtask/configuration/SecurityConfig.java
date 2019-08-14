package ua.dovhopoliuk.springtask.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.dovhopoliuk.springtask.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/js/*", "/registration", "/api/conferences", "/img/**", "/").permitAll()


                .antMatchers(HttpMethod.GET, "/api/conferences").authenticated()
                .antMatchers(HttpMethod.GET, "/api/conferences/me").authenticated()
                .antMatchers(HttpMethod.GET, "/api/conferences/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/conferences/totalNumber").authenticated()
                .antMatchers(HttpMethod.GET, "/api/conferences/*/changeRegistration").authenticated()
                .antMatchers(HttpMethod.GET, "/api/notification/me").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/me").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/speakerStatistics/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/votes/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/vote/*/me").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/speakerStatistics/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/speakerStatistics/*").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/speakerStatistics/*").authenticated()
                .antMatchers(HttpMethod.GET, "/conferences").authenticated()
                .antMatchers(HttpMethod.GET, "/notifications").authenticated()
                .antMatchers(HttpMethod.GET, "/users").authenticated()

                .antMatchers(HttpMethod.GET, "/api/reportRequests/me").hasAuthority("SPEAKER")
                .antMatchers(HttpMethod.GET, "/reports").hasAuthority("SPEAKER")
                .antMatchers(HttpMethod.GET, "/reportRequests").hasAuthority("SPEAKER")

                .antMatchers(HttpMethod.GET, "/api/conferences/requests").hasAuthority("MODER")
                .antMatchers(HttpMethod.GET, "/api/conferences/finished").hasAuthority("MODER")
                .antMatchers(HttpMethod.GET, "/api/conferences/*/registeredGuests").hasAuthority("MODER")
                .antMatchers(HttpMethod.GET, "/api/notifications").hasAuthority("MODER")
                .antMatchers(HttpMethod.GET, "/api/reports").hasAuthority("MODER")
                .antMatchers(HttpMethod.GET, "/api/reportRequests").hasAuthority("MODER")
                .antMatchers(HttpMethod.GET, "/api/reports").hasAuthority("MODER")

                .antMatchers(HttpMethod.GET, "/api/users").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/conferences", "/api/votes/*").authenticated()
                .antMatchers(HttpMethod.POST, "/api/reportRequests/request/id").hasAuthority("SPEAKER")
                .antMatchers(HttpMethod.POST, "/api/conferences/*/processRequest", "/api/reportRequests/id").hasAuthority("MODER")

                .antMatchers(HttpMethod.PUT, "/api/users").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/conferences/*", "/api/conferences/*/finish", "/api/reports").hasAuthority("MODER")

                .antMatchers(HttpMethod.DELETE, "/api/notifications/*").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/reports/*").hasAuthority("MODER")
                .antMatchers(HttpMethod.DELETE, "/api/users/*").hasAuthority("ADMIN")
//
//
//                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
//                .antMatchers("/api/users/*/", "/notifications", "/home", "/conferences").authenticated()
//                .antMatchers(HttpMethod.PUT, "/api/users").authenticated()
//                .antMatchers(HttpMethod.POST, "/api/users/*/changeAvatar").authenticated()
//
//                .antMatchers(HttpMethod.GET, "/api/reportRequests/me").hasAuthority("SPEAKER")
//
//                .antMatchers(HttpMethod.GET, "/api/reportRequests/").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/users", "/all_users", "/api/reportRequests").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/users/*").hasAuthority("ADMIN")
                .and()
                .formLogin().loginPage("/login").usernameParameter("login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and()
                .csrf().disable();

    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(bcryptPasswordEncoder());
    }
}
